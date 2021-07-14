package ink.hutao.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.hutao.manage.entity.po.Car;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/14
 */
public interface CarMapper extends BaseMapper<Car> {
    int judgeCarNumber(String txt);

    Car getOwnerCarInfo(Long ownerId);
}
