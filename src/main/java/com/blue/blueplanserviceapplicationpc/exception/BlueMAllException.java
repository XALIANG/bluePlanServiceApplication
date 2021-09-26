package com.blue.blueplanserviceapplicationpc.exception;

/**
 *  统一异常
 */
public class BlueMAllException  extends  Exception{

    private  final  Integer code;

    private  final  String message;

    public BlueMAllException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BlueMAllException(BlueExceptionEnum exceptionEnum){

        this(exceptionEnum.getCode(), exceptionEnum.getMsg());

    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
