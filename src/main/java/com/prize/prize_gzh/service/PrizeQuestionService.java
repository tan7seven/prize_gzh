package com.prize.prize_gzh.service;

import com.prize.prize_gzh.dto.PrizeQuestionDto;
import com.prize.prize_gzh.entity.PrizeQuestionEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 答题信息
 */
public interface PrizeQuestionService {

    /**
     * 分页查询
     * @param dto
     * @param rows
     * @param page
     * @return
     */
    Page<PrizeQuestionEntity> getPage(PrizeQuestionDto dto, Integer rows, Integer page);

    /**
     * 随机获取三十道题目
     * @param num
     * @return
     */
    List<PrizeQuestionEntity> getRand(Integer num);
}
