package com.prize.prize_gzh.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 奖品信息
 */
@Data
public class PrizeTypeDto {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 奖品名称
     */
    private String prizeName;
    /**
     * 奖品总数
     */
    private Integer prizeSum;
    /**
     * 剩余数量
     */
    private Integer remainNumber;
    /**
     * 得奖概率
     */
    private Integer probability;

}
