package top.charles7c.api.blog.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.charles7c.api.blog.mapper.BlogArticleViewMapper;
import top.charles7c.api.blog.mapper.BlogPageViewMapper;
import top.charles7c.api.blog.pojo.domain.BlogArticleView;
import top.charles7c.api.blog.pojo.domain.BlogPageView;
import top.charles7c.api.blog.service.VisitorStatisticsService;
import top.charles7c.api.config.properties.BlogProperties;
import top.charles7c.api.util.BrowserUtil;
import top.charles7c.api.util.CheckUtils;
import top.charles7c.api.util.IpUtil;
import top.charles7c.api.util.RequestHolder;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 访客统计业务实现类
 *
 * @author Charles7c
 * @since 2022/7/28 23:54
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VisitorStatisticsServiceImpl implements VisitorStatisticsService {

    private final BlogPageViewMapper blogPageViewMapper;
    private final BlogArticleViewMapper blogArticleViewMapper;
    private final BlogProperties blogProperties;

    @Override
    public void getPv(String pageUrl) {
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        boolean needRecord = this.checkNeedRecord(request);
        if (!needRecord) {
            return;
        }

        // 记录页面访问
        BlogPageView view = new BlogPageView();
        view.setPageUrl(pageUrl);
        view.setVisitorIp(ServletUtil.getClientIP(request));
        view.setAddress(IpUtil.getCityInfo(view.getVisitorIp()));
        view.setBrowser(BrowserUtil.getBrowser(request));
        view.setCreateTime(new Date());
        blogPageViewMapper.insert(view);
    }

    @Override
    public Long getArticleViewCount(String articleId, String pageUrl) {
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        boolean needRecord = this.checkNeedRecord(request)
                && !blogProperties.getExcludePaths().contains(URLUtil.toURI(pageUrl).getPath());

        // 记录访问
        if (needRecord) {
            BlogArticleView view = new BlogArticleView();
            view.setArticleId(articleId);
            view.setVisitorIp(ServletUtil.getClientIP(request));
            view.setAddress(IpUtil.getCityInfo(view.getVisitorIp()));
            view.setBrowser(BrowserUtil.getBrowser(request));
            view.setPageUrl(pageUrl);
            view.setCreateTime(new Date());
            blogArticleViewMapper.insert(view);
        }

        // 查询该文章的总阅读数
        return blogArticleViewMapper.selectCount(Wrappers.<BlogArticleView>query()
                .select("DISTINCT `visitor_ip`")
                .lambda()
                .eq(BlogArticleView::getArticleId, articleId));
    }

    /**
     * 检测是否需要记录访问
     *
     * @param request 请求信息
     * @return 是否需要记录访问
     */
    private boolean checkNeedRecord(HttpServletRequest request) {
        // 获取访客 IP
        String visitorIp = ServletUtil.getClientIP(request);
        String address = IpUtil.getCityInfo(visitorIp);
        if ("内网IP".equals(address)) {
            return false;
        }

        // 获取 referer
        String referer = ServletUtil.getHeader(request, "referer", StandardCharsets.UTF_8);
        CheckUtils.exceptionIfBlank(referer, "非法访问 referer");
        log.info("referer：{}", referer);
        if (StrUtil.containsAny(URLUtil.toURI(referer).getHost(), "localhost", "127.0.0.1", "192.168.")) {
            return false;
        }
        return true;
    }
}
