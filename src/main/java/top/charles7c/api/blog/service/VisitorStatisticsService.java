package top.charles7c.api.blog.service;

import java.util.Map;

/**
 * 访客统计业务类
 *
 * @author Charles7c
 * @since 2022/7/28 23:52
 */
public interface VisitorStatisticsService {

    /**
     * 记录, 并获取文章阅读数
     *
     * @param articleId 文章ID
     * @return 阅读数
     */
    Long getPv(String articleId);
}
