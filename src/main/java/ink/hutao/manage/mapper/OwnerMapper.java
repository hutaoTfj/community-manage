package ink.hutao.manage.mapper;

import ink.hutao.manage.entity.po.Owner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.hutao.manage.entity.vo.GetOwnerRealVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tfj
 * @since 2021-06-20
 */
public interface OwnerMapper extends BaseMapper<Owner> {

    GetOwnerRealVo getOwnerRealInfo(Long ownerId);

    String getEmpRole(Long id);
}
