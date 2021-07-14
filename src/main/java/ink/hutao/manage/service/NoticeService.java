package ink.hutao.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.entity.dto.PostNoticeDto;
import ink.hutao.manage.entity.po.Notice;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p></p>
 * @author tfj
 * @since 2021/6/29
 */
public interface NoticeService extends IService<Notice> {
    /**
     * <p>获取所有公告信息</p>
     * @author tfj
     * @since 2021/6/29
     */
    Result getAllNotice(String path);
    /**
     * <p>发布公告</p>
     * @author tfj
     * @since 2021/6/29
     */
    Result releaseNotice(PostNoticeDto postNoticeDto, String path);
    /**
     * <p>修改公告</p>
     * @author tfj
     * @since 2021/6/29
     */
    Result updateNotice(Long id,PostNoticeDto postNoticeDto, String path);
    /**
     * <p>删除公告</p>
     * @author tfj
     * @since 2021/6/29
     */
    Result deleteNotice(Long id, String path);
    /**
     * <p>获取最新公告简略信息</p>
     * @author tfj
     * @since 2021/7/1
     */
    Result getNewestBriefInfo(String path);
}
