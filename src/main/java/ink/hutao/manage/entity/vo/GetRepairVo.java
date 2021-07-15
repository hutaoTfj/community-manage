package ink.hutao.manage.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>获取报修对象</p>
 * @author tfj
 * @since 2021/7/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRepairVo {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @TableField("imageUrl")
    private String imageUrl;

    @TableField("repairContext")
    private String repairContext;

    @TableField("repairState")
    private String repairState;

    @TableField("createTime")
    private Date createTime;

    @TableField("phoneNumber")
    private String phoneNumber;
}
