package top.charles7c.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 系统返回码枚举
 *
 * @author Charles7c
 * @since 2022/7/30 23:03
 */
@Getter
@RequiredArgsConstructor
public enum  SystemCodeEnum {

    FAILURE(-1, "系统未知异常"),
    SUCCESS(1, "操作成功"),
    PARAM_MISS(100000, "缺少必要的请求参数"),
    PARAM_TYPE_ERROR(100001, "请求参数类型错误"),
    PARAM_BIND_ERROR(100002, "请求参数绑定错误"),
    PARAM_VALID_ERROR(100003, "参数校验失败"),
    NOT_FOUND(100004, "404 没找到请求"),
    MSG_NOT_READABLE(100005, "消息不能读取"),
    METHOD_NOT_SUPPORTED(100006, "不支持当前请求方法"),
    MEDIA_TYPE_NOT_SUPPORTED(100007, "不支持当前媒体类型"),
    MEDIA_TYPE_NOT_ACCEPT(100008, "不接受的媒体类型"),
    REQ_REJECT(100009, "请求被拒绝"),
    BAD_GATEWAY(100010, "Bad gateway"),
    SERVICE_UNAVAILABLE(100011, "Service unavailable"),
    GATEWAY_TIMEOUT(100012, "Gateway timeout"),
    GATEWAY_FORBADE(100013, "Gateway forbade"),
    DATA_NOT_EXIST(100100, "数据不存在"),
    DATA_EXISTED(100101, "数据已存在"),
    DATA_ADD_FAILED(100102, "数据添加失败"),
    DATA_UPDATE_FAILED(100103, "数据更新失败"),
    DATA_DELETE_FAILED(100104, "数据删除失败");

    /** 返回码 */
    private final int code;
    /** 返回消息 */
    private final String msg;
}