package ink.hutao.manage.service;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.entity.po.Admin;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/13
 */
public interface AdminService extends IService<Admin> {
    /**
     * <p>管理员登录</p>
     * @author tfj
     * @since 2021/7/13
     */
    Result adminLogin(String account, String cipher, String path) throws ClientException;
    /**
     * <p>管理员登录验证码验证</p>
     * @author tfj
     * @since 2021/7/13
     */
    Result judgeAdminLogin(String account,String code, String path);
    /**
     * <p>退出登录</p>
     * @author tfj
     * @since 2021/7/13
     */
    Result exitLogin(String path);
    /**
     * <p>修改管理员密码</p>
     * @author tfj
     * @since 2021/7/13
     */
    Result putAdminCipher(String account, String newCipher, String oldCipher, String path);
    /**
     * <p>获取所有业主真实信息</p>
     * @author tfj
     * @since 2021/7/14
     */
    Result getAllOwnerRealInfo(String path);
}
