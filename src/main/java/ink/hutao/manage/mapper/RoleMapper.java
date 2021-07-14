package ink.hutao.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.hutao.manage.entity.po.Role;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/13
 */
public interface RoleMapper extends BaseMapper<Role> {
    String getRole(String account);

    String judgeEmployee(Long ownerId);
}
