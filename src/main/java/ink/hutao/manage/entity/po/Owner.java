package ink.hutao.manage.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

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

    private Long id;

    @ApiModelProperty(value = "openID")
    @TableField("openId")
    private String openId;

    @ApiModelProperty(value = "真实性名")
    @TableField("realName")
    private String realName;

    @ApiModelProperty(example = "用户名")
    @TableField("nickName")
    private String nickName;

    @ApiModelProperty(example = "电话号码")
    @TableField("phoneNumber")
    private String phoneNumber;

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

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    private Date createTime;

    @TableField("modifyTime")
    private Date modifyTime;
}
