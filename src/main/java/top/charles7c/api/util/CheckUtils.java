package top.charles7c.api.util;

import cn.hutool.core.util.StrUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import top.charles7c.api.config.exception.BadRequestException;

import java.util.function.BooleanSupplier;

/**
 * 检查工具类
 *
 * @author Charles7c
 * @since 2022/3/19 17:25
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckUtils {

    /**
     * 如果对象为 null，抛出异常
     *
     * @param obj     对象
     * @param message 异常消息
     */
    public static void exceptionIfNull(Object obj, String message) {
        if (obj == null) {
            log.error(message);
            throw new BadRequestException(message);
        }
    }

    /**
     * 如果字符串为空，抛出异常
     *
     * @param str     字符串
     * @param message 异常消息
     */
    public static void exceptionIfBlank(String str, String message) {
        if (StrUtil.isBlank(str)) {
            log.error(message);
            throw new BadRequestException(message);
        }
    }

    /**
     * 如果条件成立，抛出异常
     *
     * @param supplier 提供者
     * @param message  异常消息
     */
    public static void exceptionIfCondition(BooleanSupplier supplier, String message) {
        if (supplier != null && supplier.getAsBoolean()) {
            log.error(message);
            throw new BadRequestException(message);
        }
    }
}
