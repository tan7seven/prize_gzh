package com.prize.prize_gzh.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class JsonResponse implements Serializable {
    private static final long serialVersionUID = 7045589773258130512L;

    private int result; // 返回码，通常0代表无错误，其它正整数值表示出现错误
    private String info; // 通知最终用户的描述文字，直接展示给用户
    private Serializable data; // 附加的数据对象，需要实现可序列化接口，常用的如String等直接传进来即可

    public JsonResponse() {
        this.result = 0;
    }

    public JsonResponse(int result, String info, Serializable data) {
        this.result = result;
        this.info = info;
        this.data = data;
    }

    public JsonResponse(int result, String info) {
        this.result = result;
        this.info = info;
        this.data = null;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Serializable getData() {
        return data;
    }

    public void setData(Serializable data) {
        this.data = data;
    }
    public String toJSONString() {
        return JSONObject.toJSON(this).toString();

    }
}
