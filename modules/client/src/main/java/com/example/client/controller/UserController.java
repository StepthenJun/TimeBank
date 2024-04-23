package com.example.client.controller;
import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.api.IdCardAuth;
import com.example.client.annotation.Notify;
import com.example.client.domain.*;
import com.example.client.domain.vo.EventVo;
import com.example.client.domain.vo.UserVo;
import com.example.client.service.*;
import com.example.core.domain.R;
import com.example.core.domain.model.accusation.AccusationBody;
import com.example.core.domain.model.user.AuthIdCardBody;
import com.example.core.domain.model.user.UpdateInfoBody;
import com.example.core.util.ValidatorUtils;
import com.example.oss.exception.OssException;
import com.example.oss.util.FileService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@SaIgnore
@RequestMapping("/user")
// TODO @SaIgnore要删去
public class UserController {
    private final AuthIdCardService authIdCardService;
    private final UserService userService;
    private final EventService eventService;
    private final ParticipationsService participationsService;
    private final AccusationService accusationService;
    private final FileService fileService;
    private final CourseService courseService;
    /**
     * 用户进行实名认证
     * @return String 提示信息
     */
    @PostMapping("/authIdCard")
    public R<String> authIdCard(@Validated @RequestBody AuthIdCardBody authBody){
        ValidatorUtils.validate(authBody);
        String realName = authBody.getRealName();
        String idNumber = authBody.getIdNumber();
        String responseString = IdCardAuth.AuthIdCard(idNumber, realName);
        // 使用Fastjson解析JSON响应
        JSONObject jsonObj = JSON.parseObject(responseString);
        // 提取 'isok' 和 'birthday' 字段
        boolean isOk = jsonObj.getJSONObject("result").getBoolean("isok");
        AuthIdCard authIdCard = BeanUtil.copyProperties(authBody, AuthIdCard.class);
        if (isOk){
            // TODO 给用户的信息加个出生日期字段
            String birthDateStr = idNumber.substring(6, 14); // 提取出生年月日部分
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            try{
                Date birthDate = dateFormat.parse(birthDateStr);
                System.out.println(birthDate);
            }catch (ParseException e) {
                throw new RuntimeException(e);
            }
            authIdCardService.save(authIdCard);
            userService.update(new LambdaUpdateWrapper<User>()
                    .eq(User::getId, StpUtil.getLoginIdAsLong())
                    .set(User::getIsRealName,isOk));
        }
        return isOk ? R.ok("实名认证成功") : R.fail("实名认证失败");
    }
    /**
     * 主页获取用户信息
     * @return UserVo 用户可显信息
     */
    @GetMapping("/index/{userId}")
    public R<UserVo> getUserMsg(@NotNull(message = "主键不能为空") @PathVariable Long userId){
        User one = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getId, userId));
        UserVo userVo = BeanUtil.copyProperties(one, UserVo.class);
        LambdaQueryWrapper<Participations> qw = new LambdaQueryWrapper<Participations>();
        // 状态3表示已经完成
        qw.eq(Participations::getParticipationId,userId)
                .eq(Participations::getStatus,3);
        participationsService.count(qw);
        log.info("user:{}",one);
        return R.ok(userVo);
    }
    /**
     * @Description:个人信息更新
     * @param updateInfoBody
     * @return: com.example.core.domain.R
     * @Author: kai
     * @Date 2024/4/23 21:09
     */
    @PutMapping("/update")
    public R updateUserInfo(@RequestBody UpdateInfoBody updateInfoBody){
        User user = new User((Long) StpUtil.getLoginId());
        BeanUtil.copyProperties(updateInfoBody, user);
        userService.updateById(user);
        return R.ok("更改成功");
    }

    /**
     * 用户查看自己发布的需求
     * @return eventvos 发布的需求
     */
    @GetMapping("/publishevents")
    public R<List<EventVo>> getpublisheventsByuser(@RequestParam Long userId){
        List<Event> events = eventService.list(new LambdaQueryWrapper<Event>()
                .eq(Event::getCreateBy, userId));
        List<EventVo> eventVos = events.stream()
                .map(event -> {
                    EventVo eventVo = new EventVo();
                    BeanUtil.copyProperties(event, eventVo);
                    return eventVo;
                })
                .toList();
        return R.ok(eventVos);
    }
    /**
     * @Description:查看发布的培训视频
     */
    @GetMapping("/getCourses")
    public R<List<Course>> getCourses(){
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Course::getCreateBy, StpUtil.getLoginId())
                .eq(Course::getDelFlag,0)
                .eq(Course::getStatus,1);
        return R.ok(courseService.list(queryWrapper));
    }
    /**
     * 举报
     * @return
     */
    @SaCheckLogin
    @PostMapping("/accusation/{userId}")
    public R<Boolean> accusationUser(@PathVariable Long userId,
                                     @RequestBody AccusationBody accusationBody){
        ValidatorUtils.validate(accusationBody);
        MultipartFile[] multipartFiles = accusationBody.getMultipartFiles();
        String reason = accusationBody.getReason();
        String content = accusationBody.getContent();
        try {
            // 上传所有文件并获取它们的URLs
            List<String> fileUrls = fileService.uploadMultipleFiles(multipartFiles);
            // 创建举报实例并设置其属性
            Accusation accusation = new Accusation();
            accusation.setContent(content);
            accusation.setReason(reason);
            accusation.setCreateBy(StpUtil.getLoginIdAsLong());
//            accusation.setImageUrls(String.join(",", fileUrls)); // 将文件URLs存储为逗号分隔的字符串
            // 保存举报信息到数据库
            boolean saveResult = accusationService.save(accusation);
            return saveResult ? R.ok(true) : R.fail("保存举报信息失败");
        } catch (OssException e) {
            return R.fail("上传文件失败：" + e.getMessage());
        }
    }
    /**
     * @Description:用户列表
     * @param
     * @return: com.example.core.domain.R<java.util.List<com.example.client.domain.vo.UserVo>>
     * @Author: kai
     * @Date 2024/4/23 21:08
     */
    @GetMapping("/list")
    public R<List<UserVo>> listUser(){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getStatus,0)
                .eq(User::getDelFlag,0)
                .orderByDesc(User::getUserName);
        List<User> users = userService.list(queryWrapper);
        List<UserVo> userVos = users.stream()
                .map(user -> {
                    UserVo userVo = new UserVo();
                    BeanUtil.copyProperties(user, userVo);
                    return userVo;
                })
                .toList();
        return R.ok(userVos);
    }
    /**
     * 用户查询
     */
    @GetMapping("/search")
    public R<User> searchUser(@RequestParam String userName){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getStatus,0)
                .eq(User::getDelFlag,0)
                .like(User::getUserName,userName);
        User user = userService.getOne(queryWrapper);
        return R.ok(user);
    }


}
