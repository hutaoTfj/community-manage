package ink.hutao.manage.config;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.xiaoTools.core.randomUtil.RandomUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * <p>阿里云短信验证码配置</p>
 * @author tfj
 * @since 2021/6/28
 */
@Configuration
@Data
@Slf4j
public class SmsConfig {
    @Value("${ali.sms.AccessKeyId}")
    private String accessKeyId;

    @Value("${ali.sms.AccessKeySecret}")
    private String accessKeySecret;

    @Value("${ali.sms.domain}")
    private String domain;

    @Value("${ali.sms.SignName}")
    private String signName;

    public String code ;

    public CommonResponse generateSmsRequest(String phone,String model) throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        code = RandomUtil.randomNumber(4);
        CommonRequest request = new CommonRequest();
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.setSysDomain(domain);
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers",phone );
        request.putQueryParameter("SignName",signName);
        request.putQueryParameter("TemplateCode", model);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        CommonResponse response = client.getCommonResponse(request);
        log.info(response.getData());
        return response;
    }

    public CommonResponse sendSmsMessage(String phone,String model) throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.setSysDomain(domain);
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers",phone );
        request.putQueryParameter("SignName",signName);
        request.putQueryParameter("TemplateCode", model);
        CommonResponse response = client.getCommonResponse(request);
        log.info(response.getData());
        return response;
    }
}
