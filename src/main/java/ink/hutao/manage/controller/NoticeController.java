package ink.hutao.manage.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.entity.dto.PostNoticeDto;
import ink.hutao.manage.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * <p>前端控制器</p>
 * @author tfj
 * @since 2021/6/29
 */
@RestController
@CrossOrigin
@RequestMapping("/notice")
@Api(tags = "公告")
public class NoticeController {
    @Resource
    private NoticeService noticeService;
    /**
     * <p>获取所有公告信息</p>
     * @author tfj
     * @since 2021/6/29
     */
    @ApiOperation(value = "获取所有公告信息")
    @GetMapping("/getAllNotice")
    public Result getAllNotice(){
        return noticeService.getAllNotice("/notice/getAllNotice");
    }
    /**
     * <p>发布公告</p>
     * @author tfj
     * @since 2021/6/29
     */
    @ApiOperation(value = "发布公告")
    @SaCheckRole("admin")
    @PostMapping("/releaseNotice")
    public Result releaseNotice(@RequestBody PostNoticeDto postNoticeDto){
        return noticeService.releaseNotice(postNoticeDto,"/notice/releaseNotice");
    }
    /**
     * <p>修改公告</p>
     * @author tfj
     * @since 2021/6/29
     */
    @ApiOperation(value = "修改公告")
    @SaCheckRole("admin")
    @PutMapping("/updateNotice")
    public Result updateNotice(@RequestParam Long id,@RequestBody PostNoticeDto postNoticeDto){
        return noticeService.updateNotice(id,postNoticeDto,"/notice/updateNotice");
    }
    /**
     * <p>删除公告</p>
     * @author tfj
     * @since 2021/6/29
     */
    @ApiOperation(value = "删除公告")
    @SaCheckRole("admin")
    @DeleteMapping("deleteNotice")
    public Result deleteNotice(@RequestParam Long id){
        return noticeService.deleteNotice(id,"/notice/deleteNotice");
    }

    /**
     * <p>获取最新公告简略信息</p>
     * @author tfj
     * @since 2021/7/1
     */
    @ApiOperation(value = "获取最新公告简略信息")
    @GetMapping("/getNewestBriefInfo")
    public Result getNewestBriefInfo(){
        return noticeService.getNewestBriefInfo("/notice/getNewestBriefInfo");
    }

}
