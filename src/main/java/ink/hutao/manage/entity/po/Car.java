package ink.hutao.manage.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/14
 */
@EqualsAndHashCode(callSuper = false)
@TableName("sys_car")
@ApiModel(value="car", description="业主汽车表实体类")
@Data
public class Car implements Serializable   {

    private Long id;

    @TableField("carNumber")
    private String carNumber;

    @TableField("carInfo")
    private String carInfo;

    @TableField("ownerId")
    private Long ownerId;

    @TableField("deleted")
    @TableLogic
    private int deleted;

    @TableField("carImageUrl")
    private String carImageUrl;

    @TableField("createTime")
    private Date createTime;

    @TableField("modifyTime")
    private Date modifyTime;

}
