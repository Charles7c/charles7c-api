package top.charles7c.api.enums;

import java.io.Serializable;

/**
 * 返回码接口
 *
 * @author Charles7c
 * @since 2022/7/30 23:29
 */
public interface IResultCode extends Serializable {
    int getCode();

    default String getMsg() {
        return "系统未知异常";
    }
}