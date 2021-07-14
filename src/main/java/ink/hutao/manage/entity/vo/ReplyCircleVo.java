package ink.hutao.manage.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/5
 */
@Data
public class ReplyCircleVo {


    @TableField("openId")
    private String openId;

    @TableField("imageUrl")
    private String imageUrl;

    private List<GetCircleVo> getCircleVoList;

}
