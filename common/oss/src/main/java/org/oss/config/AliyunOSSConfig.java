package org.oss.config;
 
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
@Configuration
@Data
public class AliyunOSSConfig {
    private String endPoint="xxxxxxxx";// 地域节点
    private String accessKeyId="xxxxxxxxx";
    private String accessKeySecret="xxxxxxxxxxxxxx";
    private String bucketName="demotest-dfp";// OSS的Bucket名称
    private String urlPrefix="demotest-dfp.oss-cn-shenzhen.aliyuncs.com";// Bucket 域名
    private String fileHost="fileTest";// 目标文件夹
    @Bean
    public OSS OSSClient(){
        return new OSSClient(endPoint,accessKeyId,accessKeySecret);
    }
}