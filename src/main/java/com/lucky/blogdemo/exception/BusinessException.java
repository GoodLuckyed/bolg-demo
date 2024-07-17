package com.lucky.blogdemo.exception;

import com.lucky.blogdemo.common.ErrorCode;

/**
 * 自定义异常类
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 7494283428277838049L;

    private final int code;

    public BusinessException(int code,String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode,String message){
        super(message);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
