package com.example.controller;

import com.example.core.domain.R;
import com.example.core.domain.pojos.Grades;
import com.example.core.domain.pojos.Majors;
import com.example.core.domain.pojos.Notification;
import com.example.core.domain.pojos.NotificationTypes;
import com.example.domain.NotificationDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: TimeBank
 * @ClassName: NotificationController
 * @description:
 * @author: kai
 * @create: 2024-10-26 23:39
 */
@RestController
@RequestMapping("/admin/notify")
public class NotificationController {
    @GetMapping("/showRelease")
    public R<List<Notification>> showNotifications(){
        return R.ok(new ArrayList<>());
    }
    @GetMapping("/showDraft")
    public R<List<Notification>> showDraft(){
        return R.ok(new ArrayList<>());
    }
    @PostMapping("/write")
    public R writeNotification(@RequestBody NotificationDto notificationDto){
        return R.ok();
    }
    @GetMapping("/getTypes")
    public R<List<NotificationTypes>> showTypes(){
        return R.ok(new ArrayList<>());
    }
    @GetMapping("/getGrades")
    public R<List<Grades>> getGrades(){
        return R.ok(new ArrayList<>());
    }
    @GetMapping("/getMajors")
    public R<List<Majors>> getMajors(@RequestParam Long gradeId){
        return R.ok(new ArrayList<>());
    }
    @GetMapping("/getClasses")
    public R<List<Class>> getClasses(@RequestParam Long majorId){
        return R.ok(new ArrayList<>());
    }

}
