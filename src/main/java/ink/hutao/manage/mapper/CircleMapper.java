package ink.hutao.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.hutao.manage.entity.po.Circle;
import ink.hutao.manage.entity.vo.GetCircleVo;
import ink.hutao.manage.entity.vo.OwnerInfoVo;

import java.util.List;

public interface CircleMapper extends BaseMapper<Circle> {
    /**
     * <p>楼主圈子</p>
     * @author tfj
     * @since 2021/7/2
     */
    List<GetCircleVo> showParentCircle();
    /**
     * <p>获取发表用户信息</p>
     * @author tfj
     * @since 2021/7/2
     */
    OwnerInfoVo getChildUserInfo(Long id);
    /**
     * <p>N楼评论</p>
     * @author tfj
     * @since 2021/7/2
     */
    List<GetCircleVo> showChildCircle(Long parentId);
    /**
     * <p>楼主信息</p>
     * @author tfj
     * @since 2021/7/4
     */
    OwnerInfoVo getParentUserInfo(Long id);
    /**
     * <p>判断是否有子圈子</p>
     * @author tfj
     * @since 2021/7/5
     */
    int judgeChildCircle(Long id);

    /**
     * <p>判断是否有三级圈子</p>
     * @author tfj
     * @since 2021/7/5
     */
    int judgeThirdCircle(Long id);

}
