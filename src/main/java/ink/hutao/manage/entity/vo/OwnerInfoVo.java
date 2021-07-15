package ink.hutao.manage.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>用户信息对象</p>
 * @author tfj
 * @since 2021/7/4
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_owner")
public class OwnerInfoVo {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @TableField("nickName")
    private String nickName;

    @TableField("imageUrl")
    private String imageUrl;
}
