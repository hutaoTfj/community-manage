package ink.hutao.manage.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * <p>绑定用户信息对象</p>
 * @author tfj
 * @since 2021/6/29
 */
@Data
public class PostReal {

    @TableField("realName")
    private String realName;

    private String address;
}
