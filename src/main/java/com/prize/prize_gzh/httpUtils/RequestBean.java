package com.prize.prize_gzh.httpUtils;

import java.util.HashMap;
import java.util.Map;

public class RequestBean {

    private String url;
    private Map<String, String> header = new HashMap<String,String>();
    private Object params;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String toString() {
        return "url:" + url + ";header" + (header == null ? "" : header) + ";parameter:"
                + (params == null ? "" : (params instanceof Map ? (Map<String, String>) params : params.toString()));
    }
}
