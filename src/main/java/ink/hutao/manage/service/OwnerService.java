package ink.hutao.manage.service;

import com.aliyuncs.exceptions.ClientException;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.entity.dto.PostReal;
import ink.hutao.manage.entity.dto.PostUserDto;
import ink.hutao.manage.entity.po.Owner;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

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
    Result sendSmsCode(String phoneNumber,Long ownerId, String path) throws ClientException;
    /**
     * <p>输入验证码验证</p>
     * @author tfj
     * @since 2021/6/29
     */
    Result judgeCode(String code,Long ownerId, String path);
    /**
     * <p>绑定用户信息</p>
     * @author tfj
     * @since 2021/6/29
     */
    Result bindOwnerInfo(PostReal postReal,Long ownerId, String path);
    /**
     * <p>获取业主真实信息和住址</p>
     * @author tfj
     * @since 2021/6/30
     */
    Result getOwnerRealInfo(Long ownerId, String path);
    /**
     * <p>上传图片接口</p>
     * @author tfj
     * @since 2021/7/2
     */
    String upLoadImages(MultipartFile file);
    /**
     * <p>业主修改绑定信息</p>
     * @author tfj
     * @since 2021/7/4
     */
    Result updateBindInfo(Long ownerId, String phoneNumber, String path) throws ClientException;
    /**
     * <p>业主端圈子展示</p>
     * @author tfj
     * @since 2021/7/5
     */
    Result getOwnerCircle(String path);
    /**
     * <p>判断用户绑定状态</p>
     * @author tfj
     * @since 2021/7/11
     */
    Result judgeOwnerState(Long ownerId,String path);
    /**
     * <p>验证绑定验证码</p>
     * @author tfj
     * @since 2021/7/11
     */
    Result judgeCodeUpdate(String code,Long ownerId,PostReal postReal, String path);
}
