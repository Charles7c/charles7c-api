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
        String visitorIp = ServletUtil.getClientIP(request);
        String address = IpUtil.getCityInfo(visitorIp);
        String browser = BrowserUtil.getBrowser(request);
        String referer = ServletUtil.getHeader(request, "referer", StandardCharsets.UTF_8);

        BlogPageView view = blogPageViewMapper.selectOne(Wrappers.<BlogPageView>lambdaQuery()
                .eq(BlogPageView::getPageUrl, pageUrl)
                .eq(BlogPageView::getVisitorIp, visitorIp)
                .eq(BlogPageView::getAddress, address)
                .eq(BlogPageView::getBrowser, browser)
                .eq(BlogPageView::getReferer, referer));

        if (view == null) {
            view = new BlogPageView();
            view.setPageUrl(pageUrl);
            view.setVisitorIp(visitorIp);
            view.setAddress(address);
            view.setBrowser(browser);
            view.setReferer(referer);
            view.setTimes(1);
            view.setCreateTime(new Date());
            blogPageViewMapper.insert(view);
        } else {
            blogPageViewMapper.update(null, Wrappers.<BlogPageView>lambdaUpdate()
                    .eq(BlogPageView::getId, view.getId())
                    .set(BlogPageView::getTimes, view.getTimes() + 1)
                    .set(BlogPageView::getUpdateTime, new Date()));
        }
    }

    @Override
    public Long getArticleViewCount(String articleId, String pageUrl) {
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        boolean needRecord = this.checkNeedRecord(request)
                && !blogProperties.getExcludePaths().contains(URLUtil.toURI(pageUrl).getPath());

        // 记录访问
        if (needRecord) {
            String visitorIp = ServletUtil.getClientIP(request);
            String address = IpUtil.getCityInfo(visitorIp);
            String browser = BrowserUtil.getBrowser(request);
            String referer = ServletUtil.getHeader(request, "referer", StandardCharsets.UTF_8);

            BlogArticleView view = blogArticleViewMapper.selectOne(Wrappers.<BlogArticleView>lambdaQuery()
                    .eq(BlogArticleView::getArticleId, articleId)
                    .eq(BlogArticleView::getVisitorIp, visitorIp)
                    .eq(BlogArticleView::getAddress, address)
                    .eq(BlogArticleView::getBrowser, browser)
                    .eq(BlogArticleView::getPageUrl, pageUrl)
                    .eq(BlogArticleView::getReferer, referer));

            if (view == null) {
                view = new BlogArticleView();
                view.setArticleId(articleId);
                view.setVisitorIp(visitorIp);
                view.setAddress(address);
                view.setBrowser(browser);
                view.setReferer(referer);
                view.setTimes(1);
                view.setPageUrl(pageUrl);
                view.setCreateTime(new Date());
                blogArticleViewMapper.insert(view);
            } else {
                blogArticleViewMapper.update(null, Wrappers.<BlogArticleView>lambdaUpdate()
                        .eq(BlogArticleView::getId, view.getId())
                        .set(BlogArticleView::getTimes, view.getTimes() + 1)
                        .set(BlogArticleView::getUpdateTime, new Date()));
            }
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
