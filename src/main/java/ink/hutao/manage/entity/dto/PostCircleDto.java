package ink.hutao.manage.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
/**
 * <p></p>
 * @author tfj
 * @since 2021/7/13
 */
@Data
public class PostCircleDto {

    private String message;

    @TableField("parentId")
    private Long parentId;

    @TableField("circleImagesUrl")
    private String circleImagesUrl;

}
