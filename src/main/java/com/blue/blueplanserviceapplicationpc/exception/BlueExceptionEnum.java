package com.blue.blueplanserviceapplicationpc.exception;

/**
 * 枚举 异常
 */
public enum BlueExceptionEnum {

    NEED_USER_NAME(404, "用户名不能为空"),
    NEED_PASSWORD(404, "密码不能为空"),
    PASSWORD_TOO_SHORT(404, "密码长度不能小于8位"),
    NAME_EXISTED(404, "不允许重名"),
    INSERT_FAILED(404, "插入失败，请重试"),
    WRONG_PASSWORD(404, "密码错误"),
    NEED_LOGIN(404, "用户未登录");

    /**
     * 异常码
     */
    Integer code;
    /**
     * 异常信息
     */
    String msg;

    BlueExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
