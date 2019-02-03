package com.prize.prize_gzh.service;

import com.prize.prize_gzh.entity.PrizeTypeEntity;

import java.util.List;

/**
 * 奖品信息
 */
public interface PrizeTypeService {
    /**
     * 获取奖品信息根据概率排序
     * @return
     */
    List<PrizeTypeEntity> getPrize();

    /**
     * 修改
     * @param entity
     * @return
     */
    int modify(PrizeTypeEntity entity) throws Exception;
}
