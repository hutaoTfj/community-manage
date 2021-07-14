package ink.hutao.manage.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.config.OssConfig;
import ink.hutao.manage.config.SmsConfig;
import ink.hutao.manage.config.WxConfig;
import ink.hutao.manage.entity.dto.PostReal;
import ink.hutao.manage.entity.dto.PostUserDto;
import ink.hutao.manage.entity.po.CustomUserState;
import ink.hutao.manage.entity.po.Owner;
import ink.hutao.manage.entity.po.Real;
import ink.hutao.manage.entity.vo.GetCircleVo;
import ink.hutao.manage.entity.vo.GetOwnerRealVo;
import ink.hutao.manage.entity.vo.ReplyCircleVo;
import ink.hutao.manage.mapper.OwnerMapper;
import ink.hutao.manage.mapper.RealMapper;
import ink.hutao.manage.service.CircleService;
import ink.hutao.manage.service.OwnerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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
    private OssConfig ossConfig;
    @Resource
    private RealMapper realMapper;
    @Resource
    private CircleService circleService;
    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

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
        StpUtil.setLoginId(owner.getId());
        if (ownerMapper.updateById(owner)==1){
            return new Result().result200(owner.getId(),path);
        }
        return new Result().result500("获取失败",path);
    }
    /**
     * <p>发送手机验证码</p>
     * @author tfj
     * @since 2021/6/29
     */
    @Override
    public Result sendSmsCode(String phoneNumber,Long ownerId, String path) throws ClientException {
        Real real = realMapper.selectOne(new QueryWrapper<Real>().eq("ownerId",ownerId));
        if (real!=null){
            return new Result().result200("已经绑定过信息",path);
        }
        String model="SMS_218034906";
        CommonResponse commonResponse = smsConfig.generateSmsRequest(phoneNumber,model);
        Real addReal=new Real();
        addReal.setId(wxConfig.getId());
        addReal.setOwnerId(ownerId);
        addReal.setPhoneNumber(phoneNumber);
        addReal.setCreateTime(new Date());
        realMapper.insert(addReal);
        if (commonResponse!=null){
            redisTemplate.opsForValue().set(ownerId,smsConfig.getCode(),5, TimeUnit.MINUTES);
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
    public Result judgeCode(String code,Long ownerId, String path) {
        String a =(String) redisTemplate. opsForValue().get(ownerId);
        assert a != null;
        if (a.equals(code)){
            return new Result().result200("验证成功",path);
        }
        return new Result().result500("验证码不正确或者已过期",path);
    }
    /**
     * <p>绑定用户信息</p>
     * @author tfj
     * @since 2021/6/29
     */
    @Override
    public Result bindOwnerInfo(PostReal postReal,Long ownerId, String path) {
        Real insertReal = realMapper.selectOne(new QueryWrapper<Real>().eq("ownerId", ownerId));
        if (insertReal!=null){
            insertReal.setRealName(postReal.getRealName());
            insertReal.setAddress(postReal.getAddress());
            realMapper.updateById(insertReal);
            return new Result().result200("绑定用户信息成功",path);
        }
        return new Result().result500("绑定用户信息失败",path);
    }
    /**
     * <p>获取业主真实信息和住址</p>
     * @author tfj
     * @since 2021/6/30
     */
    @Override
    public Result getOwnerRealInfo(Long ownerId, String path) {
        GetOwnerRealVo ownerRealInfo = ownerMapper.getOwnerRealInfo(ownerId);
        return new Result().result200(ownerRealInfo,path);
    }
    /**
     * <p>上传图片接口</p>
     * @author tfj
     * @since 2021/7/2
     */
    @Override
    public String upLoadImages(MultipartFile file) {
        return ossConfig.uploadImage(file);
    }
    /**
     * <p>业主修改绑定信息发送验证码</p>
     * @author tfj
     * @since 2021/7/4
     */
    @Override
    public Result updateBindInfo(Long ownerId, String phoneNumber, String path) throws ClientException {
        String model="SMS_218034906";
        CommonResponse commonResponse = smsConfig.generateSmsRequest(phoneNumber,model);
        if (commonResponse!=null){
            redisTemplate.opsForValue().set(ownerId,smsConfig.getCode(),5, TimeUnit.MINUTES);
            return new Result().result200("发送成功",path);
        }

        return new Result().result500("发送失败",path);
    }
    /**
     * <p>验证绑定验证码修改绑定信息</p>
     * @author tfj
     * @since 2021/7/11
     */
    @Override
    public Result judgeCodeUpdate(String code,Long ownerId,PostReal postReal, String path) {
        String a = (String) redisTemplate.opsForValue().get(ownerId);
        assert a != null;
        if (a.equals(code)){
            Real selectOne = realMapper.selectOne(new QueryWrapper<Real>().eq("ownerId", ownerId));
            selectOne.setAddress(postReal.getAddress());
            selectOne.setRealName(postReal.getRealName());
            if (realMapper.updateById(selectOne)==1){
                return new Result().result200("修改绑定信息成功",path);
            }
        }
        return new Result().result500("验证码错误",path);
    }

    /**
     * <p>业主端圈子展示</p>
     * @author tfj
     * @since 2021/7/5
     */
    @Override
    public Result getOwnerCircle(String path) {
        List<GetCircleVo> getCircleVos = circleService.showCircles();
        for (int i = 0; i < getCircleVos.size(); i++) {
            GetCircleVo getCircleVo = getCircleVos.get(i);
            if (circleService.judgeChildCircle(getCircleVo.getId())){
                getCircleVo.setChildrenList(circleService.showChildCircles(getCircleVo.getId()));
                List<GetCircleVo> childrenList = getCircleVo.getChildrenList();
                for (GetCircleVo getChildCircleVo : childrenList) {
                    if (circleService.judgeThirdCircle(getChildCircleVo.getId())) {
                        getChildCircleVo.setThirdList(circleService.showThirdCircle(getChildCircleVo.getId()));
                    }
                }
            }
            Collections.replaceAll(getCircleVos,getCircleVos.get(i),getCircleVo);
        }

        ReplyCircleVo replyCircleVo=new ReplyCircleVo();
        replyCircleVo.setGetCircleVoList(getCircleVos);
        return new Result().result200(replyCircleVo,path);
    }
    /**
     * <p>判断用户绑定状态</p>
     * @author tfj
     * @since 2021/7/11
     */
    @Override
    public Result judgeOwnerState(Long ownerId,String path) {
        Real real = realMapper.selectOne(new QueryWrapper<Real>().eq("ownerId", ownerId));
        if (real!=null){
            return new Result().result200(true,path);
        }
        return new Result().result200(false,path);
    }
}
