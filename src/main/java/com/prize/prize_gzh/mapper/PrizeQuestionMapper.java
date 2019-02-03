package com.prize.prize_gzh.mapper;

import com.prize.prize_gzh.entity.PrizeQuestionEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PrizeQuestionMapper {
    /**
     * 随机获取30道题目
     * @param num
     * @return
     */
    @Select("select id, title, option_a optionA, option_b optionB, option_c optionC, option_d optionD, answer from prize_question order by rand() limit ${num};")
    List<PrizeQuestionEntity> getRand(@Param("num") Integer num);
}
