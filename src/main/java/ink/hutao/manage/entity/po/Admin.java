package ink.hutao.manage.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/13
 */
@EqualsAndHashCode(callSuper = false)
@TableName("sys_admin")
@ApiModel(value="admin", description="管理员表实体类")
@Data
public class Admin implements Serializable {

    private Long id;

    @TableField("nickName")
    private String nickName;

    private String account;

    @TableField("createTime")
    private Date createTime;

    @TableField("modifyTime")
    private Date modifyTime;

}
