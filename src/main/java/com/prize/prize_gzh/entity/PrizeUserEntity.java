package com.prize.prize_gzh.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 */
@Data
@Entity
@Table(name = "prize_user")
public class PrizeUserEntity implements Serializable {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 微信用户识别码
     */
    @Column
    private String openid;
    /**
     *用户电话
     */
    @Column(length = 11, name = "user_phone")
    private String userPhone;
    /**
     *用户名称
     */
    @Column(length = 64, name = "user_name")
    private String userName;
    /**
     *用户地址
     */
    @Column(length = 125, name = "user_address")
    private String userAddress;
    /**
     *是否得将，0：否、1：是、默认0
     */
    @Column(length = 1, name = "is_award")
    private String isAward = "0";
    /**
     *奖品ID
     */
    @Column(name = "award_id")
    private Integer awardId;
    /**
     *奖品名字
     */
    @Column(length = 125, name = "award_name")
    private String awardName;
    /**
     *添加时间
     */
    @Column(name = "add_date")
    private Date addDate = new Date();
}
