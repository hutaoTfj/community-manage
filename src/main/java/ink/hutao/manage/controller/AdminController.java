package ink.hutao.manage.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.aliyuncs.exceptions.ClientException;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/13
 */
@CrossOrigin
@RestController
@RequestMapping("/admin")
@Api(tags = "管理员")
public class AdminController {
    @Resource
    private AdminService adminService;
    /**
     * <p>管理员登录</p>
     * @author tfj
     * @since 2021/7/13
     */
    @ApiOperation(value = "管理员登录")
    @GetMapping("/adminLogin")
    public Result adminLogin(@RequestParam String account,String cipher) throws ClientException {
        return adminService.adminLogin(account,cipher,"/admin/adminLogin");
    }
    /**
     * <p>管理员登录验证码验证</p>
     * @author tfj
     * @since 2021/7/13
     */
    @ApiOperation(value = "管理员登录验证码验证")
    @GetMapping("/judgeAdminLogin")
    public Result judgeAdminLogin(@RequestParam String account,String code){
        return adminService.judgeAdminLogin(account,code,"/admin/judgeAdminLogin");
    }
    /**
     * <p>退出登录</p>
     * @author tfj
     * @since 2021/7/13
     */
    @ApiOperation(value = "退出登录")
    @GetMapping("/exitLogin")
    public Result exitLogin(){
        return adminService.exitLogin("/admin/exitLogin");
    }

    /**
     * <p>修改管理员密码</p>
     * @author tfj
     * @since 2021/7/13
     */
    @ApiOperation(value = "修改管理员密码")
    @SaCheckRole("admin")
    @PutMapping("/putAdminCipher")
    public Result putAdminCipher(@RequestParam String account,String newCipher,String oldCipher){
        return adminService.putAdminCipher(account,newCipher,oldCipher,"/admin/putAdminCipher");
    }
    /**
     * <p>获取所有业主真实信息</p>
     * @author tfj
     * @since 2021/7/14
     */
    @ApiOperation(value = "获取所有业主真实信息")
    @SaCheckRole("admin")
    @GetMapping("/getAllOwnerRealInfo")
    public Result getAllOwnerRealInfo(){
        return adminService.getAllOwnerRealInfo("/admin/getAllOwnerRealInfo");
    }
}
