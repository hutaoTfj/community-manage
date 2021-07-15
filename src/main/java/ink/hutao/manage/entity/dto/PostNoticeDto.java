package ink.hutao.manage.entity.dto;

import lombok.Data;
/**
 * <p>添加公告实体类</p>
 * @author tfj
 * @since 2021/6/29
 */
@Data
public class PostNoticeDto {

    private String headline;

    private String notice;

    private String imageUrl;

    private String briefInfo;

}
