package ink.hutao.manage.service.impl;

import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.config.SmsConfig;
import ink.hutao.manage.config.WxConfig;
import ink.hutao.manage.entity.dto.PostUserDto;
import ink.hutao.manage.entity.po.CustomUserState;
import ink.hutao.manage.entity.po.Owner;
import ink.hutao.manage.mapper.OwnerMapper;
import ink.hutao.manage.service.OwnerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tfj
 * @since 2021-06-20
 */
@Service
public class OwnerServiceImpl extends ServiceImpl<OwnerMapper, Owner> implements OwnerService {
    @Resource
    private  WxConfig wxConfig;
    @Resource
    private  OwnerMapper ownerMapper;
    @Resource
    private SmsConfig smsConfig;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * <p>获取微信登录凭证</p>
     * @author tfj
     * @since 2021/6/28
     */
    @Override
    public Result getOpenId(String code, String path) {
        Owner owner=new Owner();
        CustomUserState responseEntity = wxConfig.getResponseEntity(code);
        owner.setId(wxConfig.getId());
        owner.setOpenId(responseEntity.getOpenid());
        owner.setCreateTime(new Date());
        Owner selectOne = ownerMapper.selectOne(new QueryWrapper<Owner>().eq("openId", owner.getOpenId()));
        if (selectOne==null){
            ownerMapper.insert(owner);
        }
        return new Result().result200(responseEntity,path);
    }
    /**
     * <p>获取微信用户信息</p>
     * @author tfj
     * @since 2021/6/28
     */
    @Override
    public Result getUserInfo(PostUserDto postUserDto, String path) {
        Owner owner = ownerMapper.selectOne(new QueryWrapper<Owner>().eq("openId", postUserDto.getOpenId()));
        owner.setGender(postUserDto.getGender());
        owner.setCity(postUserDto.getCity());
        owner.setProvince(postUserDto.getProvince());
        owner.setCountry(postUserDto.getCountry());
        owner.setNickName(postUserDto.getNickName());
        owner.setImageUrl(postUserDto.getImageUrl());
        if (ownerMapper.updateById(owner)==1){
            return new Result().result200("获取微信用户信息成功",path);
        }
        return new Result().result500("获取失败",path);
    }
    /**
     * <p>发送手机验证码</p>
     * @author tfj
     * @since 2021/6/29
     */
    @Override
    public Result sendSmsCode(String phoneNumber,String openId, String path) throws ClientException {
        CommonResponse commonResponse = smsConfig.generateSmsRequest(phoneNumber);
        if (commonResponse!=null){
            redisTemplate.opsForValue().set(openId,smsConfig.getCode(),5, TimeUnit.SECONDS);
            return new Result().result200("获取手机验证码成功,五分钟内有效",path);
        }
        return new Result().result500("获取验证码失败",path);
    }
    /**
     * <p>输入验证码验证</p>
     * @author tfj
     * @since 2021/6/29
     */
    @Override
    public Result judgeCode(int code,String openId, String path) {
        int num = (int)redisTemplate.opsForValue().get(openId);
        if (num==code){
            return new Result().result200("验证成功",path);
        }
        return new Result().result500("验证码不正确或者已过期",path);
    }
}
