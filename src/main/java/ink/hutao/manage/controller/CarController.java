package ink.hutao.manage.controller;

import com.xiaoTools.core.result.Result;
import ink.hutao.manage.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/14
 */
@CrossOrigin
@RestController
@RequestMapping("/car")
@Api(tags = "车辆")
public class CarController {
    @Resource
    private CarService carService;
    /**
     * <p>车牌号识别</p>
     * @author tfj
     * @since 2021/7/14
     */
    @ApiOperation(value = "车牌号识别")
    @PostMapping("/licensePlateIdentification")
    public Result licensePlateIdentification(@RequestPart MultipartFile file) throws Exception {
        return carService.licensePlateIdentification(file,"/car/licensePlateIdentification");
    }
    /**
     * <p>业主添加所属车辆</p>
     * @author tfj
     * @since 2021/7/14
     */
    @ApiOperation(value = "业主添加所属车辆")
    @PostMapping("/addOwnerCar")
    public Result addOwnerCar(@RequestPart MultipartFile file,Long ownerId) throws Exception {
        return carService.addOwnerCar(file,ownerId,"/car/addOwnerCar");
    }
    /**
     * <p>判断是否是本小区汽车</p>
     * @author tfj
     * @since 2021/7/14
     */
    @ApiOperation(value = "判断是否是本小区汽车")
    @PostMapping("/judgeCarNumber")
    public Result judgeCarNumber(@RequestPart MultipartFile file) throws Exception {
        return carService.judgeCarNumber(file,"/car/judgeCarNumber");
    }
    /**
     * <p>获取业主车辆信息</p>
     * @author tfj
     * @since 2021/7/14
     */
    @ApiOperation(value = "获取业主车辆信息")
    @GetMapping("/getOwnerCarInfo")
    public Result getOwnerCarInfo(@RequestParam Long ownerId){
        return carService.getOwnerCarInfo(ownerId,"/car/getOwnerCarInfo");
    }
}
