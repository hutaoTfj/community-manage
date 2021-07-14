package ink.hutao.manage.service.impl;

import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.config.SmsConfig;
import ink.hutao.manage.entity.po.Repair;
import ink.hutao.manage.entity.vo.GetRepairVo;
import ink.hutao.manage.mapper.RepairMapper;
import ink.hutao.manage.mapper.RoleMapper;
import ink.hutao.manage.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/4
 */
@Service
public class EmployeeServiceImpl  implements EmployeeService {
    @Resource
    private RepairMapper repairMapper;
    @Resource
    private SmsConfig smsConfig;
    @Resource
    private RoleMapper roleMapper;
    /**
     * <p>修理员查看业主报修信息</p>
     * @author tfj
     * @since 2021/7/4
     */
    @Override
    public Result getOwnerRepairInfo(String path) {
        List<GetRepairVo> getRepairVos = repairMapper.getOwnerRepairInfo();
        return new Result().result200(getRepairVos,path);
    }
    /**
     * <p>修理员处理报修信息</p>
     * @author tfj
     * @since 2021/7/4
     */
    @Override
    public Result resolveRepair(Long repairId,String path) throws ClientException {
        Repair selectById = repairMapper.selectById(repairId);
        String ownerPhoneNumber = repairMapper.getOwnerPhoneNumber(selectById.getOpenId());
        String model="SMS_218890834";
        CommonResponse commonResponse = smsConfig.sendSmsMessage(ownerPhoneNumber,model);
        if (commonResponse!=null){
            Repair repair = repairMapper.selectById(repairId);
            repair.setRepairState("已处理");
            repairMapper.updateById(repair);
            return new Result().result200("处理成功",path);
        }
        return new Result().result200("处理失败",path);
    }
    /**
     * <p>判断是否为员工或者管理员</p>
     * @author tfj
     * @since 2021/7/14
     */
    @Override
    public Result judgeEmployee(Long ownerId, String path) {
        if ("RepairMan".equals(roleMapper.judgeEmployee(ownerId))||"admin".equals(roleMapper.judgeEmployee(ownerId))){
            return new Result().result200(true,path);
        }
        return new Result().result500(false,path);
    }
}
