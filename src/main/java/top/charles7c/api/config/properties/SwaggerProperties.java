package top.charles7c.api.config.properties;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * Swagger 配置属性
 *
 * @author Charles7c
 * @since 2022/9/14 20:01
 */
@Data
@Component
@ConfigurationProperties(prefix = SwaggerProperties.PREFIX)
public class SwaggerProperties {

    static final String PREFIX = "swagger";

    /**
     * 是否启用接口文档
     */
    private Boolean enabled = false;

    /**
     * 接口文档基本信息配置
     */
    @NestedConfigurationProperty
    private InfoProperties info = new InfoProperties();

    /**
     * 接口文档基本信息配置属性
     */
    @Data
    public static class InfoProperties {

        /**
         * 标题
         */
        private String title;

        /**
         * 描述
         */
        private String description;

        /**
         * 版本
         */
        private String version;

        /**
         * 联系人信息
         */
        @NestedConfigurationProperty
        private Contact contact;

        /**
         * 许可协议
         */
        @NestedConfigurationProperty
        private License license;
    }
}
