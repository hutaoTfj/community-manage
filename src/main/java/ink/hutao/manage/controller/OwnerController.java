package ink.hutao.manage.controller;


import com.aliyuncs.exceptions.ClientException;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.entity.dto.PostUserDto;
import ink.hutao.manage.service.OwnerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tfj
 * @since 2021-06-20
 */
@RestController
@CrossOrigin
@RequestMapping("/owner")
@ApiOperation(value = "业主操作控制器")
public class OwnerController {
    @Resource
    private  OwnerService ownerService;

    /**
     * <p>获取微信登录凭证</p>
     * @author tfj
     * @since 2021/6/28
     */
    @ApiOperation(value = "获取微信登录凭证")
    @GetMapping("/getOpenId")
    public Result getOpenId(@RequestParam String code){
        return ownerService.getOpenId(code,"/owner/getOpenId");
    }
    /**
     * <p>获取微信用户信息</p>
     * @author tfj
     * @since 2021/6/28
     */
    @ApiOperation(value = "获取微信用户信息")
    @PostMapping("/getUserInfo")
    public Result getUserInfo(@RequestBody PostUserDto postUserDto){
        return ownerService.getUserInfo(postUserDto,"/owner/getUserInfo");
    }
    /**
     * <p>发送手机验证码</p>
     * @author tfj
     * @since 2021/6/28
     */
    @ApiOperation(value = "发送手机验证码")
    @GetMapping("/sendSmsCode")
    public Result sendSmsCode(@RequestParam String phoneNumber,@RequestParam String openId) throws ClientException {
        return ownerService.sendSmsCode(phoneNumber,openId,"/owner/sendSmsCode");
    }
    /**
     * <p>输入验证码验证</p>
     * @author tfj
     * @since 2021/6/29
     */
    @ApiOperation(value = "输入手机验证码验证")
    @GetMapping("/judgeCode")
    public Result judgeCode(@RequestParam int code,@RequestParam String openId){
        return ownerService.judgeCode(code,openId,"/owner/judgeCode");
    }
}

