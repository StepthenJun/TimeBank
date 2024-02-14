package org.oss.util;
 
import com.aliyun.oss.OSS;
import lombok.Data;
import org.oss.config.AliyunOSSConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Data
public class OssUtil {
        //设置允许上传文件格式
        private static final String[] IMAGE_TYPE=new String[]{".bmp",".jpg",".jpeg",".png",".gif",".mp3",".mp4",".mkv"};
        @Autowired
        private OSS ossClient;
        @Autowired
        private AliyunOSSConfig aliyunOSSConfig;

        public String upload(MultipartFile file){
            String bucketNanme=aliyunOSSConfig.getBucketName();
            String endPoint = aliyunOSSConfig.getEndPoint();
            String accessKeyId = aliyunOSSConfig.getAccessKeyId();
            String accessKeySecret = aliyunOSSConfig.getAccessKeySecret();
            String fileHost = aliyunOSSConfig.getFileHost();
            //返回的Url
            String returnUrl="";
            //审核上传文件是否符合规定格式
            boolean isLegal=false;
            for (String type:IMAGE_TYPE){
                if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename(),type)){
                    isLegal=true;
                    break;
                }
            }
            if (!isLegal){
//            如果不正确返回错误状态码
                return null;
            }
            //获取文件的名称
            String originalFilename = file.getOriginalFilename();
            //截取文件类型
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
//        最终保存文件名称
            String newFileName= UUID.randomUUID().toString()+ fileType;
            //构建日期路径  ps ：oss目标文件夹/yyyy/MM/dd文件名称
            String filePath=new SimpleDateFormat("yyyy/MM/dd").format(new Date());
//        文件上传文件的路径
            String uploadUrl=fileHost+"/"+filePath+"/"+newFileName;
//        获取文件输入流
            InputStream inputStream=null;
            try{
                inputStream=file.getInputStream();

            }catch (IOException e){
                e.printStackTrace();
            }
            //文件上传到阿里云oss
//        ossClient.put
            ossClient.putObject(bucketNanme,uploadUrl,inputStream);//,meta
            returnUrl="http://"+bucketNanme+"."+endPoint+"/"+uploadUrl;
            return returnUrl;
        }
}