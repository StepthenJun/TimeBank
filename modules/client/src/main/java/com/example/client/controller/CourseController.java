package com.example.client.controller;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.api.FabricApi;
import com.example.api.domain.UpdateAssetDto;
import com.example.client.domain.Course;
import com.example.client.service.CourseService;
import com.example.core.domain.R;
import lombok.AllArgsConstructor;
import org.hyperledger.fabric.gateway.ContractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.concurrent.TimeoutException;
/**
 * @program: TimeBank
 * @ClassName: CourseController
 * @description: 培训视频相关接口
 * @author: kai
 * @create: 2024-04-22 22:02
 */
@SaIgnore
@RestController
@AllArgsConstructor
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private FabricApi fabricApi;
    /**
     * @Description:培训视频购买
     * @param courseId
     * @return: com.example.core.domain.R
     * @Author: kai
     * @Date 2024/4/23 21:12
     */
    @GetMapping("/buy")
    public R buyCourse(@RequestParam Long courseId) throws ContractException, InterruptedException, TimeoutException {
        Integer price=courseService.getById(courseId).getPrice();
        Integer currentPrice=fabricApi.queryAsset((Long) StpUtil.getLoginId()).getAppraisedValue();
        if(currentPrice<price)
            return R.fail("资金不足");
        fabricApi.updateAsset(new UpdateAssetDto(-price, (String)StpUtil.getLoginId(),"0"));
        return R.ok("交易成功");
    }
    /**
     * @Description:查询指定培训资料
     * @param courseName
     * @return: com.example.core.domain.R<java.util.List<com.example.client.domain.Course>>
     * @Author: kai
     * @Date 2024/4/23 21:11
     */
    @GetMapping("/search")
    public R<List<Course>> searchCourse(@RequestParam String courseName)  {
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Course::getName, courseName)
                .eq(Course::getStatus, 1)
                .eq(Course::getDelFlag,0);
        return R.ok(courseService.list(queryWrapper));
    }


}
