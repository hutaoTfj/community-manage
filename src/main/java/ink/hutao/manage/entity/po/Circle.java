package ink.hutao.manage.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/4
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_circle")
@ApiModel(value="circle对象", description="circle表实体类")
public class Circle implements Serializable {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "ownerId")
    @TableField("ownerId")
    private Long ownerId;

    private String message;

    @TableField("parentId")
    private Long parentId;

    @TableField("circleImagesUrl")
    private String circleImagesUrl;

    @TableField("deleted")
    @TableLogic
    private int deleted;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    private Date createTime;

    @TableField("modifyTime")
    private Date modifyTime;
}
