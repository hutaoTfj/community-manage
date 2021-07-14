package ink.hutao.manage.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import com.aliyuncs.exceptions.ClientException;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.entity.dto.PostReal;
import ink.hutao.manage.entity.dto.PostUserDto;
import ink.hutao.manage.service.OwnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@Api(tags = "业主端")
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
    @PostMapping("/postUserInfo")
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
    public Result sendSmsCode(@RequestParam String phoneNumber,@RequestParam Long ownerId) throws ClientException {
        return ownerService.sendSmsCode(phoneNumber,ownerId,"/owner/sendSmsCode");
    }
    /**
     * <p>输入验证码验证</p>
     * @author tfj
     * @since 2021/6/29
     */
    @ApiOperation(value = "输入手机验证码验证")
    @GetMapping("/judgeCode")
    public Result judgeCode(@RequestParam String code,@RequestParam Long ownerId){
        return ownerService.judgeCode(code,ownerId,"/owner/judgeCode");
    }
    /**
     * <p>绑定用户信息</p>
     * @author tfj
     * @since 2021/6/29
     */
    @ApiOperation(value = "绑定用户信息")
    @PostMapping("/bindOwnerInfo")
    public Result bindOwnerInfo(@RequestBody PostReal postReal,@RequestParam Long ownerId){
        return ownerService.bindOwnerInfo(postReal,ownerId,"/owner/bindOwnerInfo");
    }
    /**
     * <p>获取业主真实信息和住址</p>
     * @author tfj
     * @since 2021/6/30
     */
    @ApiOperation(value = "获取业主真实信息和住址")
    @GetMapping("/getOwnerRealInfo")
    public Result getOwnerRealInfo(@RequestParam Long ownerId){
        return ownerService.getOwnerRealInfo(ownerId,"/owner/getOwnerRealInfo");
    }
    /**
     * <p>上传图片接口</p>
     * @author tfj
     * @since 2021/7/2
     */
    @ApiOperation(value = "上传图片接口")
    @PostMapping("/upLoadImages")
    public String upLoadImages(@RequestPart MultipartFile file){
        return ownerService.upLoadImages(file);
    }

    /**
     * <p>业主修改绑定信息发送验证码</p>
     * @author tfj
     * @since 2021/7/4
     */
    @ApiOperation(value = "业主修改绑定信息发送验证码")
    @GetMapping("/updateBindInfo")
    public Result updateBindInfo(@RequestParam Long ownerId,String phoneNumber) throws ClientException {
        return ownerService.updateBindInfo(ownerId,phoneNumber,"/owner/updateBindInfo");
    }
    /**
     * <p>验证绑定验证码</p>
     * @author tfj
     * @since 2021/7/11
     */
    @ApiOperation(value = "验证绑定验证码修改绑定信息")
    @PutMapping("/judgeCodeUpdate")
    public Result judgeCodeUpdate(@RequestParam String code,Long ownerId, @RequestBody PostReal postReal){
        return ownerService.judgeCodeUpdate(code,ownerId,postReal,"/owner/judgeCodeUpdate");
    }
    /**
     * <p>业主端所有圈子展示</p>
     * @author tfj
     * @since 2021/7/5
     */
    @ApiOperation(value = "业主端所有圈子展示")
    @GetMapping("/getOwnerCircle")
    public Result getOwnerCircle(){
        return ownerService.getOwnerCircle("/owner/getOwnerCircle");
    }

    /**
     * <p>判断用户绑定状态</p>
     * @author tfj
     * @since 2021/7/11
     */
    @ApiOperation(value = "判断用户绑定状态")
    @GetMapping("/judgeOwnerState")
    public Result judgeOwnerState(@RequestParam Long ownerId){
        return ownerService.judgeOwnerState(ownerId,"/owner/judgeOwnerState");
    }


}
