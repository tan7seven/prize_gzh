package com.prize.prize_gzh.service;

import com.prize.prize_gzh.dto.PrizeUserDto;
import com.prize.prize_gzh.entity.PrizeUserEntity;
import org.springframework.data.domain.Page;

/**
 * 用户信息
 */
public interface PrizeUserService {
    /**
     * 新增
     * @param entity
     * @return
     */
    PrizeUserEntity add(PrizeUserEntity entity);

    /**
     * 分页查询
     * @param rows
     * @param page
     * @param dto
     * @return
     */
    Page<PrizeUserEntity> getPage(Integer rows, Integer page, PrizeUserDto dto);
}
