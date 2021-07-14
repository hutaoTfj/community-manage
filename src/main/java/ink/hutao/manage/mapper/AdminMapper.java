package ink.hutao.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.hutao.manage.entity.po.Admin;
import ink.hutao.manage.entity.vo.GetAllOwnerRealInfoVo;

import java.util.List;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/13
 */
public interface AdminMapper extends BaseMapper<Admin> {
    String judgeCipher(String account);

    String queryCipher(String account);

    boolean putAdminCipher(String account, String newCipher);

    List<GetAllOwnerRealInfoVo> getAllOwnerRealInfo();
}
