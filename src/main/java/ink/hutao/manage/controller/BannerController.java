package ink.hutao.manage.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.entity.dto.PostBannerDto;
import ink.hutao.manage.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * <p>前端控制器</p>
 *
 * @author tfj
 * @since 2021/6/29
 */
@RestController
@CrossOrigin
@RequestMapping("/banner")
@Api(tags = "轮播图")
public class BannerController {
    @Resource
    private BannerService bannerService;

    /**
     * <p>获取所有轮播图</p>
     * @author tfj
     * @since 2021/6/29
     */
    @ApiOperation(value = "获取所有轮播图")
    @GetMapping("/getAllBanner")
    public Result getAllBanner() {
        return bannerService.getAllBanner("/banner/getAllBanner");
    }
    /**
     * <p>添加轮播图</p>
     * @author tfj
     * @since 2021/6/29
     */
    @ApiOperation(value = "添加轮播图")
    @SaCheckRole("admin")
    @PostMapping("/addBanner")
    public Result addBanner(@RequestBody PostBannerDto postBannerDto){
        return bannerService.addBanner(postBannerDto,"/banner/addBanner");
    }

    /**
     * <p>修改轮播图</p>
     * @author tfj
     * @since 2021/6/29
     */
    @ApiOperation(value = "修改轮播图")
    @SaCheckRole("admin")
    @PutMapping("/updateBanner")
    public Result updateBanner(@RequestParam Long id,@RequestBody PostBannerDto postBannerDto){
        return bannerService.updateBanner(postBannerDto,id,"/banner/updateBanner");
    }
    /**
     * <p>逻辑删除轮播图</p>
     * @author tfj
     * @since 2021/6/29
     */
    @ApiOperation(value = "删除轮播图")
    @SaCheckRole("admin")
    @DeleteMapping("/deleteBanner")
    public Result deleteBanner(@RequestParam Long id){
        return bannerService.deleteBanner(id,"/banner/deleteBanner");
    }
}
