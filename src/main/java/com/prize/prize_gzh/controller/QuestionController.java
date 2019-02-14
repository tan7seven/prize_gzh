package com.prize.prize_gzh.controller;

import com.alibaba.fastjson.JSONObject;
import com.prize.prize_gzh.entity.PrizeActivityTimeEntity;
import com.prize.prize_gzh.entity.PrizeUserEntity;
import com.prize.prize_gzh.service.PrizeActivityTimeService;
import com.prize.prize_gzh.service.PrizeQuestionService;
import com.prize.prize_gzh.service.PrizeUserService;
import com.prize.prize_gzh.utils.JsonResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping(value = "/question")
public class QuestionController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Resource(name = "prizeQuestionService")
    private PrizeQuestionService prizeQuestionService;

    @Resource(name = "prizeUserService")
    private PrizeUserService prizeUserService;

    @Resource(name = "prizeActivityTimeService")
    private PrizeActivityTimeService prizeActivityTimeService;

    /**
     * 随机获取题目
     * @param num
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/getQuestion.do")
    protected String getQuestion(Integer num) throws Exception {
        String openid = (String) this.request.getSession().getAttribute("openid");
        if(StringUtils.isBlank(openid)){
            openid = "ceShi";
//            return  new JsonResponse(1,"请重新登录页面！").toJSONString();
        }
        PrizeUserEntity oldUser = prizeUserService.getByOpenid(openid);
        if(null != oldUser){
            return  new JsonResponse(1,"不能重复抽奖！").toJSONString();
        }
        if(null == num){
            logger.error("==》   随机获取题目信息异常：num为空");
            throw new Exception("==》   随机获取题目信息异常：num为空");
        }
        PrizeActivityTimeEntity entity = prizeActivityTimeService.findByGroup(1);
        if(entity.getStartTime().getTime() > new Date().getTime()){
            return new JsonResponse(2, "活动尚未开始！",JSONObject.toJSON(prizeQuestionService.getRand(num))).toJSONString();
        }
        if(entity.getEndTime().getTime() < new Date().getTime()){
            return new JsonResponse(3, "活动尚已结束！",JSONObject.toJSON(prizeQuestionService.getRand(num))).toJSONString();
        }
        return new JsonResponse(0, "获取成功",JSONObject.toJSON(prizeQuestionService.getRand(num))).toJSONString();
    }
}
