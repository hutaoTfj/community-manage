package ink.hutao.manage.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/14
 */
@Data
public class GetAllOwnerRealInfoVo implements Serializable {

    private Long id;

    @ApiModelProperty(example = "微信昵称")
    @TableField("nickName")
    private String nickName;

    @ApiModelProperty(value = "头像地址")
    @TableField("imageUrl")
    private String imageUrl;

    @TableField("realName")
    private String realName;

    @TableField("address")
    private String address;

    @TableField("phoneNumber")
    private String phoneNumber;

    @TableField("communityName")
    private String communityName;

    @TableField("createTime")
    private Date createTime;

}
