package com.springcloud.common.result;

import java.io.Serializable;

public class Result<T> implements Serializable {

    // http 状态码
    private int code;

    // 返回信息
    private String msg;

    // 返回的数据
    private T data;

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

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(CommonStatusInterface code) {
        this(code, null);
    }

    public Result() {
    }

    public Result(CommonStatusInterface code, T data) {
        this(code.getCode(), code.getText(),data);
    }

    public Result(String msg) {
        this.msg = msg;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
