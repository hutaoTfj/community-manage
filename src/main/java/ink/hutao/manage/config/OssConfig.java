package ink.hutao.manage.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * <p></p>
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

}
