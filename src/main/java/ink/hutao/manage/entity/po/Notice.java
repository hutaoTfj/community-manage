package ink.hutao.manage.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * <p></p>
 * @author tfj
 * @since 2021/6/29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_notice")
@ApiModel(value="notice对象", description="notice表实体类")
public class Notice implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @TableField("headline")
    private String headline;

    @TableField("notice")
    private String notice;

    @TableField("briefInfo")
    private String briefInfo;

    @TableField("imageUrl")
    private String imageUrl;

    @TableField("deleted")
    @TableLogic
    private int deleted;

    @TableField("createTime")
    private Date createTime;

    @TableField("modifyTime")
    private Date modifyTime;
}
