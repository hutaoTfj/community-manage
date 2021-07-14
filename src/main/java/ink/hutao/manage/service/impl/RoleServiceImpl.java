package ink.hutao.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.hutao.manage.entity.po.Role;
import ink.hutao.manage.mapper.RoleMapper;
import ink.hutao.manage.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/13
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
