package ink.hutao.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.config.OsrConfig;
import ink.hutao.manage.config.OssConfig;
import ink.hutao.manage.config.WxConfig;
import ink.hutao.manage.entity.po.Car;
import ink.hutao.manage.entity.po.Plates;
import ink.hutao.manage.entity.vo.GetOwnerCarInfoVo;
import ink.hutao.manage.mapper.CarMapper;
import ink.hutao.manage.service.CarService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/14
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {
    @Resource
    private OsrConfig osrConfig;
    @Resource
    private OssConfig ossConfig;
    @Resource
    private CarMapper carMapper;
    @Resource
    private WxConfig wxConfig;
    /**
     * <p>车牌号识别</p>
     * @author tfj
     * @since 2021/7/14
     */
    @Override
    public Result licensePlateIdentification(MultipartFile file, String path) throws Exception {
        //上传车辆图片返回图片地址
        String uploadImage = ossConfig.uploadImage(file);
        //发送图片地址获取车牌信息
        Plates plantInfo = osrConfig.getPlants(uploadImage);
        if (plantInfo.getTxt()!=null){
            return new Result().result200(plantInfo,path);
        }
        return new Result().result500("识别失败",path);
    }

    /**
     * <p>业主添加所属车辆</p>
     * @author tfj
     * @since 2021/7/14
     */
    @Override
    public Result addOwnerCar(MultipartFile file, Long ownerId, String path) throws Exception {
        String uploadImage = ossConfig.uploadImage(file);
        Plates plants = osrConfig.getPlants(uploadImage);
        //查询是否是相同车牌
        Car selectOne = carMapper.selectOne(new QueryWrapper<Car>().eq("carNumber", plants.getTxt()));
        if (selectOne!=null){
            return new Result().result500("已有车辆，无法添加",path);
        }
        Car car=new Car();
        car.setId(wxConfig.getId());
        car.setOwnerId(ownerId);
        car.setCarNumber(plants.getTxt());
        car.setCarInfo(plants.getClsName());
        car.setCarImageUrl(uploadImage);
        car.setCreateTime(new Date());
        if (carMapper.insert(car)==1){
            return new Result().result200("业主添加所属车辆成功",path);
        }
        return new Result().result500("业主添加所属车辆失败",path);
    }
    /**
     * <p>判断是否是本小区汽车</p>
     * @author tfj
     * @since 2021/7/14
     */
    @Override
    public Result judgeCarNumber(MultipartFile file, String path) throws Exception {
        String s = ossConfig.uploadImage(file);
        Plates plants = osrConfig.getPlants(s);
        if (carMapper.judgeCarNumber(plants.getTxt())>0){
            return new Result().result200(true,path);
        }
        return new Result().result200(false,path);
    }
    /**
     * <p>获取业主车辆信息</p>
     * @author tfj
     * @since 2021/7/14
     */
    @Override
    public Result getOwnerCarInfo(Long ownerId, String path) {
        List<GetOwnerCarInfoVo> ownerCarInfo = carMapper.getOwnerCarInfo(ownerId);
        if (ownerCarInfo!=null){
            return new Result().result200(ownerCarInfo,path);
        }
        return new Result().result500("获取失败",path);
    }
}
