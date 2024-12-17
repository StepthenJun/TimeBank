package com.example.client.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/product/")
public class ProductController {

}
