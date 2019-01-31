package com.prize.prize_gzh.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 答题信息
 */
@Data
public class PrizeQuestionDto {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 题目
     */
    private String title;
    /**
     * 选项A
     */
    private String optionA;
    /**
     *选项B
     */
    private String optionB;
    /**
     *选择C
     */
    private String optionC;
    /**
     *选项D
     */
    private String optionD;
    /**
     *答案
     */
    private String answer;
}
