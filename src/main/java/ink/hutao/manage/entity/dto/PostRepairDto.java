package ink.hutao.manage.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class PostRepairDto {

    @TableField("imageUrl")
    private String imageUrl;

    @TableField("repairContext")
    private String repairContext;
}
