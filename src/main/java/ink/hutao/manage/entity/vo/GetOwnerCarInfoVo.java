package ink.hutao.manage.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * <p>获取用户车辆信息对象</p>
 * @author tfj
 * @since 2021/7/15
 */
@Data
public class GetOwnerCarInfoVo {

    private String carNumber;

    private String carInfo;

    private String carImageUrl;

    @TableField("createTime")
    private Date createTime;
}
