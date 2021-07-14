package ink.hutao.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.config.OssConfig;
import ink.hutao.manage.config.WxConfig;
import ink.hutao.manage.entity.dto.PostBannerDto;
import ink.hutao.manage.entity.po.Banner;
import ink.hutao.manage.mapper.BannerMapper;
import ink.hutao.manage.service.BannerService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p></p>
 *
 * @author tfj
 * @since 2021/6/29
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {
    @Resource
    private BannerMapper bannerMapper;
    @Resource
    private WxConfig wxConfig;
    @Resource
    private OssConfig ossConfig;

    @Override
    public Result getAllBanner(String path) {
        List<Banner> banners = bannerMapper.selectList(new QueryWrapper<>());
        return new Result().result200(banners, path);
    }

    /**
     * <p>添加轮播图</p>
     *
     * @author tfj
     * @since 2021/6/29
     */
    @Override
    public Result addBanner(PostBannerDto postBannerDto, String path) {
        Banner banner = new Banner();
        banner.setId(wxConfig.getId());
        banner.setBannerContext(postBannerDto.getBannerContext());
        banner.setBannerUrl(postBannerDto.getBannerUrl());
        banner.setCreateTime(new Date());
        if (bannerMapper.insert(banner) == 1) {
            return new Result().result200("添加轮播图成功", path);
        }
        return new Result().result500("添加轮播图失败，请联系开发者", path);
    }
    /**
     * <p>修改轮播图</p>
     * @author tfj
     * @since 2021/6/29
     */
    @Override
    public Result updateBanner(PostBannerDto postBannerDto,Long id, String path) {
        Banner banner = bannerMapper.selectById(id);
        banner.setBannerContext(postBannerDto.getBannerContext());
        banner.setBannerUrl(postBannerDto.getBannerUrl());
        banner.setModifyTime(new Date());
        if (bannerMapper.updateById(banner)==1){
            return new Result().result200("修改轮播图成功", path);
        }
        return new Result().result500("修改轮播图失败", path);
    }
    /**
     * <p>删除轮播图</p>
     * @author tfj
     * @since 2021/6/29
     */
    @Override
    public Result deleteBanner(Long id, String path) {
        if (bannerMapper.deleteById(id)==1){
            return new Result().result200("逻辑删除轮播图成功", path);
        }
        return new Result().result200("逻辑删除轮播图失败", path);
    }
}
