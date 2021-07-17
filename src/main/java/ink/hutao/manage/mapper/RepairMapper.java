package ink.hutao.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.hutao.manage.entity.po.Repair;
import ink.hutao.manage.entity.vo.GetOwnerRepairVo;
import ink.hutao.manage.entity.vo.GetRepairVo;

import java.util.List;

public interface RepairMapper extends BaseMapper<Repair> {
    /**
     * <p>修理员查看业主报修信息</p>
     * @author tfj
     * @since 2021/7/4
     */
    List<GetRepairVo> getOwnerRepairInfo();

    String getOwnerPhoneNumber(Long ownerId);

    List<GetOwnerRepairVo> getHistoryRepair(Long ownerId);
}
