package com.prize.prize_gzh.utils;

import com.alibaba.fastjson.JSONObject;
import com.prize.prize_gzh.dto.WxCheckDto;
import com.prize.prize_gzh.httpUtils.HttpResEntity;
import com.prize.prize_gzh.httpUtils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信接口工具类
 */
public class WxCheckUtil {

    private static final Logger logger = LoggerFactory.getLogger(WxCheckUtil.class);
    /**
     * access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token。
     */
    public static Map<String, Object> accessTokenMap = new HashMap<>();
    /**
     * accessTokenMap的key
     */
    public static final String accessTokenKry = "access_token";
    /**
     * expireInMap的key
     */
    public static final String expireInKry = "expires_in";
    /**
     * APP_ID：开发者ID是公众号开发识别码，配合开发者密码可调用公众号的接口能力。
     */
    public static final String APP_ID = "wxcf40f6ff5cf4686b";
    /**
     * 开发者密码
     */
    public static final String APP_SECRET = "06a0d92c255a1afd3d41dcaeaeb34bdf";
    /**
     * 微信网页授权域名
     */
    public static final String BACK_URL = "http://www.fujianshunfengche.cn";
    /**
     * 不弹出授权页面，直接跳转，只能获取用户openid
     */
    public static final String  SCOPE_BASE = "snsapi_base";
    /**
     * 弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息
     */
    public static final String  SCOPE_USERINFO = "snsapi_userinfo";
    /**
     * 服务器认证TOKEN
     */
    private static final String TOKEN = "WEIJIAZHENG";
    /**
     * session保存用户的kry值
     */
    public static final String SESSION_KEY = "user";
    /**
     * 微信开发模式接入验证
     * @param wxCheckDto
     * @return
     */
    public static boolean wxCheck(WxCheckDto wxCheckDto){
        String signature = wxCheckDto.getSignature();
        String timestamp = wxCheckDto.getTimestamp();
        String nonce = wxCheckDto.getNonce();
        String[] arr = new String[]{TOKEN, timestamp,nonce };
        //排序
        Arrays.sort(arr);
        //生成字符串
        StringBuffer content = new StringBuffer();
        for (String ar : arr) {
            content.append(ar);
        }
        //SHA1加密
        String temp = SHA1Util.encodeSHA1(content.toString());
        logger.info(temp);
        return  temp.equals(signature);
    }

    /**
     * 微信网页授权登录：1.用户同意授权，获取code
     * @param backUrl
     * @return
     */
    public static String getCode(String backUrl, String scope){
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=" + APP_ID +
                "&redirect_uri=" + URLEncoder.encode(backUrl) +
                "&response_type=code" +
                "&scope=" +scope +
                "&state=STATE#wechat_redirect";
        return url;
    }

    /**
     * 微信网页授权登录：2.通过code换取网页授权access_token（与基础支持中的access_token不同）
     * @param code
     * @return
     */
    public static String codeToAccessToken(String code){
        String url ="https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + APP_ID +
                "&secret=" + APP_SECRET +
                "&code=" + code +
                "&grant_type=authorization_code";
        return url;
    }

    /**
     * 获取accessToken
     * @return
     */
    public static JSONObject getAccessToken() throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/token?" +
                "grant_type=client_credential" +
                "&appid=" + APP_ID +
                "&secret="+ APP_SECRET;
        JSONObject resAccessToken = HttpUtil.doGetJson(url);
        return resAccessToken;
    }

    /**
     * 客户消息URL
     * @return
     * @throws IOException
     */
    public static String serviceInfoUrl(String accessToken) throws IOException {
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+accessToken;
        return url;
    }

    /**
     * 客服接口-发消息：发送文本消息
     * @param openId
     * @return
     * @throws IOException
     */
    public static HttpResEntity sendTextServiceInfo(String openId, String msg, String accessToken) throws IOException {
        //调用：客服接口-发消息：发送文本消息
        String urlServiceInfo = serviceInfoUrl(accessToken);
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, String> headerMap = new HashMap<>();
        paramMap.put("access_token", accessToken);
        paramMap.put("touser", openId);
        paramMap.put("appid", APP_ID);
        paramMap.put("msgtype", "text");
        Map<String, String> textMap = new HashMap<>();
        paramMap.put("text", textMap);
        textMap.put("content", msg);
        return HttpUtil.sendPostJson(urlServiceInfo,JSONObject.toJSONString(paramMap),headerMap);
    }
}
