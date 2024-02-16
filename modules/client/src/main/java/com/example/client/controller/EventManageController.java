package com.example.client.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.client.domain.Event;
import com.example.client.service.EventService;
import com.example.core.domain.R;
import com.example.core.domain.model.AuditBody;
import com.example.core.enums.EventStatus;
import com.example.core.exception.UserException;
import com.example.core.util.ValidatorUtils;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@SaIgnore
@RequestMapping("/eventmanage")
public class EventManageController {

    @Autowired
    private EventService eventService;

    /**
     * @desciption: 返回所有需求列表 审核和未审核的一并交给前端分开展示
     * @return
     */
    @GetMapping("/eventlist")
    public R<List<Event>> getEventList(){
        List<Event> list = eventService.list();
        return R.ok(list);
    }


    /**
     * @description: 根据需求id对需求进行审核
     * @param auditBody
     * @return
     */
    // TODO 记得新增需求事件的异常类
    @PostMapping("/audit")
    public R<String> auditEvent(@Validated @RequestBody AuditBody auditBody) {
        ValidatorUtils.validate(auditBody);
        String auditManager = auditBody.getAuditManager();
        Long eventId = auditBody.getId();
        Boolean ifPass = auditBody.getIfPass();
        // 通过则直接改变状态
        if (ifPass) {
            try {
                eventService.update(new LambdaUpdateWrapper<Event>()
                        .eq(Event::getId, eventId)
                        .set(Event::getStatus, EventStatus.PASS.getCode()));
                return R.ok("操作成功");
            } catch (Exception e) {
                throw new UserException("更新错误");
            }
        }
        // 未通过则放入错误信息
        try {
            // 数据库还未设计出失败原因字段
            String failReason = auditBody.getFailReason();
            eventService.update(new LambdaUpdateWrapper<Event>()
                    .eq(Event::getId, eventId)
                    .set(Event::getStatus, EventStatus.UNPASS.getCode()));
            return R.ok("操作成功");
        }catch (Exception e) {
            throw new UserException("更新错误");
        }
    }
}
