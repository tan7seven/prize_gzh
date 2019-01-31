package com.prize.prize_gzh.dto;

import lombok.Data;

/**
 * 微信开发模式验证检验
 */
@Data
public class WxCheckDto {
    /**
     * 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     */
    private String signature;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 随机数
     */
    private String nonce;
    /**
     * 随机字符串
     */
    private  String echostr;


}
