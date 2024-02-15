package com.example.client.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.client.domain.Event;
import com.example.client.domain.vo.EventVo;
import com.example.client.service.EventService;
import com.example.core.domain.R;
import com.example.core.util.ValidatorUtils;
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
@RequestMapping("/require")
public class EventController {

    @Autowired
    private EventService eventService;


    @SaIgnore
    @GetMapping("/index")
    public R<List<Event>> eventlist(){
        List<Event> list = eventService.list(new LambdaQueryWrapper<Event>()
                .eq(Event::getStatus, "3")
                .eq(Event::getDelFlag, "0")
        );
        return R.ok(list);
    }

    @SaIgnore
    @GetMapping("eventdetail")
    public R<Event> getEventDetail(Long eventId){
        Event one = eventService.getOne(new LambdaQueryWrapper<Event>()
                .eq(Event::getId, eventId));
        return R.ok(one);
    }

    @PostMapping("/publish")
    public R<String> publish(@Validated @RequestBody EventVo eventVo) {
        try {
            ValidatorUtils.validate(eventVo, EventVo.class);
            Event event = BeanUtil.copyProperties(eventVo, Event.class);
            eventService.save(event);
            return R.ok("上传成功");
        }catch (Exception e) {
            return R.fail("上传失败: 系统异常");
        }
    }
}
