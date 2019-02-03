package com.prize.prize_gzh.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 答案封装类
 */
@Data
public class PrizeAnswerEntry implements Serializable {
    /**
     * 选项
     */
    private String option;
    /**
     * 选项内容
     */
    @JSONField(name = "answer_name")
    private String answerName;
}
