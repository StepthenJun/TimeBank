package com.example;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.client.domain.Product;
import com.example.client.domain.User;
import com.example.client.domain.vo.ProductVo;
import com.example.client.service.ProductService;
import com.example.client.service.UserService;
import com.example.api.IdCardAuth;
import com.example.core.constant.Captcha;
import com.example.oss.util.AliyunOSSUtil;
import com.example.oss.util.FileService;
import com.example.redis.util.RedisUtils;
import com.example.sms.util.AliyunSmsUtil;


import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = AdminApplication.class)
@ComponentScan()
class AdminApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    void test(){
        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        String b = a.toString();
        productService.update(new LambdaUpdateWrapper<Product>()
            .eq(Product::getId,1)
            .set(Product::getImage,b));

        System.out.println(b);
    }

    @Test
    void one(){
        ProductVo one = productService.getById(1);
        System.out.println(one);
    }

    @Test
    void testString(){
        List<String> list = List.of("1", "2");
        String join = String.join(",", list);
        System.out.println(join);
    }


}
