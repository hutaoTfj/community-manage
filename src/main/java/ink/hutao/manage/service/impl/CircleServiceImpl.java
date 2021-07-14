package ink.hutao.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.config.WxConfig;
import ink.hutao.manage.entity.dto.PostCircleDto;
import ink.hutao.manage.entity.po.Circle;
import ink.hutao.manage.entity.vo.GetCircleVo;
import ink.hutao.manage.mapper.CircleMapper;
import ink.hutao.manage.service.CircleService;
import ink.hutao.manage.utils.BeansUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p></p>
 * @author tfj
 * @since 2021/7/2
 */
@Service
public class CircleServiceImpl extends ServiceImpl<CircleMapper, Circle> implements CircleService {
    @Resource
    private CircleMapper circleMapper;
    @Resource
    private WxConfig wxConfig;
    /**
     * <p>发表圈子</p>
     * @author tfj
     * @since 2021/7/2
     */
    @Override
    public Result publishedCircle(PostCircleDto postCircleDto, Long ownerId, String path) {
        Circle circle = BeansUtils.beanCopy(postCircleDto, Circle.class);
        circle.setId(wxConfig.getId());
        circle.setOwnerId(ownerId);
        circle.setCreateTime(new Date());
        if (circleMapper.insert(circle)==1){
            return new Result().result200("发表圈子成功",path);
        }
        return new Result().result500("发表失败",path);
    }
    /**
     * <p>圈子展示</p>
     * @author tfj
     * @since 2021/7/2
     */
    @Override
    public List<GetCircleVo> showCircles() {
        List<GetCircleVo> parentCircle = circleMapper.showParentCircle();
        for (int i=0;i<parentCircle.size(); i++){
            GetCircleVo getParentCircleVo = parentCircle.get(i);
            getParentCircleVo.setOriginalPoster(circleMapper.getParentUserInfo(getParentCircleVo.getId()));
            Collections.replaceAll(parentCircle,parentCircle.get(i),getParentCircleVo);
        }
        return parentCircle;
    }
    /**
     * <p>子圈子展示</p>
     * @author tfj
     * @since 2021/7/2
     */
    @Override
    public List<GetCircleVo> showChildCircles(Long parentId) {
        List<GetCircleVo> getChildCircleVo=circleMapper.showChildCircle(parentId);

        for (int j=0;j<getChildCircleVo.size();j++){
            GetCircleVo childCircleVo = getChildCircleVo.get(j);
            childCircleVo.setChildPoster(circleMapper.getChildUserInfo(childCircleVo.getId()));
            if (this.judgeChildCircle(childCircleVo.getId())){
                childCircleVo.setChildrenList(this.showThirdCircle(childCircleVo.getId()));
            }
            Collections.replaceAll(getChildCircleVo,getChildCircleVo.get(j),childCircleVo);
        }
        return getChildCircleVo;
    }
    /**
     * <p>三级圈子展示</p>
     * @author tfj
     * @since 2021/7/4
     */
    @Override
    public  List<GetCircleVo> showThirdCircle(Long parentId) {
        List<GetCircleVo> getChildCircle=circleMapper.showChildCircle(parentId);
        for (int j=0;j<getChildCircle.size();j++){
            GetCircleVo childCircleVo = getChildCircle.get(j);
            childCircleVo.setChildPoster(circleMapper.getChildUserInfo(childCircleVo.getId()));
            Collections.replaceAll(getChildCircle,getChildCircle.get(j),childCircleVo);
        }
        return getChildCircle;
    }
    /**
     * <p>判断是否有子评论</p>
     * @author tfj
     * @since 2021/7/5
     */
    @Override
    public boolean judgeChildCircle(Long id) {
        return circleMapper.judgeChildCircle(id) != 0;
    }

    /**
     * <p></p>
     * @author tfj
     * @since 2021/7/2
     */
    @Override
    public Result deleteCircle(Long id, String path) {
        if (circleMapper.deleteById(id) == 1) {
            return new Result().result200("删除成功", path);
        }
        return new Result().result500("删除失败",path);
    }
    /**
     * <p>判断是否有三级圈子</p>
     * @author tfj
     * @since 2021/7/5
     */
    @Override
    public boolean judgeThirdCircle(Long id) {
        return circleMapper.judgeThirdCircle(id) != 0;
    }
    /**
     * <p></p>
     * @author tfj
     * @since 2021/7/13
     */
    @Override
    public Result getChildCircleByParentId(Long parentId, String path) {
        List<GetCircleVo> getCircleVos = this.showChildCircles(parentId);
        return new Result().result200(getCircleVos,path);
    }
    /**
     * <p>通过子id获取三级圈子</p>
     * @author tfj
     * @since 2021/7/13
     */
    @Override
    public Result getThirdCircleByChildId(Long childId, String path) {
        List<GetCircleVo> getCircleVos = this.showThirdCircle(childId);
        return new Result().result200(getCircleVos,path);
    }
}
