package ink.hutao.manage.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
/**
 * <p></p>
 * @author tfj
 * @since 2021/6/29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_banner")
@ApiModel(value="banner对象", description="banner表实体类")
public class Banner  {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @TableField("bannerUrl")
    private String bannerUrl;

    @TableField("bannerContext")
    private String bannerContext;

    @TableField("deleted")
    @TableLogic
    private int deleted;

    @TableField("createTime")
    private Date createTime;

    @TableField("modifyTime")
    private Date modifyTime;
}

