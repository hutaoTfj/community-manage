package ink.hutao.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoTools.core.result.Result;
import ink.hutao.manage.entity.dto.PostBannerDto;
import ink.hutao.manage.entity.po.Banner;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p></p>
 * @author tfj
 * @since 2021/6/29
 */
public interface BannerService extends IService<Banner> {
    /**
     * <p>获取所有轮播图</p>
     * @author tfj
     * @since 2021/6/29
     */
    Result getAllBanner(String path);
    /**
     * <p>添加轮播图</p>
     * @author tfj
     * @since 2021/6/29
     */
    Result addBanner(PostBannerDto postBannerDto, String path);
    /**
     * <p>修改轮播图</p>
     * @author tfj
     * @since 2021/6/29
     */
    Result updateBanner(PostBannerDto postBannerDto,Long id, String path);
    /**
     * <p>删除轮播图</p>
     * @author tfj
     * @since 2021/6/29
     */
    Result deleteBanner(Long id, String path);
}
