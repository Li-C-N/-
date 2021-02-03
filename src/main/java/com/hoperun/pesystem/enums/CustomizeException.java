package com.hoperun.pesystem.enums;


public class CustomizeException extends RuntimeException{
    Integer code;
    String message;

    public CustomizeException(CustomizeCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
