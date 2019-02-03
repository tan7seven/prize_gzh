package com.prize.prize_gzh.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 奖品信息
 */
@Data
@Entity
@Table(name = "prize_type")
public class PrizeTypeEntity implements Serializable{

    private static final long serialVersionUID = 7045589773258130512L;
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
