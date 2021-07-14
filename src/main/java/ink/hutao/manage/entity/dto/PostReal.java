package ink.hutao.manage.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class PostReal {

    @TableField("realName")
    private String realName;

    private String address;
}
