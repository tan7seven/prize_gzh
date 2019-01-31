package com.prize.prize_gzh.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 活动时间
 */
@Data
public class PrizeActivityTimeDto {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 开始时间
     */
    @Column(name = "start_time")
    private Date startTime;
    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Date endTime;
    /**
     *  奖品总数
     */
    @Column(name = "prize_sum")
    private Integer prizeSum;
    /**
     *  奖品剩余数量
     */
    @Column(name = "remain_number")
    private Integer remainNumber;
}
