package top.charles7c.api;

import cn.hutool.core.convert.Convert;
import cn.hutool.extra.spring.SpringUtil;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.charles7c.api.config.properties.SystemProperties;
import top.charles7c.api.util.IpUtil;

import javax.annotation.Resource;

/**
 * 启动程序
 *
 * @author Charles7c
 * @since 2022/11/25 20:06
 */
@Hidden
@Slf4j
@EnableScheduling
@RestController
@SpringBootApplication
@Import(cn.hutool.extra.spring.SpringUtil.class)
@ComponentScan(basePackages = {"top.charles7c.api", "cn.hutool.extra.spring"})
public class Charles7cServiceApplication implements ApplicationRunner {

    @Resource
    private ServerProperties serverProperties;

    public static void main(String[] args) {
        SpringApplication.run(Charles7cServiceApplication.class, args);
    }

    /**
     * 访问首页提示
     *
     * @return /
     */
    @GetMapping("/")
    public String index() {
        return "Charles7c API started successfully.";
    }

    @Override
    public void run(ApplicationArguments args) {
        // 启动成功
        log.info("--------------------------------------");
        log.info(" {} v{} started.", SystemProperties.APP_NAME, SystemProperties.VERSION);
        log.info(" API：http://{}:{}", IpUtil.getLocalIp(), serverProperties.getPort());
        Boolean swaggerEnabled = Convert.toBool(SpringUtil.getProperty("swagger.enabled"));
        if (Boolean.TRUE.equals(swaggerEnabled)) {
            log.info(" 接口文档：http://{}:{}/doc.html", IpUtil.getLocalIp(), serverProperties.getPort());
        }
        log.info("--------------------------------------");
    }
}
