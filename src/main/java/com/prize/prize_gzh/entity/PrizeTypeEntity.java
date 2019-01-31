package com.prize.prize_gzh.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 奖品信息
 */
@Data
@Entity
@Table(name = "prize_type")
public class PrizeTypeEntity {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 奖品名称
     */
    @Column(name = "prize_name")
    private String prizeName;
    /**
     * 奖品总数
     */
    @Column(name = "prize_sum")
    private Integer prizeSum;
    /**
     * 剩余数量
     */
    @Column(name = "remain_number")
    private Integer remainNumber;
    /**
     * 得奖概率
     */
    @Column
    private Integer probability;

}
