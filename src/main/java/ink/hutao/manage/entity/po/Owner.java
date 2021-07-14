package ink.hutao.manage.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author tfj
 * @since 2021-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_owner")
@ApiModel(value="Owner对象", description="Owner表实体类")
public class Owner implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "openID")
    @TableField("openId")
    private String openId;

    @ApiModelProperty(example = "微信昵称")
    @TableField("nickName")
    private String nickName;

    @ApiModelProperty(value = "头像地址")
    @TableField("imageUrl")
    private String imageUrl;

    @ApiModelProperty(value = "用户性别")
    @TableField("gender")
    private int gender;

    @ApiModelProperty(value = "城市")
    @TableField("city")
    private String city;

    @ApiModelProperty(value = "省份")
    @TableField("province")
    private String province;

    @ApiModelProperty(value = "国家")
    @TableField("country")
    private String country;

    @TableField("deleted")
    @TableLogic
    private int deleted;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    private Date createTime;

    @TableField("modifyTime")
    private Date modifyTime;
}
