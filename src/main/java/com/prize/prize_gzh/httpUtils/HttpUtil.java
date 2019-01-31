package com.prize.prize_gzh.httpUtils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
********************************************************** 
* @作者: Administrator
* @日期: 2015年9月11日
* @版权: 2015 www.shuzun.net Inc. All rights reserved.
* @描述: 
**********************************************************
 */
@SuppressWarnings("deprecation")
public class HttpUtil {

    protected static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 使用Get方式获取数据
     * @param url url
     * @param paramMap 参数（如果url中已包含参数则可以为null）
     * @param header 消息头（可以为null）
     * @return
     */
    public static HttpResEntity sendGet(String url, Map<String, String> paramMap, Map<String, String> header) {

        HttpResEntity resEntity = new HttpResEntity();
        resEntity.setResCode(404); // 默认返回404错误
        String urlStr = url;
        try {
            if (null != paramMap && !paramMap.isEmpty()) {
                StringBuffer sb = new StringBuffer();
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    sb.append("&").append(entry.getKey()).append("=")
                            .append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                }
                if (sb.length() > 0) {
                    sb.delete(0, 1);
                    urlStr += "?" + sb.toString();
                }
            }

            @SuppressWarnings("resource")
			HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(urlStr);

            // 设置消息头
            if (null != header && !header.isEmpty()) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }

            HttpResponse response = client.execute(httpGet);
            if (null != response) {
                resEntity.setResCode(response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    resEntity.setResContent(EntityUtils.toString(entity, "UTF-8"));
                }
            }
        } catch (Exception e) {
            logger.error("****** 发送get请求出错：" + e.getMessage());
            e.printStackTrace();
        }
        return resEntity;
    }

    /**
     * POST请求，json形式数据
     * @param url 请求地址
     * @param param 请求数据json格式
     * @param header 消息头 没有可为null
     */
    public static HttpResEntity sendPostJson(String url, String param, Map<String, String> header) {

        HttpResEntity resEntity = new HttpResEntity();
        resEntity.setResCode(404); // 默认返回404错误

        try {
            @SuppressWarnings("resource")
			HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            // 设置消息头
            if (null != header && !header.isEmpty()) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }

            // 设置参数
            StringEntity stringEntity = new StringEntity(param, ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);

            HttpResponse response = client.execute(httpPost);
            if (null != response) {
                resEntity.setResCode(response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    resEntity.setResContent(EntityUtils.toString(entity, "UTF-8"));
                }
            }
        } catch (Exception e) {
            logger.error("****** 发送post请求出错：" + e.getMessage());
            e.printStackTrace();
        }

        return resEntity;
    }

    /**
     * POST请求，Map形式数据
     * @param url 请求地址
     * @param param 请求数据
     */
    public static HttpResEntity sendPost(String url, Map<String, String> param, Map<String, String> header) {

        HttpResEntity resEntity = new HttpResEntity();
        resEntity.setResCode(404); // 默认返回404错误

        try {
            @SuppressWarnings("resource")
			HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            // 设置消息头
            if (null != header && !header.isEmpty()) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }

            // 设置参数
            if (null != param && !param.isEmpty()) {
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : param.entrySet()) {
                    NameValuePair valuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
                    list.add(valuePair);
                }
                if (!list.isEmpty()) {
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
                    httpPost.setEntity(entity);
                }
            }

            HttpResponse response = client.execute(httpPost);
            if (null != response) {
                resEntity.setResCode(response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    resEntity.setResContent(EntityUtils.toString(entity, "UTF-8"));
                }
            }
        } catch (Exception e) {
            logger.error("****** 发送post请求出错：" + e.getMessage());
            e.printStackTrace();
        }
        return resEntity;
    }

    public static JSONObject doGetJson(String url) throws IOException {
        JSONObject jsonObject = null;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity =  response.getEntity();
        if(null != entity){
            String result = EntityUtils.toString(entity,"UTF-8");
            jsonObject = JSONObject.parseObject(result);
        }
        httpGet.releaseConnection();
        return jsonObject;
    }
}
