package ink.hutao.manage.config;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * <p>OSS上传文件</p>
 * @author tfj
 * @since 2021/6/12
 */
@Configuration
@Data
public class OssConfig {
    @Value("${ali.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${ali.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${ali.oss.endpoint}")
    private String endpoint;

    @Value("${ali.oss.bucketName}")
    private String bucketName;

    @Value("${ali.oss.domain}")
    private String domain;

    public String uploadImage(MultipartFile file) {
        //创建连接实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        if (file != null) {
            //获取文件名
            String originalFilename = file.getOriginalFilename();
            PutObjectRequest putObjectRequest = null;
            try {
                //读文件
                putObjectRequest = new PutObjectRequest(bucketName, originalFilename, new ByteArrayInputStream(file.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //上传
            ossClient.putObject(putObjectRequest);
            //断开连接
            ossClient.shutdown();

            //获取上传图片的完整地址
            return domain + originalFilename;
        }
        return null;
    }

}
