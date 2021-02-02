package com.hoperun.pesystem.exception;


public enum CustomizeErrorCode implements ICustomizeErrorCode {
    LOGIN_FAILED(4001, "登录失败，密码或账号错误！"),
    REGISTER_FAILED(4002, "该手机号码已经注册！"),

    ;

    private final Integer code;
    private final String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
