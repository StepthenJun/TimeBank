package org.example.config;

import org.example.util.AliyunSmsUtil;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class SmsConfig {
    @Bean
    public AliyunSmsUtil aliyunSmsUtil(){
        return new AliyunSmsUtil();
    }
}
