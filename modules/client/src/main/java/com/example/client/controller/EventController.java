package com.example.client.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.client.domain.Event;
import com.example.client.domain.vo.EventVo;
import com.example.client.service.EventService;
import com.example.core.domain.R;
import com.example.core.domain.model.PulishEventBody;
import com.example.core.util.ValidatorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/event")
@SaIgnore
public class EventController {

    @Autowired
    private EventService eventService;


    /**
     * 返回需求列表，应该是放在主页访问
     * @return
     */
    @GetMapping("/index")
    public R<List<EventVo>> eventlist(){
        List<Event> events = eventService.list(new LambdaQueryWrapper<Event>()
                .eq(Event::getStatus, "3")
                .eq(Event::getDelFlag, "0")
        );
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
     * 根据eventId查看需求的详细信息
     * @return
     */
    @GetMapping("/detail")
    public R<EventVo> getEventDetail(@RequestParam Long eventId){
        Event one = eventService.getOne(new LambdaQueryWrapper<Event>()
                .eq(Event::getId, eventId));
        EventVo eventVo = BeanUtil.copyProperties(one, EventVo.class);
        return R.ok(eventVo);
    }

    /**
     *上传需求（应该要看是什么角色然后才能上传）
     * @return String提示信息
     */
    @PostMapping("/publish")
    public R<String> publish(@Validated @RequestBody PulishEventBody pulishEventBody) {
        try {
            ValidatorUtils.validate(pulishEventBody);
            Event event = BeanUtil.copyProperties(pulishEventBody, Event.class);
            eventService.save(event);
            return R.ok("上传成功");
        }catch (Exception e) {
            log.info(e.getMessage());
            return R.fail("上传失败: 系统异常");
        }
    }
}
