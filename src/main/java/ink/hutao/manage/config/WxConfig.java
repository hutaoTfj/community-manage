package ink.hutao.manage.config;

import com.google.gson.Gson;
import com.xiaoTools.core.IdUtil.IdUtil;
import com.xiaoTools.core.IdUtil.snowflake.Snowflake;
import ink.hutao.manage.entity.po.CustomUserState;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * <p></p>
 * @author tfj
 * @since 2021/6/10
 */
@Configuration
public class WxConfig {

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.AppSecret}")
    private String appSecret;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private Gson gson;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public CustomUserState getResponseEntity(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String body = responseEntity.getBody();
        return gson.fromJson(body, CustomUserState.class);
    }
    @Bean
    public Long getId(){
        Snowflake snowflakeId = IdUtil.createSnowflakeId(3, 4);
        return snowflakeId.nextId();
    }
}
