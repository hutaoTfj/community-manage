package ink.hutao.manage.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p></p>
 * @author tfj
 * @since 2021/6/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_owner")
public class PostUserDto {

    @ApiModelProperty(value = "用户名")
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

    @ApiModelProperty(value = "openID")
    @TableField("openId")
    private String openId;
}
