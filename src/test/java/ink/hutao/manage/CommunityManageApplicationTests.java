package ink.hutao.manage;

import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import ink.hutao.manage.config.SmsConfig;
import ink.hutao.manage.config.WxConfig;
import ink.hutao.manage.entity.po.Owner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

@SpringBootTest
class CommunityManageApplicationTests {
    @Autowired
    private WxConfig wxConfig;
    @Resource
    private SmsConfig smsConfig;
    @Test
    void contextLoads() {
    }

    @Test
    public void createSnowFlakeId(){
        Owner owner=new Owner();
        owner.setId(wxConfig.getId());
        System.out.println(owner.getId());
    }

    @Test
    public void getSmsCode() throws ClientException {
        CommonResponse commonRequest = smsConfig.generateSmsRequest("19941395047");
        System.out.println(commonRequest.getData());
        System.out.println(commonRequest.getHttpStatus());
        System.out.println(commonRequest.getHttpResponse());
    }
}
