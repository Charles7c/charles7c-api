package top.charles7c.api.blog.service;

/**
 * 访客统计业务类
 *
 * @author Charles7c
 * @since 2022/7/28 23:52
 */
public interface VisitorStatisticsService {

    /**
     * 记录页面浏览量
     *
     * @param pageUrl 页面URL
     */
    void getPv(String pageUrl);

    /**
     * 记录, 并查询文章阅读数
     *
     * @param articleId 文章ID
     * @param pageUrl   页面URL
     * @return 阅读数
     */
    Long getArticleViewCount(String articleId, String pageUrl);
}
