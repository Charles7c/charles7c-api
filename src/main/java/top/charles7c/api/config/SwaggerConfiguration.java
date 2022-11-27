package top.charles7c.api.config;

import cn.hutool.core.util.RandomUtil;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SpringDocConfiguration;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.charles7c.api.config.properties.SwaggerProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * API 接口文档配置
 * 接口文档访问地址：http://localhost:xxxx/doc.html
 *
 * @author Charles7c
 * @since 2022/9/24 22:47
 */
@Configuration
@RequiredArgsConstructor
@AutoConfigureBefore(SpringDocConfiguration.class)
@ConditionalOnProperty(prefix = "swagger", name = "enabled")
public class SwaggerConfiguration {

    private final SwaggerProperties swaggerProperties;

    /**
     * 接口文档配置
     */
    @Bean
    @ConditionalOnMissingBean(OpenAPI.class)
    public OpenAPI openAPI() {
        SwaggerProperties.InfoProperties info = swaggerProperties.getInfo();
        return new OpenAPI()
                // 基本信息配置
                .info(new Info()
                        .title(info.getTitle())
                        .version(info.getVersion())
                        .description(info.getDescription())
                        .contact(info.getContact()));
    }

    @Bean
    public GroupedOpenApi defaultApi() {
        return GroupedOpenApi.builder()
                .group("brand")
                .pathsToMatch("/**")
                .build();
    }

    /**
     * 根据@Tag 上的排序，写入x-order
     *
     * @return the global open api customizer
     */
    @Bean
    public GlobalOpenApiCustomizer orderGlobalOpenApiCustomizer() {
        return openApi -> {
            if (openApi.getTags() != null) {
                openApi.getTags().forEach(tag -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("x-order", RandomUtil.randomInt(0, 100));
                    tag.setExtensions(map);
                });
            }
        };
    }
}
