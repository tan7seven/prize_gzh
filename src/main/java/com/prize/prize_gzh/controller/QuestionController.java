package com.prize.prize_gzh.controller;

import com.alibaba.fastjson.JSONObject;
import com.prize.prize_gzh.service.PrizeQuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/question")
public class QuestionController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Resource(name = "prizeQuestionService")
    private PrizeQuestionService prizeQuestionService;

    /**
     * 随机获取题目
     * @param num
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/getQuestion.do")
    protected String getQuestion(Integer num) throws Exception {
        if(null == num){
            logger.error("==》   随机获取题目信息异常：num为空");
            throw new Exception("==》   随机获取题目信息异常：num为空");
        }
        return JSONObject.toJSONString(prizeQuestionService.getRand(num));
    }
}
