package org.example.util;

import com.alibaba.fastjson.JSON;
import com.aliyun.teaopenapi.Client;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class AliyunSmsUtil {
    @Value("${aliyun.sms.sms-access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.sms.sms-access-key-secret}")
    private String accessKeySecret;

    @Value("${aliyun.sms.sms-sign-name}")
    private String signName;

    @Value("${aliyun.sms.sms-template-code}")
    private String templateCode;

    @Value("${aliyun.sms.sms-endpoint}")
    private String endpoint;
    public boolean sendMsg(String phoneNumber, HashMap<String, Object> templateParams) {
        try {
            // 按照官方文档来的
            Config config = new Config()
                    .setAccessKeyId(accessKeyId)
                    .setAccessKeySecret(accessKeySecret);
            config.endpoint = endpoint;
            com.aliyun.dysmsapi20170525.Client client = new com.aliyun.dysmsapi20170525.Client(config);
            com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest();
            sendSmsRequest.setSignName(signName);
            sendSmsRequest.setPhoneNumbers(phoneNumber);
            // 转成json符合官方给的规范
            sendSmsRequest.setTemplateCode(JSON.toJSONString(templateParams));
            sendSmsRequest.setTemplateCode(templateCode);
            RuntimeOptions runtime = new RuntimeOptions();
            client.sendSmsWithOptions(sendSmsRequest,runtime);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}

