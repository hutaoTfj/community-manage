package ink.hutao.manage.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/4
 */
@Data
public class GetCircleVo implements Serializable {

    private Long id;

    @ApiModelProperty(value = "ownerId")
    @TableField("ownerId")
    private Long ownerId;

    @TableField("parentId")
    private Long parentId;

    @ApiModelProperty("第N楼")
    private OwnerInfoVo childPoster;

    @ApiModelProperty("楼主")
    private OwnerInfoVo originalPoster;

    private String message;

    @ApiModelProperty(value = "图片地址")
    @TableField("circleImagesUrl")
    private String circleImagesUrl;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    private Date createTime;

    @ApiModelProperty("子圈子")
    private List<GetCircleVo> childrenList;

    @ApiModelProperty("三级圈子")
    private List<GetCircleVo> thirdList;
}
