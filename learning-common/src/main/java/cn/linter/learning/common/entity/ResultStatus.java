package cn.linter.learning.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 响应状态枚举类
 *
 * @author wangxiaoyang
 * @date 2020/12/19
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResultStatus {

    /**
     * 响应状态
     */
    SUCCESS("0000", "操作成功"),
    UNAUTHORIZED("1000", "未授权"),
    FORBIDDEN("1001", "权限不足"),
    TOKEN_IS_INVALID("1002", "Token无效"),
    ARGUMENT_NOT_VALID("2000", "参数无效"),
    USER_NOT_FOUND("3000", "用户不存在"),
    USERNAME_ALREADY_EXISTS("3001", "用户名已存在");

    /**
     * 状态码
     */
    private final String code;
    /**
     * 状态信息
     */
    private final String message;

    ResultStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}