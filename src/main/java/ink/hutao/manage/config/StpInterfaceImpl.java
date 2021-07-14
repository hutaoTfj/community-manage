package ink.hutao.manage.config;


import cn.dev33.satoken.stp.StpInterface;
import ink.hutao.manage.mapper.OwnerMapper;
import ink.hutao.manage.mapper.RoleMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>获取当前账号权限码集合</p>
 * @author tfj
 * @since 2021/6/21
 */
@Component
@Data
public class StpInterfaceImpl implements StpInterface {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private OwnerMapper ownerMapper;
    @Override
    public List<String> getPermissionList(Object id, String s) {
        String empRole = ownerMapper.getEmpRole((Long) id);
        List<String> list=new ArrayList<>();
        if ("RepairMan".equals(empRole)){
            list.add("emp_repair");
        }
        return list;
    }

    @Override
    public List<String> getRoleList(Object account, String s) {

        List<String> list=new ArrayList<>();
        String adminRole = roleMapper.getRole((String) account);
        if ("admin".equals(adminRole)){
            list.add("admin");
        }
        return list;
    }

}
