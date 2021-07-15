package ink.hutao.manage.controller;

import com.aliyuncs.exceptions.ClientException;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/4
 */
@RestController
@RequestMapping("/employee")
@CrossOrigin
@Api(tags = "员工")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;
    /**
     * <p>修理员查看业主报修信息</p>
     * @author tfj
     * @since 2021/7/4
     */
    @ApiOperation(value = "修理员查看业主报修信息")
    @GetMapping("/getOwnerRepairInfo")
    public Result getOwnerRepairInfo(){
        return employeeService.getOwnerRepairInfo("/employee/getOwnerRepairInfo");
    }
    /**
     * <p>修理员处理报修信息</p>
     * @author tfj
     * @since 2021/7/4
     */
    @ApiOperation(value = "修理员处理报修信息")
    @PostMapping("/resolveRepair")
    public Result resolveRepair(@RequestParam Long repairId) throws ClientException {
        return employeeService.resolveRepair(repairId,"/employee/resolveRepair");
    }
    /**
     * <p>判断是否为员工</p>
     * @author tfj
     * @since 2021/7/14
     */
    @ApiOperation(value = "判断是否为员工")
    @GetMapping("/judgeEmployee")
    public Result judgeEmployee(@RequestParam Long ownerId){
        return employeeService.judgeEmployee(ownerId,"/employee/judgeEmployee");
    }
}
