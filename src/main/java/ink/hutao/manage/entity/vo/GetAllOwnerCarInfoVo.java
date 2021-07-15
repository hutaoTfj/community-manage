package ink.hutao.manage.entity.vo;

import com.fasterxml.jackson.databind.node.LongNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.Serializable;

/**
 * <p>获取所有用户车辆信息对象</p>
 * @author tfj
 * @since 2021/7/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllOwnerCarInfoVo implements Serializable {

    private Long id;

    private String realName;

    private String imageUrl;

    private String carNumber;

    private String carInfo;

    private String carImageUrl;

}
