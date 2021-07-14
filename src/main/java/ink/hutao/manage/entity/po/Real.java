package ink.hutao.manage.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
/**
 * <p></p>
 * @author tfj
 * @since 2021/6/29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_real")
@ApiModel(value="Real", description="Real表实体类")
public class Real {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @TableField("realName")
    private String realName;

    @TableField("address")
    private String address;

    @JsonSerialize(using= ToStringSerializer.class)
    @TableField("ownerId")
    private Long ownerId;

    @TableField("phoneNumber")
    private String phoneNumber;

    @TableField("createTime")
    private Date createTime;

    @TableField("modifyTime")
    private Date modifyTime;

}
