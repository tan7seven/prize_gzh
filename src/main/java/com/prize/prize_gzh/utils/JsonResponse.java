package com.prize.prize_gzh.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class JsonResponse implements Serializable {
    private static final long serialVersionUID = 7045589773258130512L;

    private int code; // 返回码，通常0代表无错误，其它正整数值表示出现错误
    private String msg; // 通知最终用户的描述文字，直接展示给用户
    private Serializable data; // 附加的数据对象，需要实现可序列化接口，常用的如String等直接传进来即可

    public JsonResponse() {
        this.code = 0;
    }

    public JsonResponse(int code, String msg, Serializable data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public JsonResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Serializable getData() {
        return data;
    }

    public void setData(Serializable data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String toJSONString() {
        return JSONObject.toJSON(this).toString();

    }
}
