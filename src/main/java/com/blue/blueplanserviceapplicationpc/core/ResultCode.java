package com.blue.blueplanserviceapplicationpc.core;

public enum ResultCode  implements  IErrorCode{
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(400, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(402, "没有相关权限"),
    NO_USER_NAME(403,"用户名不存在"),
    FROZEN_USER(405,"用户已冻结"),
    CANCELLATION_USER(406,"用户已注销"),
    ERRPR_PASSWORD(407,"密码错误"),
    VOER_SHIROSESSION(408,"shiro的session过期");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
