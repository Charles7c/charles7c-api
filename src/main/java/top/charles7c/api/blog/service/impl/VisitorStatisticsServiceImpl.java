package top.charles7c.api.blog.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.charles7c.api.blog.mapper.VisitorStatisticsMapper;
import top.charles7c.api.blog.pojo.domain.VisitorStatistics;
import top.charles7c.api.blog.service.VisitorStatisticsService;
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

    private final VisitorStatisticsMapper visitorStatisticsMapper;

    @Override
    public Long getPv(String articleId) {
        // 1、如果来源是内网 IP 或 本地，不记录访问
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        // 获取访客 IP
        String visitorIp = ServletUtil.getClientIP(request);
        String address = IpUtil.getCityInfo(visitorIp);
        // 获取 referer
        String referer = ServletUtil.getHeader(request, "referer", StandardCharsets.UTF_8);
        CheckUtils.exceptionIfBlank(referer, "非法访问 referer");
        log.info("referer：{}", referer);
        boolean needRecord = !("内网IP".equals(address) || StrUtil.containsAny(URLUtil.toURI(referer).getHost(), "localhost", "127.0.0.1", "192.168."));

        // 2、记录访问
        if (needRecord) {
            VisitorStatistics visitorStatistics = new VisitorStatistics();
            visitorStatistics.setArticleId(articleId);
            visitorStatistics.setVisitorIp(visitorIp);
            visitorStatistics.setAddress(address);
            visitorStatistics.setBrowser(BrowserUtil.getBrowser(request));
            visitorStatistics.setReferer(referer);
            visitorStatistics.setCreateTime(new Date());
            visitorStatisticsMapper.insert(visitorStatistics);
        }

        // 3、查询该文章的总阅读数
        return visitorStatisticsMapper.selectCount(Wrappers.<VisitorStatistics>query()
                .select("DISTINCT `visitor_ip`")
                .lambda()
                .eq(VisitorStatistics::getArticleId, articleId));
    }
}
