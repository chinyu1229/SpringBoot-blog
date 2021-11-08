package com.mszlu.blog.vo;

public enum  ErrorCode {

    PARAMS_ERROR(10001,"參數有誤"),
    ACCOUNT_PWD_NOT_EXIST(10002,"帳戶或密碼不存在"),
    TOKEN_ERROR(10003,"token不合法"),
    ACCOUNT_EXIST(10004,"帳號已存在"),
    NO_PERMISSION(70001,"無訪問權限"),
    SESSION_TIME_OUT(90001,"Session超時"),
    NO_LOGIN(90002,"未登入"),;

    private int code;
    private String msg;

    ErrorCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
