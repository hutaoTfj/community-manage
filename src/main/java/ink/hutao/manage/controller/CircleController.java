package ink.hutao.manage.controller;



import cn.dev33.satoken.annotation.SaCheckRole;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.entity.dto.PostCircleDto;
import ink.hutao.manage.service.CircleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/2
 */
@RestController
@CrossOrigin
@RequestMapping("/circle")
@Api(tags = "邻里圈")
public class CircleController {
    @Resource
    private CircleService circleService;
    /**
     * <p>发表圈子</p>
     * @author tfj
     * @since 2021/7/2
     */
    @ApiOperation(value = "发表圈子")
    @PostMapping("/publishedCircle")
    public Result publishedCircle(@RequestBody PostCircleDto postCircleDto, @RequestParam Long ownerId){
        return circleService.publishedCircle(postCircleDto,ownerId,"/circle/publishedCircle");
    }

    /**
     * <p>逻辑删除圈子</p>
     * @author tfj
     * @since 2021/7/2
     */
    @ApiOperation(value = "逻辑删除圈子")
    @SaCheckRole("admin")
    @DeleteMapping("/deleteCircle")
    public Result deleteCircle(@RequestParam Long id){
        return circleService.deleteCircle(id,"/circle/deleteCircle");
    }
    /**
     * <p>通过父id查找子圈子</p>
     * @author tfj
     * @since 2021/7/13
     */
    @ApiOperation(value = "通过父id查找子圈子")
    @GetMapping("/getChildCircleByParentId")
    public Result getChildCircleByParentId(@RequestParam Long parentId){
        return circleService.getChildCircleByParentId(parentId,"/circle/getChildCircleByParentId");
    }
    /**
     * <p>通过子id获取三级圈子</p>
     * @author tfj
     * @since 2021/7/13
     */
    @ApiOperation(value = "通过子id获取三级圈子")
    @GetMapping("/getThirdCircleByChildId")
    public Result getThirdCircleByChildId(@RequestParam Long childId){
        return circleService.getThirdCircleByChildId(childId,"/circle/getThirdCircleByChildId");
    }

}
