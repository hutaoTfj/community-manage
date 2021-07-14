package ink.hutao.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.entity.dto.PostRepairDto;
import ink.hutao.manage.entity.po.Repair;
/**
 * <p></p>
 * @author tfj
 * @since 2021/6/30
 */
public interface RepairService extends IService<Repair> {
    /**
     * <p>业主提交报修信息</p>
     * @author tfj
     * @since 2021/6/30
     */
    Result postRepairInfo(PostRepairDto postRepairDto, String openId, String path);
    /**
     * <p>业主撤回报修</p>
     * @author tfj
     * @since 2021/7/2
     */
    Result deleteRepair(Long id, String path);
    /**
     * <p>业主查看报修历史</p>
     * @author tfj
     * @since 2021/7/2
     */
    Result getHistoryRepair(String openId, String path);
}
