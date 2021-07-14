package ink.hutao.manage.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoTools.core.randomUtil.RandomUtil;
import com.xiaoTools.core.regular.validation.Validation;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.config.SmsConfig;
import ink.hutao.manage.entity.po.Admin;
import ink.hutao.manage.entity.vo.GetAllOwnerRealInfoVo;
import ink.hutao.manage.mapper.AdminMapper;
import ink.hutao.manage.service.AdminService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/13
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private RedisTemplate<Object,Object> redisTemplate;
    @Resource
    private SmsConfig smsConfig;
    @Resource
    private SimpleMailMessage simpleMailMessage;
    @Resource
    private JavaMailSender javaMailSender;
    @Override
    public Result adminLogin(String account, String cipher, String path) throws ClientException {
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("account", account));
        if (admin==null){
            return new Result().result500("账号不存在",path);
        }
        if (Validation.isEmail(account)){
            String code = RandomUtil.randomNumber(4);
            simpleMailMessage.setTo(account);
            simpleMailMessage.setText("邮箱验证码："+code);
            redisTemplate.opsForValue().set(account,code,5,TimeUnit.MINUTES);
            javaMailSender.send(simpleMailMessage);
        }
        if (Validation.isMobile(account)){
            String model="SMS_218034906";
            smsConfig.generateSmsRequest(account, model);
            redisTemplate.opsForValue().set(account,smsConfig.getCode(),5, TimeUnit.MINUTES);
        }
        String md5Cipher = DigestUtils.md5DigestAsHex(cipher.getBytes());
        if (!md5Cipher.equals(adminMapper.judgeCipher(account))){
            return new Result().result500("密码错误",path);
        }
        StpUtil.setLoginId(account);
        Map<String, Object> objectMap=new HashMap<>(2);
        objectMap.put("info","账号密码正确");
        objectMap.put("saToken",StpUtil.getTokenValue());
        return new Result().result200(objectMap,path);
    }
    /**
     * <p>管理员登录验证码验证</p>
     * @author tfj
     * @since 2021/7/13
     */
    @Override
    public Result judgeAdminLogin(String account,String code, String path) {
        String a =(String)redisTemplate.opsForValue().get(account);
        assert a != null;
        if (a.equals(code)){
            return new Result().result200("验证成功",path);
        }
        return new Result().result500("验证码不正确或者已过期",path);
    }

    /**
     * <p>退出登录</p>
     * @author tfj
     * @since 2021/7/13
     */
    @Override
    public Result exitLogin(String path) {
        StpUtil.logout();
        return new Result().result200("退出登录成功",path);
    }
    /**
     * <p>修改管理员密码</p>
     * @author tfj
     * @since 2021/7/13
     */
    @Override
    public Result putAdminCipher(String account, String newCipher, String oldCipher, String path) {
        String dbCipher=adminMapper.queryCipher(account);
        if (dbCipher.equals(oldCipher)){
            return new Result().result500("密码与原密码相同,无法修改",path);
        }
        String md5Cipher = DigestUtils.md5DigestAsHex(newCipher.getBytes());
        if (adminMapper.putAdminCipher(account,md5Cipher)){
            return new Result().result200("修改成功",path);
        }
        return new Result().result500("修改失败",path);
    }

    /**
     * <p>获取所有业主真实信息</p>
     * @author tfj
     * @since 2021/7/14
     */
    @Override
    public Result getAllOwnerRealInfo(String path) {
        List<GetAllOwnerRealInfoVo> ownerRealInfoVos= adminMapper.getAllOwnerRealInfo();
        return new Result().result200(ownerRealInfoVos,path);
    }
}
