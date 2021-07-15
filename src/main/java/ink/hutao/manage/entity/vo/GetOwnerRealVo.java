package ink.hutao.manage.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * <p>获取用户真实信息对象</p>
 * @author tfj
 * @since 2021/6/30
 */
@Data
public class GetOwnerRealVo {

    @ApiModelProperty(example = "微信昵称")
    @TableField("nickName")
    private String nickName;

    @ApiModelProperty(value = "头像地址")
    @TableField("imageUrl")
    private String imageUrl;

    @TableField("realName")
    private String realName;

    private String address;

    @TableField("phoneNumber")
    private String phoneNumber;
}
