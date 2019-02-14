package com.prize.prize_gzh.controller;

import com.alibaba.fastjson.JSONObject;
import com.prize.prize_gzh.dto.WxCheckDto;
import com.prize.prize_gzh.httpUtils.HttpUtil;
import com.prize.prize_gzh.utils.JsonResponse;
import com.prize.prize_gzh.utils.WxCheckUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.pl.REGON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

import static com.prize.prize_gzh.utils.WxCheckUtil.getAccessToken;
import static com.prize.prize_gzh.utils.WxCheckUtil.getJsApiTicket;
import static com.prize.prize_gzh.utils.WxCheckUtil.makeWXTicket;

/**
 * 微信登录
 */
@Controller
@RequestMapping(value = "/wxLogin")
public class WxLoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(WxLoginController.class);

    //微信参数
    private String accessToken;
    private String jsApiTicket;
    //获取参数的时刻
    private Long getTiketTime = 0L;
    private Long getTokenTime = 0L;
    //参数的有效时间,单位是秒(s)
    private Long tokenExpireTime = 0L;
    private Long ticketExpireTime = 0L;

    /**
     * 微信服务器验证
     * @param dto
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/wxCheck.do")
    protected String wxCheck(WxCheckDto dto){
        if(WxCheckUtil.wxCheck(dto)){
            return  dto.getEchostr();
        }else{
            return null;
        }
    }
    /**
     * 微信登录获取code参数
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/getOpenid.do")
    protected void sendMsgLogin() throws IOException {
        String backUrl = WxCheckUtil.BACK_URL+"/wxLogin/callBack.do";
        String url = WxCheckUtil.getCode(backUrl, WxCheckUtil.SCOPE_BASE);
        response.sendRedirect(url);
    }

    /**
     * 微信回调：根据code获取access_token跟openid
     * @param code
     * @return
     */
    @RequestMapping(value = "/callBack.do")
    protected String sendMsgCallBack(String code, Model model) throws IOException {
        String url = WxCheckUtil.codeToAccessToken(code);
        JSONObject resEntity = HttpUtil.doGetJson(url);
        logger.info("==》    请求微信接口获取到的resEntity=" + JSONObject.toJSONString(resEntity));
        String openid = (String) resEntity.get("openid");
        if (null == openid){
            return "index";
        }
        this.request.getSession().setAttribute("openid",openid);
        return "index";
    }
    //获取微信JS_SDK参数
    @ResponseBody
    @RequestMapping("/jsSdkParam.do")
    public Map<String, String> getWechatParam(String url) throws IOException {
        //当前时间
        long now = System.currentTimeMillis();
        //判断accessToken是否已经存在或者token是否过期
        if(StringUtils.isBlank(accessToken)||(now - getTokenTime > tokenExpireTime*1000)){
            JSONObject tokenInfo = getAccessToken();
            if(tokenInfo != null){
                accessToken = tokenInfo.getString("access_token");
                tokenExpireTime = tokenInfo.getLongValue("expires_in");
                //获取token的时间
                getTokenTime = System.currentTimeMillis();
            }else{
                logger.info("==>    tokenInfo is null~");
                logger.info("==>    failure of getting tokenInfo,please do some check~");
            }
        }

        //判断jsApiTicket是否已经存在或者是否过期
        if(StringUtils.isBlank(jsApiTicket)||(now - getTiketTime > ticketExpireTime*1000)){
            JSONObject ticketInfo = getJsApiTicket(accessToken);
            if(ticketInfo!=null){
                jsApiTicket = ticketInfo.getString("ticket");
                ticketExpireTime = ticketInfo.getLongValue("expires_in");
                getTiketTime = System.currentTimeMillis();
            }else{
                logger.info("====>ticketInfo is null~");
                logger.info("====>failure of getting tokenInfo,please do some check~");
            }
        }
        //生成微信权限验证的参数
        Map<String, String> wxChatParam= makeWXTicket(jsApiTicket,url);
        return wxChatParam;
    }
}
