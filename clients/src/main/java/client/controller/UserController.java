package client.controller;

import client.model.vo.UserDetailVo;
import client.model.vo.UserVo;
import com.example.core.domain.R;
import com.example.core.domain.pojos.Notification;
import com.example.core.domain.pojos.NotificationTypes;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: TimeBank
 * @ClassName: UserController
 * @description:
 * @author: kai
 * @create: 2024-10-25 20:09
 */
@RestController
@RequestMapping("/client/user")
public class UserController {
    @GetMapping("/myInfo")
    public R<UserVo> getMyInfo(){
        return R.ok(new UserVo());
    }
    @PutMapping("/updateInfo")
    public R updateInfo(@RequestBody UserVo userVo){
        return R.ok();
    }
    @GetMapping("/getUserInfo")
    public R<UserVo> getUserInfo(@RequestParam Long userId){
        return R.ok(new UserVo());
    }
    @PostMapping("/follow")
    public R followUser(@RequestParam Long userId){
        return R.ok();
    }
    @PostMapping("/isAccept")
    public R acceptOrRejectUser(@RequestParam Integer isAccept){
        return R.ok();
    }
    @GetMapping("/showFriends")
    public R<List<UserDetailVo>> showFriends(){
        return R.ok(new ArrayList<UserDetailVo>());
    }
    @GetMapping("/getNotificationTypes")
    public R<List<NotificationTypes>> getNotiTypes(){
        return  R.ok(new ArrayList<>());
    }
    @GetMapping("/getNotifications")
    public R<List<Notification>> getNotification(@RequestParam Long typeId){
        return R.ok(new ArrayList<>());
    }

    //TODO CHAT

}
