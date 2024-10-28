package client.controller;

import client.model.dto.RegisterDto;
import com.example.core.domain.R;
import com.example.core.domain.pojos.Academy;
import com.example.core.domain.pojos.School;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: TimeBank
 * @ClassName: LoginController
 * @description:
 * @author: kai
 * @create: 2024-10-25 20:12
 */
@RestController
@RequestMapping("/client")
public class LoginController {
    @PostMapping("/register")
    public R register(@RequestBody RegisterDto registerDto){
        return R.ok();
    }
    //todo
    @PostMapping("/login")
    public R login(){
        return R.ok();
    }
    @GetMapping("/schools")
    public R<List<School>> getSchoolList(){
        return R.ok(new ArrayList<>());
    }
    @GetMapping("/academies")
    public R<List<Academy>> getAcademyList(){
        return R.ok(new ArrayList<Academy>());
    }


}
