package ink.hutao.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.entity.po.Car;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/14
 */
public interface CarService extends IService<Car> {
    /**
     * <p>车牌号识别</p>
     * @author tfj
     * @since 2021/7/14
     */
    Result licensePlateIdentification(MultipartFile file, String path) throws Exception;
    /**
     * <p>业主添加所属车辆</p>
     * @author tfj
     * @since 2021/7/14
     */
    Result addOwnerCar(MultipartFile file, Long ownerId, String path) throws Exception;
    /**
     * <p>判断是否是本小区汽车</p>
     * @author tfj
     * @since 2021/7/14
     */
    Result judgeCarNumber(MultipartFile file, String path) throws Exception;
    /**
     * <p>获取业主车辆信息</p>
     * @author tfj
     * @since 2021/7/14
     */
    Result getOwnerCarInfo(Long ownerId, String path);
}
