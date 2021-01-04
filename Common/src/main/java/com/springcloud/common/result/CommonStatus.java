package com.springcloud.common.result;

public enum  CommonStatus implements CommonStatusInterface{
    SUCCESS(200, "成功"),
    PARAM_ERROR(400, "参数错误"),
    NO_AUTHORITY(400500, "没有权限"),
    PASSWORD_ERROR(400501, "密码错误"),
    UNKNOWN_ERROR(400502, "用户暂未登录，请登录后重试"),
    NOT_LOGIN(400503, "暂未登录"),
    AUTHENTICATE_FAIL(400504, "无访问权限，请尝试登录或联系管理员。"),
    USER_NOT_EXIST(400505, "用户不存在，请检查账号是否输入正确"),
    ;


    private int code;
    private String text;


    CommonStatus(int value, String text) {
        this.code = code;
        this.text = text;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getText() {
        return text;
    }
}
