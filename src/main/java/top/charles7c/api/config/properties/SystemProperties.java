package top.charles7c.api.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 系统配置属性
 *
 * @author Charles7c
 * @since 2022/11/25 19:12
 */
@Data
@Component
@ConfigurationProperties(prefix = SystemProperties.PREFIX)
public class SystemProperties {

    static final String PREFIX = "system";

    /**
     * 项目名称
     */
    public static String NAME;

    public void setName(String name) {
        SystemProperties.NAME = name;
    }

    /**
     * 应用名称
     */
    public static String APP_NAME;

    public void setAppName(String appName) {
        SystemProperties.APP_NAME = appName;
    }

    /**
     * 版本
     */
    public static String VERSION;

    public void setVersion(String version) {
        SystemProperties.VERSION = version;
    }

    /**
     * 是否启用演示环境
     */
    public static boolean DEMO_ENABLED;

    public void setDemoEnabled(boolean demoEnabled) {
        SystemProperties.DEMO_ENABLED = demoEnabled;
    }

    /**
     * 是否本地解析 IP 归属地
     */
    public static boolean IP_ADDRESS_LOCAL_PARSE_ENABLED;

    public void setIpAddressLocalParseEnabled(boolean ipAddressLocalParseEnabled) {
        SystemProperties.IP_ADDRESS_LOCAL_PARSE_ENABLED = ipAddressLocalParseEnabled;
    }
}
