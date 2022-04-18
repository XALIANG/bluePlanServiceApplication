package com.blue.blueplanserviceapplicationpc.common;

import com.blue.blueplanserviceapplicationpc.exception.BlueExceptionEnum;
import com.blue.blueplanserviceapplicationpc.exception.ResultCode;

/**
 * 通用返回结果
 */
public class Result<T> {

    private Integer status;

    private String msg;

    private T data;

    private static final int OK_CODE = 200;

    private static final int ERROR_CODE = 404;

    private static final String OK_MSG = "SUCCESS";

    private static final String ERROR_MSG = "ERROR";

    public Result(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Result() {
        this(OK_CODE, OK_MSG);
    }

    public static <T> Result<T> success() {
        return new Result<>();
    }

    public static <T> Result<T> success(String msg, int status) {
        Result<T> response = new Result<>();
        response.setMsg(msg);
        response.setStatus(OK_CODE);
        return response;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>((int) ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> success(String msg, T data) {
        Result<T> response = new Result<>();
        response.setMsg(msg);
        response.setData(data);
        return response;
    }


    private void status(int status) {
    }

    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<>(code, msg);
    }

    public static <T> Result<T> error(BlueExceptionEnum ex) {
        return new Result<>(ex.getCode(), ex.getMsg());
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static int getOkCode() {
        return OK_CODE;
    }

    public static String getOkMsg() {
        return OK_MSG;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }


}
