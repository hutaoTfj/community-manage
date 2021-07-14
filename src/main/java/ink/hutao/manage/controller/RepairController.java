package ink.hutao.manage.controller;

import com.xiaoTools.core.result.Result;
import ink.hutao.manage.entity.dto.PostRepairDto;
import ink.hutao.manage.service.RepairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * <p></p>
 * @author tfj
 * @since 2021/6/30
 */
@RestController
@RequestMapping("/repair")
@CrossOrigin
@Api(tags = "报修")
public class RepairController {
    @Resource
    private RepairService repairService;
    /**
     * <p>业主提交报修信息</p>
     * @author tfj
     * @since 2021/6/30
     */
    @ApiOperation(value = "业主提交报修信息")
    @PostMapping("/postRepairInfo")
    public Result postRepairInfo(@RequestBody PostRepairDto postRepairDto,@RequestParam String openId){
        return repairService.postRepairInfo(postRepairDto,openId,"/repair/postRepairInfo");
    }
    /**
     * <p>业主撤回报修</p>
     * @author tfj
     * @since 2021/7/2
     */
    @ApiOperation(value = "业主撤回报修")
    @DeleteMapping("/deleteRepair")
    public Result deleteRepair(@RequestParam Long id){
        return repairService.deleteRepair(id,"/repair/deleteRepair");
    }
    /**
     * <p>业主查看报修历史</p>
     * @author tfj
     * @since 2021/7/2
     */
    @ApiOperation(value = "业主查看报修历史")
    @GetMapping("getHistoryRepair")
    public Result getHistoryRepair(@RequestParam String openId){
        return repairService.getHistoryRepair(openId,"/repair/getHistoryRepair");
    }


}
