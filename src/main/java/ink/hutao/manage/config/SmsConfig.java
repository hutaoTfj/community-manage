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

    /**
     * <p>发送随机数验证码</p>
     * @author tfj
     * @since 2021/7/15
     */
    public CommonResponse generateSmsRequest(String phone,String model) throws ClientException {
        //创建配置文件实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,accessKeySecret);
        //创建连接加载配置文件
        IAcsClient client = new DefaultAcsClient(profile);
        //生成随机验证码
        code = RandomUtil.randomNumber(4);
        //创建API请求并设置参数
        CommonRequest request=new CommonRequest();
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.setSysDomain(domain);
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers",phone );
        request.putQueryParameter("SignName",signName);
        request.putQueryParameter("TemplateCode", model);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        //获取返回值
        CommonResponse response = client.getCommonResponse(request);
        log.info(response.getData());
        return response;
    }
    /**
     * <p>发送短信验证码</p>
     * @author tfj
     * @since 2021/7/15
     */
    public CommonResponse sendSmsMessage(String phone,String model) throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        //创建API请求并设置参数
        CommonRequest request=new CommonRequest();
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
