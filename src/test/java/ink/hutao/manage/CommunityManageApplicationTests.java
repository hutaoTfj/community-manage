package ink.hutao.manage;

import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import com.xiaoTools.core.randomUtil.RandomUtil;
import ink.hutao.manage.config.OsrConfig;
import ink.hutao.manage.config.SmsConfig;
import ink.hutao.manage.config.WxConfig;
import ink.hutao.manage.entity.po.Plates;
import ink.hutao.manage.mapper.AdminMapper;
import ink.hutao.manage.mapper.OwnerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@SpringBootTest
class CommunityManageApplicationTests {
    @Autowired
    private WxConfig wxConfig;
    @Resource
    private SmsConfig smsConfig;
    @Resource
    private OwnerMapper ownerMapper;
    @Resource
    private SimpleMailMessage simpleMailMessage;
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private OsrConfig osrConfig;
    @Test
    void contextLoads() {
    }

    @Test
    public void createSnowFlakeId(){
        Long id = wxConfig.getId();
        System.out.println(id);
    }

    @Test
    public void getSmsCode() throws ClientException {
        String model="SMS_218890834";
        CommonResponse commonRequest = smsConfig.generateSmsRequest("19941395047",model);
        System.out.println(commonRequest.getData());
        System.out.println(commonRequest.getHttpStatus());
        System.out.println(commonRequest.getHttpResponse());
    }

    @Test
    public void testRedis(){
//        try {
//        //连接本地Redis服务
//        RedisProperties.Jedis jedis = new RedisProperties.Jedis("47.118.66.86", 6379);
//        jedis.auth("27599");
//            String ping = jedis.ping();
//            if (ping.equalsIgnoreCase("PONG")) {
//                System.out.println("redis缓存有效！" + ping);
//            }
//        } catch (Exception e) {
//            System.out.println("redis缓存失败！");
//        }
    }

    @Test
    public void testRole(){
        String empRole = ownerMapper.getEmpRole(612453973785722880L);
        System.out.println(empRole);
    }

    @Test
    public void sendMail(){
        String s = RandomUtil.randomNumber(4);
        simpleMailMessage.setTo("489516067@qq.com");
        simpleMailMessage.setText(s);
        javaMailSender.send(simpleMailMessage);
    }

    @Test
    public void random(){
        String s = RandomUtil.randomNumber(4);
        System.out.println(s);
    }

    @Test
    public void testPutCipher(){
        String md5Cipher = DigestUtils.md5DigestAsHex("111".getBytes());
        boolean b = adminMapper.putAdminCipher("489516067@qq.com", md5Cipher);
        System.out.println(b);
    }

    @Test
    public void carNumber() throws Exception {
        Plates carDto = osrConfig.getPlants("http://hutao-car.oss-cn-shanghai.aliyuncs.com/P10714-123841 (1).jpg");
        System.out.println(carDto);
    }
}
