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
    @Pattern(regexp="^1(3|4|5|7|8|9)\\d{9}$",message="手机号码格式错误！")
    private String userPhone;
    /**
     *用户名称
     */
    @NotBlank(message = "联系姓名不能为空！")
    private String userName;
    /**
     *用户地址
     */
    @NotBlank(message = "联系姓名不能为空！")
    private String userAddress;
    /**
     *是否得将，0：否、1：是、默认0
     */
    private String isAward;
    /**
     *奖品ID
     */
    private Integer awardId;
    /**
     *奖品名字
     */
    @NotBlank(message = "联系姓名不能为空！")
    private String awardName;
    /**
     *添加时间
     */
    private Date addDate;
}
