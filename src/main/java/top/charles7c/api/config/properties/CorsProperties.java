package top.charles7c.api.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 跨域配置属性
 *
 * @author Charles7c
 * @since 2022/11/26 12:04
 */
@Data
@Component
@ConfigurationProperties(prefix = CorsProperties.PREFIX)
public class CorsProperties {

    static final String PREFIX = "cors";

    /**
     * 允许跨域的域名
     */
    private List<String> allowedOrigins;

    /**
     * 允许跨域的请求方式
     */
    private List<String> allowedMethods;

    /**
     * 允许跨域的请求头
     */
    private List<String> allowedHeaders;
}
