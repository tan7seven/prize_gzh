package com.prize.prize_gzh.utils;

import com.alibaba.fastjson.JSONObject;
import com.prize.prize_gzh.httpUtils.HttpUtil;

import java.io.IOException;

/**
 * 小程序工具类
 */
public class WxUtil {
    /**
     * AppID(小程序ID)
     */
    public static final String AppID = "wxaca16b7ed1c908f5";
    /**
     * AppSecret(小程序密钥)
     */
    public static final String AppSecret="ef00e1e9449a8891adce4d3d6b8391af";

    /**
     * 获取
     * @param code
     * @return
     */
    public String getOpenidUrl(String code){
        String openidUrl = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" +  AppID +
                "&secret=" + AppSecret +
                "&js_code=" + code +
                "&grant_type=authorization_code";
        return openidUrl;
    }

    /**
     * 获取openid
     * @param code
     * @return
     * @throws IOException
     */
    public JSONObject grtOpenid(String code) throws IOException {
        String openidUrl = getOpenidUrl(code);
        JSONObject result = HttpUtil.doGetJson(openidUrl);
        return result;
    }

}
