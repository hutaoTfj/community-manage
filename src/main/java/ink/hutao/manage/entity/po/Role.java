package ink.hutao.manage.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role")
@ApiModel(value="role", description="角色表实体类")
public class Role implements Serializable {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @TableField("roleName")
    private String roleName;

    @TableField("roleId")
    private Long roleId;

    @TableField("createTime")
    private Date createTime;

    @TableField("modifyTime")
    private Date modifyTime;
}
