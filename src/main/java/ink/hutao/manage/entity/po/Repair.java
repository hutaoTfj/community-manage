package ink.hutao.manage.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
/**
 * <p></p>
 * @author tfj
 * @since 2021/6/30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_repair")
@ApiModel(value="repair", description="报修表表实体类")
public class Repair {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @TableField("ownerId")
    private Long ownerId;

    @TableField("imageUrl")
    private String imageUrl;

    @TableField("repairContext")
    private String repairContext;

    @TableField("employeeId")
    private Long employeeId;

    @TableField("repairState")
    private String repairState;

    @TableLogic
    private int deleted;

    @TableField("createTime")
    private Date createTime;

    @TableField("modifyTime")
    private Date modifyTime;
}
