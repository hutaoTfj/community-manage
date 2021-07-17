package ink.hutao.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.config.WxConfig;
import ink.hutao.manage.entity.dto.PostRepairDto;
import ink.hutao.manage.entity.po.Repair;
import ink.hutao.manage.entity.vo.GetOwnerRepairVo;
import ink.hutao.manage.entity.vo.GetRepairVo;
import ink.hutao.manage.mapper.RepairMapper;
import ink.hutao.manage.service.RepairService;
import ink.hutao.manage.utils.BeansUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/2
 */
@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements RepairService {
    @Resource
    private RepairMapper repairMapper;
    @Resource
    private WxConfig wxConfig;
    /**
     * <p>业主提交报修信息</p>
     * @author tfj
     * @since 2021/6/30
     */
    @Override
    public Result postRepairInfo(PostRepairDto postRepairDto, Long ownerId, String path) {
        Repair newRepair=new Repair();
        newRepair.setId(wxConfig.getId());
        newRepair.setRepairContext(postRepairDto.getRepairContext());
        newRepair.setImageUrl(postRepairDto.getImageUrl());
        newRepair.setOwnerId(ownerId);
        newRepair.setRepairState("待处理");
        newRepair.setCreateTime(new Date());
        if (repairMapper.insert(newRepair)==1){
            return new Result().result200("报修提交成功",path);
        }
        return new Result().result500("报修提交失败",path);
    }
    /**
     * <p>业主撤回报修</p>
     * @author tfj
     * @since 2021/7/2
     */
    @Override
    public Result deleteRepair(Long id, String path) {
        if (repairMapper.deleteById(id)==1){
            return new Result().result200("撤回成功",path);
        }
        return new Result().result500("撤回失败",path);
    }
    /**
     * <p>业主查看报修历史</p>
     * @author tfj
     * @since 2021/7/2
     */
    @Override
    public Result getHistoryRepair(Long ownerId, String path) {
        List<GetOwnerRepairVo> repairList = repairMapper.getHistoryRepair(ownerId);
        if (repairMapper!=null){
            return new Result().result200(repairList,path);
        }
        return new Result().result200(null,path);
    }

}
