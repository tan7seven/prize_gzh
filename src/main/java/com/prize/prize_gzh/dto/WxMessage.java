package com.prize.prize_gzh.dto;

import lombok.Data;

/**
 * 微信信息回复XML
 */
@Data
public class WxMessage {
    /**
     * 接收方账号（收到的OpenId）
     */
    private String ToUserName;
    /**
     * 开发者微信号
     */
    private String FromUserName;
    /**
     * 消息创建时间（整型）
     */
    private String CreateTime;
    /***
     * text
     */
    private String MsgType;
    /**
     * 回复的消息内容
     * 换行：在contenit中能够换行，微信客户端就支持换行显示。
     */
    private String Content;

}
