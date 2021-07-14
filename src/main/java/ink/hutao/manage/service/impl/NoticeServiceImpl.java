package ink.hutao.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.config.OssConfig;
import ink.hutao.manage.config.WxConfig;
import ink.hutao.manage.entity.dto.PostNoticeDto;
import ink.hutao.manage.entity.po.Notice;
import ink.hutao.manage.mapper.NoticeMapper;
import ink.hutao.manage.service.NoticeService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p></p>
 * @author tfj
 * @since 2021/6/29
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    @Resource
    private NoticeMapper noticeMapper;
    @Resource
    private WxConfig wxConfig;
    @Resource
    private OssConfig ossConfig;
    /**
     * <p>获取所有公告信息</p>
     * @author tfj
     * @since 2021/6/29
     */
    @Override
    public Result getAllNotice(String path) {
        List<Notice> notices = noticeMapper.selectList(new QueryWrapper<>());
        return new Result().result200(notices,path);
    }
    /**
     * <p>发布公告</p>
     * @author tfj
     * @since 2021/6/29
     */
    @Override
    public Result releaseNotice(PostNoticeDto postNoticeDto, String path) {
        Notice addNotice=new Notice();
        addNotice.setId(wxConfig.getId());
        addNotice.setCreateTime(new Date());
        addNotice.setHeadline(postNoticeDto.getHeadline());
        addNotice.setImageUrl(postNoticeDto.getImageUrl());
        addNotice.setNotice(postNoticeDto.getNotice());
        addNotice.setBriefInfo(postNoticeDto.getBriefInfo());
        if (noticeMapper.insert(addNotice)==1){
            return new Result().result200("发布公告成功",path);
        }
        return new Result().result500("发布错误，请联系开发人员",path);
    }
    /**
     * <p>修改公告</p>
     * @author tfj
     * @since 2021/6/29
     */
    @Override
    public Result updateNotice(Long id,PostNoticeDto postNoticeDto, String path) {
        Notice updateNotice = noticeMapper.selectById(id);
        updateNotice.setNotice(postNoticeDto.getNotice());
        updateNotice.setHeadline(postNoticeDto.getHeadline());
        updateNotice.setImageUrl(postNoticeDto.getImageUrl());
        updateNotice.setBriefInfo(postNoticeDto.getBriefInfo());
        updateNotice.setModifyTime(new Date());
        if (noticeMapper.updateById(updateNotice)==1){
            return new Result().result200("修改公告成功",path);
        }
        return new Result().result500("修改失败，请联系开发者",path);
    }
    /**
     * <p>删除公告</p>
     * @author tfj
     * @since 2021/6/29
     */
    @Override
    public Result deleteNotice(Long id, String path) {
        if (noticeMapper.deleteById(id)==1){
            return new Result().result200("逻辑删除成功",path);
        };
        return new Result().result500("逻辑删除失败，请联系开发者",path);
    }
    /**
     * <p>获取最新公告简略信息</p>
     * @author tfj
     * @since 2021/7/1
     */
    @Override
    public Result getNewestBriefInfo(String path) {
        QueryWrapper<Notice> queryWrapper= Wrappers.query();
        queryWrapper.orderByDesc("createTime").last("limit 1");
        Notice notice = noticeMapper.selectOne(queryWrapper);
        if (notice!=null){
            return new Result().result200(notice.getBriefInfo(),path);
        }
        return new Result().result500("请求出错",path);
    }
}
