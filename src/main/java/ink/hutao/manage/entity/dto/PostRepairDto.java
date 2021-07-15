package ink.hutao.manage.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * <p>提交报修对象</p>
 * @author tfj
 * @since 2021/6/30
 */
@Data
public class PostRepairDto {

    @TableField("imageUrl")
    private String imageUrl;

    @TableField("repairContext")
    private String repairContext;
}
