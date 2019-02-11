package com.prize.prize_gzh.service;

import com.prize.prize_gzh.entity.PrizeActivityTimeEntity;

import java.util.List;

/**
 * 活动时间信息
 */
public interface PrizeActivityTimeService {

    /**
     * 根据当前时间获取活动信息
     * @param noeTime
     * @return
     */
    List<PrizeActivityTimeEntity> getActivityTime(String noeTime);

}
