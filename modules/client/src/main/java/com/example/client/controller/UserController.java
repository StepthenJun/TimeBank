package com.example.client.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.example.client.domain.WalletTransaction;
import com.example.client.domain.dto.ProductDto;
import com.example.client.domain.dto.UserDto;
import com.example.client.domain.vo.ProductVo;
import com.example.client.service.ProductService;
import com.example.client.service.UserService;
import com.example.client.service.WalletTransactionService;
import com.example.core.domain.R;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description:
 * @author：StephenJun
 * @date: 2024/11/27
 * @email: 2398627868@qq.com
 */

@Slf4j
@SaIgnore
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/user/")
public class UserController {
  private final UserService userService;
  private final ProductService productService;
  private final WalletTransactionService transactionService;

  // http://196.134.42.161:8080/user/info
  @GetMapping("info")
  public R<UserDto> info(){
    UserDto info = userService.info();
    return R.ok(info);
  }

  @PostMapping("upload_avater")
  public void uploadAvater(MultipartFile file){
    userService.uploadAvater(file);
  }

  @PostMapping("publish")
  public R<Boolean> publish(@RequestBody ProductDto productDto){
    Boolean publish = productService.publish(productDto);
    return R.ok(publish);
  }

  @GetMapping("my_publish")
  public R<List<ProductVo>> getMyPublish(){
    return userService.getMyPublish();
  }

  @GetMapping("/list")
  public R<List<WalletTransaction>> getTransactions() {
    Integer userId = StpUtil.getLoginIdAsInt();
    List<WalletTransaction> transactions = transactionService.getTransactions(userId);
    return R.ok(transactions);
  }

}
