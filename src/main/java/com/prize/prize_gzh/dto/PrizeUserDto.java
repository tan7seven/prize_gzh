package com.prize.prize_gzh.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 用户信息
 */
@Data
public class PrizeUserDto {

    public static final String NOT_AWARD="0";
    public static final String IS_AWARD="1";
    /**
     * 编号
     */
    private Integer id;
    /**
     * 微信用户识别码
     */
    private String openid;
    /**
     *用户电话
     */
    private String userPhone;
    /**
     *用户名称
     */
    private String userName;
    /**
     *用户地址
     */
    private String userAddress;
    /**
     *是否得将，0：否、1：是、默认0
     */
    private String isAward = "0";
    /**
     *奖品ID
     */
    private Integer awardId;
    /**
     *奖品名字
     */
    private String awardName;
    /**
     *添加时间
     */
    private Date addDate = new Date();
}
