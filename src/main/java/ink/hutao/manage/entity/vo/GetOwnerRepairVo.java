package ink.hutao.manage.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * <p>用户报修历史对象</p>
 * @author tfj
 * @since 2021/7/16
 */
@Data
public class GetOwnerRepairVo {

    @TableField("imageUrl")
    private String imageUrl;

    @TableField("repairContext")
    private String repairContext;

    @TableField("repairState")
    private String repairState;

    @TableField("modifyTime")
    private Date modifyTime;

}
