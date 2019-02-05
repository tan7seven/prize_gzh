package com.prize.prize_gzh.controller;

import com.alibaba.fastjson.JSONObject;
import com.prize.prize_gzh.dto.WxCheckDto;
import com.prize.prize_gzh.httpUtils.HttpUtil;
import com.prize.prize_gzh.utils.JsonResponse;
import com.prize.prize_gzh.utils.WxCheckUtil;
import org.hibernate.validator.constraints.pl.REGON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 微信登录
 */
@Controller
@RequestMapping(value = "/wxLogin")
public class WxLoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(WxLoginController.class);


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
     * 微信登录获取code参数【发布信息路口】
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
     * 微信回调：根据code获取access_token跟openid【发布信息路口】
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
            return "openidMiss";
        }
        this.request.getSession().setAttribute("openid",openid);
        return "index";
    }
}
