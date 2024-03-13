package com.example.oss.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.oss.exception.OssException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.bucketName}")
    private String bucketName;

    public String uploadFileAvatar(MultipartFile file) {
        // 生成随机文件名
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + extension;

        // 创建OSS客户端
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 上传文件到OSS
            ossClient.putObject(bucketName, fileName, file.getInputStream());

            // 返回文件访问路径
            return "https://" + bucketName + "." + endpoint + "/" + fileName;
        } catch (IOException e) {
            throw new OssException("上传失败");
        } finally {
            // 关闭OSS客户端
            ossClient.shutdown();
        }
    }

    public List<String> uploadMultipleFiles(MultipartFile[] files) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        List<String> fileUrls = new ArrayList<>();

        try {
            for (MultipartFile file : files) {
                if (file != null && !file.isEmpty()) {
                    String originalFilename = file.getOriginalFilename();
                    String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                    String fileName = UUID.randomUUID() + extension;
                    ossClient.putObject(bucketName, fileName, file.getInputStream());
                    fileUrls.add("https://" + bucketName + "." + endpoint + "/" + fileName);
                }
            }
        } catch (IOException e) {
            throw new OssException("文件上传失败");
        } finally {
            ossClient.shutdown();
        }
        return fileUrls;
    }
}
