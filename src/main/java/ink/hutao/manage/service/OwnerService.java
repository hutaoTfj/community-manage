package ink.hutao.manage.service;

import com.aliyuncs.exceptions.ClientException;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.entity.dto.PostUserDto;
import ink.hutao.manage.entity.po.Owner;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tfj
 * @since 2021-06-20
 */
public interface OwnerService extends IService<Owner> {
    /**
     * <p>获取微信登录凭证</p>
     * @author tfj
     * @since 2021/6/28
     */
    Result getOpenId(@Param("code") String code, String path);
    /**
     * <p>获取微信用户信息</p>
     * @author tfj
     * @since 2021/6/28
     */
    Result getUserInfo(PostUserDto postUserDto, String path);
    /**
     * <p></p>
     * @author tfj
     * @since 2021/6/29
     */
    Result sendSmsCode(String phoneNumber,String openId, String path) throws ClientException;
    /**
     * <p>输入验证码验证</p>
     * @author tfj
     * @since 2021/6/29
     */
    Result judgeCode(int code,String openId, String path);
}
