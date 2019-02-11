package com.prize.prize_gzh.service;

import com.prize.prize_gzh.dto.PrizeUserDto;
import com.prize.prize_gzh.entity.PrizeUserEntity;
import org.springframework.data.domain.Page;

/**
 * 用户信息
 */
public interface PrizeUserService {
    /**
     * 根据openID获取用户信息
     * @param openid
     * @return
     */
    PrizeUserEntity getByOpenid(String openid);
    /**
     * 新增
     * @param entity
     * @return
     */
    PrizeUserEntity add(PrizeUserEntity entity);

    /**
     * 更新
     * @param dto
     * @return
     */
    int update(PrizeUserDto dto);
    /**
     * 更新奖品信息
     * @param dto
     * @return
     */
    int updateUserAward(PrizeUserDto dto);
    /**
     * 分页查询
     * @param rows
     * @param page
     * @param dto
     * @return
     */
    Page<PrizeUserEntity> getPage(Integer rows, Integer page, PrizeUserDto dto);
}
