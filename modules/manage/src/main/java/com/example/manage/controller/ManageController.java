package com.example.manage.controller;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.client.annotation.Notify;
import com.example.client.domain.*;
import com.example.client.domain.vo.UserVo;
import com.example.client.service.*;
import com.example.core.domain.R;
import com.example.core.domain.model.audit.ApprovalBody;
import com.example.core.domain.model.event.AuditBody;
import com.example.core.enums.EventStatus;
import com.example.core.exception.EventException;
import com.example.core.util.ValidatorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@SaIgnore
@RequestMapping("/manage")
// TODO 权限管理
public class ManageController {
    private final EventService eventService;
    private final ParticipationsService participationsService;
    private final AccusationService accusationService;
    private final CourseService courseService;
    private final UserService userService;
    /**
     * 返回所有需求列表 审核和未审核的一并交给前端分开展示
     * @return 审核的列表
     */
    @GetMapping("/eventList")
    public R<IPage<Event>> getEventList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize,@RequestParam String content){
        Page<Event> page = new Page<>(pageNum,pageSize);
        IPage<Event> eventPage = eventService.page(page, new LambdaQueryWrapper<Event>()
                .eq(!content.isBlank(),Event::getName,content)
                .orderByAsc(Event::getStatus)
                .eq(Event::getDelFlag,0)
        );
        return R.ok(eventPage);
    }
    /**
     * @Description:培训审核列表
     */
    @GetMapping("/courseList")
    public R<IPage<Course>> getCourseList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize,@RequestParam String content){
        Page<Course> page = new Page<>(pageNum,pageSize);
        IPage<Course> coursePage = courseService.page(page, new LambdaQueryWrapper<Course>()
                .eq(!content.isBlank(),Course::getName,content)
                .orderByAsc(Course::getStatus)
                .eq(Course::getDelFlag,0)
        );
        return R.ok(coursePage);
    }
    /**
     * @Description:举报审核列表
     */
    @GetMapping("/accusationList")
    public R<IPage<Accusation>> getAccusationList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize,@RequestParam String content){
        Page<Accusation> page = new Page<Accusation>(pageNum,pageSize);
        Long userId=null;
        if(!content.isBlank()) {
             userId = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUserName, content)).getId();
        }
        IPage<Accusation> accusationPage = accusationService.page(page, new LambdaQueryWrapper<Accusation>()
                .eq(!Objects.isNull(userId),Accusation::getCreateBy,userId)
                .orderByAsc(Accusation::getStatus)
        );
        return R.ok(accusationPage);
    }
    /**
     * @Description:后台查询用户
     */
    @GetMapping("/userList")
    public R<List<UserVo>> getUserList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize,@RequestParam String content){
        Page<User> page = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>()
                .eq(!content.isBlank(), User::getUserName, content)
                .orderByAsc(User::getStatus)
                .eq(User::getDelFlag, 0);
         userService.page(page, queryWrapper);
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
     * @Description:账号状态更新
     * @param approvalBody
     */
    @GetMapping("/userUpdate")
    public R updateUser(@RequestBody  ApprovalBody approvalBody){
        List<Long> idList = approvalBody.getIdList();
        for (Long id : idList) {
            LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(User::getId,id)
                    .set(User::getStatus,approvalBody.getStatus());
        }
      return R.ok("更新用户状态成功");
    }
    /**
     *  根据需求id对需求进行审核
     * @return 提示信息就好了
     */
    @PostMapping("/auditEvent")
    @Notify(type = "需求")
    public R<String> auditEvent(@Validated @RequestBody AuditBody auditBody) {
        ValidatorUtils.validate(auditBody);
        List<Long> eventIds = auditBody.getIdList();
        Integer status = auditBody.getStatus();
        // 通过则直接改变状态
        if (String.valueOf(status).equals(EventStatus.PASS.getCode())) {
            try {
                for (Long eventId : eventIds) {
                    eventService.update(new LambdaUpdateWrapper<Event>()
                            .eq(Event::getId, eventId)
                            .set(Event::getStatus, EventStatus.PASS.getCode()));
                    return R.ok("操作成功");
                }
            } catch (Exception e) {
                throw new EventException("更新错误");
            }
        }
        // 未通过则放入错误信息
        try {
            String failReason = auditBody.getFailReason();
            for (Long eventId : eventIds) {
                eventService.update(new LambdaUpdateWrapper<Event>()
                        .eq(Event::getId, eventId)
                        .set(Event::getStatus, EventStatus.UNPASS.getCode())
                        .set(Event::getDefaultReason,failReason));
            }
            return R.ok("操作成功");
        }catch (Exception e) {
            throw new EventException("更新错误");
        }
    }
    /**
     * 对举报信息进行审核
     */

    @PostMapping("/auditAccusation")
    @Notify
    public R<String> auditAccusation(@RequestBody ApprovalBody approvalBody){
        LambdaUpdateWrapper<Accusation> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(Accusation::getId, approvalBody.getIdList())
                .set(Accusation::getStatus, approvalBody.getStatus());
         accusationService.update(updateWrapper);
            return R.ok("更新成功");
    }
    @PostMapping("/auditCourse")
    @Notify
    public R<String> auditCourse(@RequestBody ApprovalBody approvalBody){
        LambdaUpdateWrapper<Course> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(Course::getId, approvalBody.getIdList())
                .set(Course::getStatus, approvalBody.getStatus());
        courseService.update(updateWrapper);
            return R.ok("更新成功");

    }

    /**
     * 查看参加的人
     * @return 返回申请者的列表
     */
    @GetMapping("/participationsByEvent")
    public R<List<Participations>> getparticipationByeventId(@RequestParam Long eventid){
        participationsService.list(new LambdaQueryWrapper<Participations>()
                .eq(Participations::getEventId,eventid));
        return null;
    }
    /**
     * 对参与者进行审核
     * @return 提示信息
     */
    @PostMapping("/auditParticipation")
    public R<String> auditParticipation(@RequestBody ApprovalBody approvalBody){
        LambdaUpdateWrapper<Participations> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(Participations::getId, approvalBody.getIdList())
                .set(Participations::getStatus, approvalBody.getStatus());
        boolean updateResult = participationsService.update(updateWrapper);
        if (updateResult) {
            return R.ok("更新成功");
        } else {
            return R.fail("更新失败");
        }
    }
}
