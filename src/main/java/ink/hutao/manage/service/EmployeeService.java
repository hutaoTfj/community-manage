package ink.hutao.manage.service;

import com.aliyuncs.exceptions.ClientException;
import com.xiaoTools.core.result.Result;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/4
 */
public interface EmployeeService {
    /**
     * <p>修理员查看业主报修信息</p>
     * @author tfj
     * @since 2021/7/4
     */
    Result getOwnerRepairInfo(String path);
    /**
     * <p>修理员处理报修信息</p>
     * @author tfj
     * @since 2021/7/4
     */
    Result resolveRepair(Long repairId,Long employeeId,String path) throws ClientException;
    /**
     * <p>判断是否为员工</p>
     * @author tfj
     * @since 2021/7/14
     */
    Result judgeEmployee(Long ownerId, String path);
}
