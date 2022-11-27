package top.charles7c.api.blog.pojo.vo;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 响应信息
 *
 * @author Charles7c
 * @since 2022/9/4 20:40
 */
@Data
@Schema(description = "响应信息")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class R<V extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否成功
     */
    @Schema(description = "是否成功")
    private boolean success;
    /**
     * 状态码
     */
    @Schema(description = "状态码")
    private int code;
    /**
     * 状态信息
     */
    @Schema(description = "状态信息")
    private String msg;
    /**
     * 返回数据
     */
    @Schema(description = "返回数据")
    private V data;
    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    /** 成功状态码 */
    private static final int SUCCESS_CODE = HttpStatus.HTTP_OK;
    /** 失败状态码 */
    private static final int FAIL_CODE = HttpStatus.HTTP_INTERNAL_ERROR;

    private R(boolean success, int code, String msg, V data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <V extends Serializable> R<V> ok() {
        return new R<>(true, SUCCESS_CODE, "操作成功", null);
    }

    public static <V extends Serializable> R<V> ok(V data) {
        return new R<>(true, SUCCESS_CODE, "操作成功", data);
    }

    public static <V extends Serializable> R<V> ok(String msg) {
        return new R<>(true, SUCCESS_CODE, msg, null);
    }

    public static <V extends Serializable> R<V> ok(String msg, V data) {
        return new R<>(true, SUCCESS_CODE, msg, data);
    }

    public static <V extends Serializable> R<V> fail() {
        return new R<>(false, FAIL_CODE, "操作失败", null);
    }

    public static <V extends Serializable> R<V> fail(String msg) {
        return new R<>(false, FAIL_CODE, msg, null);
    }

    public static <V extends Serializable> R<V> fail(V data) {
        return new R<>(false, FAIL_CODE, "操作失败", data);
    }

    public static <V extends Serializable> R<V> fail(String msg, V data) {
        return new R<>(false, FAIL_CODE, msg, data);
    }

    public static <V extends Serializable> R<V> fail(int code, String msg) {
        return new R<>(false, code, msg, null);
    }
}
