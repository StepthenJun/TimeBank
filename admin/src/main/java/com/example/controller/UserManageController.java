package com.example.controller;
import com.example.core.domain.R;
import com.example.core.domain.vo.UserDetailVo;
import com.example.domain.UpdateUserDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: TimeBank
 * @ClassName: UserManageController
 * @description:
 * @author: kai
 * @create: 2024-10-26 23:50
 */
@RestController
@RequestMapping("/admin/user")
public class UserManageController {
    @GetMapping("/search")
    public R<List<UserDetailVo>> searchUsers(@RequestParam String content){
        return R.ok(new ArrayList<>());
    }
    @GetMapping("/showOnline")
    public R<List<UserDetailVo>> showOnline(){
        return R.ok(new ArrayList<>());
    }
    @PutMapping("/updateUser")
    public R updateUser(@RequestBody UpdateUserDto updateUserDto){
        return R.ok();
    }


}
