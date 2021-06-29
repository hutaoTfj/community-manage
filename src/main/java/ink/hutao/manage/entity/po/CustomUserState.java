package ink.hutao.manage.entity.po;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p></p>
 * @author tfj
 * @since 2021/6/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="User登录对象", description="自定义用户登录状态")
public class CustomUserState {

    private String openid;

    private String session_key;
}
