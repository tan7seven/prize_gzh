package com.prize.prize_gzh.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 答题信息
 */
@Data
@Entity
@Table(name = "prize_question")
public class PrizeQuestionEntity implements Serializable {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 题目
     */
    @Column
    @JSONField(name = "topic_name")
    private String title;
    /**
     * 选项A
     */
    @Column(length = 64,name = "option_a")
    private String optionA;
    /**
     *选项B
     */
    @Column(length = 64,name = "option_b")
    private String optionB;
    /**
     *选择C
     */
    @Column(length = 64,name = "option_c")
    private String optionC;
    /**
     *选项D
     */
    @Column(length = 64,name = "option_d")
    private String optionD;
    /**
     *答案
     */
    @Column(length = 1)
    private String answer;
    /**
     * 类型：0单选、1多选
     */
    @Column(length = 1)
    private String type;
    /**
     * 答案分录
     */
    @Transient
    @JSONField(name = "topic_answer")
    protected List<PrizeAnswerEntry> topicAnswer;
}
