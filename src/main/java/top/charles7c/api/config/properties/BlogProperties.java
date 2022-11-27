package top.charles7c.api.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 博客配置属性
 *
 * @author Charles7c
 * @since 2022/11/27 20:14
 */
@Data
@Component
@ConfigurationProperties(prefix = BlogProperties.PREFIX)
public class BlogProperties {

    static final String PREFIX = "blog.article.view";

    /**
     * 排除路径列表
     */
    private List<String> excludePaths;
}
