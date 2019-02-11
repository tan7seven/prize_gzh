package com.prize.prize_gzh.controller;

import com.alibaba.fastjson.JSONObject;
import com.prize.prize_gzh.dto.PrizeUserDto;
import com.prize.prize_gzh.entity.PrizeActivityTimeEntity;
import com.prize.prize_gzh.entity.PrizeTypeEntity;
import com.prize.prize_gzh.entity.PrizeUserEntity;
import com.prize.prize_gzh.service.PrizeActivityTimeService;
import com.prize.prize_gzh.service.PrizeTypeService;
import com.prize.prize_gzh.service.PrizeUserService;
import com.prize.prize_gzh.utils.JsonResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/prizeType")
public class PrizeTypeController extends BaseController {

    @Resource(name = "prizeActivityTimeService")
    private PrizeActivityTimeService prizeActivityTimeService;

    @Resource(name = "prizeTypeService")
    private PrizeTypeService prizeTypeService;

    @Resource(name = "prizeUserService")
    private PrizeUserService prizeUserService;


    @ResponseBody
    @RequestMapping(value = "/getPrize.do")
    protected String getPrize() throws Exception {
        String openid = (String) this.request.getSession().getAttribute("openid");

        if(StringUtils.isBlank(openid)){
            openid = "ceShi";
        }
        PrizeUserEntity oldUser = prizeUserService.getByOpenid(openid);
        if(null != oldUser){
            return  new JsonResponse(2,"不能重复参与活动！").toJSONString();
        }
        PrizeUserEntity entity = new PrizeUserEntity();
        entity.setOpenid(openid);
        prizeUserService.add(entity);
        Random rd = new Random();
        double db = rd.nextDouble() * 100;
        System.out.println("随机数="+db);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmss");
        String nowTime = formatter.format(new Date());
        List<PrizeTypeEntity> prizes = prizeTypeService.getPrize();
        List<PrizeActivityTimeEntity> activities=prizeActivityTimeService.getActivityTime(nowTime);
        for (PrizeTypeEntity type:prizes) {
            if(db <= type.getProbability() &&
                    type.getRemainNumber() > 0 &&
                    activities.size() > 0){
                type.setRemainNumber(type.getRemainNumber()-1);
                int flag1 = prizeTypeService.modify(type);
                if(flag1 > 0){
                    PrizeUserDto dto = new PrizeUserDto();
                    dto.setOpenid(openid);
                    dto.setIsAward(PrizeUserDto.IS_AWARD);
                    dto.setAwardId(type.getId());
                    dto.setAwardName(type.getPrizeName());
                    prizeUserService.updateUserAward(dto);
                    return new JsonResponse(0,"恭喜中奖", JSONObject.toJSON(type)).toJSONString();
                }
            }
        }
        return new JsonResponse(1,"很遗憾，没有中奖").toJSONString();
    }
}
