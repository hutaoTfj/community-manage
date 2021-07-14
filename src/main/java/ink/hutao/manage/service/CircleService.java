package ink.hutao.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.entity.dto.PostCircleDto;
import ink.hutao.manage.entity.po.Circle;
import ink.hutao.manage.entity.vo.GetCircleVo;

import java.util.List;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/2
 */
public interface CircleService extends IService<Circle> {
    /**
     * <p>发表圈子</p>
     * @author tfj
     * @since 2021/7/2
     */
    Result
    publishedCircle(PostCircleDto postCircleDto, Long ownerId, String path);
    /**
     * <p>圈子展示</p>
     * @author tfj
     * @since 2021/7/2
     */
    List<GetCircleVo> showCircles();
    /**
     * <p>子圈子展示</p>
     * @author tfj
     * @since 2021/7/2
     */
    List<GetCircleVo> showChildCircles(Long parentId);
    /**
     * <p>删除圈子</p>
     * @author tfj
     * @since 2021/7/2
     */
    Result deleteCircle(Long id, String path);
    /**
     * <p>三级圈子展示</p>
     * @author tfj
     * @since 2021/7/4
     */
    List<GetCircleVo> showThirdCircle(Long parentId);
    /**
     * <p>判断是否有子评论</p>
     * @author tfj
     * @since 2021/7/5
     */
    boolean judgeChildCircle(Long id);
    /**
     * <p>判断是否有三级圈子</p>
     * @author tfj
     * @since 2021/7/5
     */
    boolean judgeThirdCircle(Long id);
    /**
     * <p>通过父id查找子圈子</p>
     * @author tfj
     * @since 2021/7/13
     */
    Result getChildCircleByParentId(Long parentId, String path);
    /**
     * <p>通过子id获取三级圈子</p>
     * @author tfj
     * @since 2021/7/13
     */
    Result getThirdCircleByChildId(Long childId, String path);
}
