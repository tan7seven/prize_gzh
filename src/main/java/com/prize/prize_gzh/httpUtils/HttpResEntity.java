package com.prize.prize_gzh.httpUtils;

import java.net.HttpURLConnection;

/**
 * http请求返回的实�?
 */
public class HttpResEntity {
    /**
     * http请求返回的状态码 如：请求成功返回 200
     */
    private int resCode;
    /**
     * http请求返回的信息
     */
    private String resContent;

    public HttpResEntity() {
        this.setResCode(HttpURLConnection.HTTP_NOT_FOUND);
        this.setResContent("");
    }

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResContent() {
        return resContent;
    }

    public void setResContent(String resContent) {
        this.resContent = resContent;
    }

	@Override
	public String toString() {
		return "HttpResEntity [resCode=" + resCode + ", resContent="
				+ resContent + "]";
	}

}
