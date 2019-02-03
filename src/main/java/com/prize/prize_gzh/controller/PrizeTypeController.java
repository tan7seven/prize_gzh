package com.prize.prize_gzh.controller;

import com.alibaba.fastjson.JSONObject;
import com.prize.prize_gzh.entity.PrizeActivityTimeEntity;
import com.prize.prize_gzh.entity.PrizeTypeEntity;
import com.prize.prize_gzh.service.PrizeActivityTimeService;
import com.prize.prize_gzh.service.PrizeTypeService;
import com.prize.prize_gzh.utils.JsonResponse;
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


    @ResponseBody
    @RequestMapping(value = "/getPrize.do")
    protected String getPrize() throws Exception {
        Random rd = new Random();
        double db = rd.nextDouble() * 100;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmss");
        String nowTime = formatter.format(new Date());
        List<PrizeTypeEntity> prizes = prizeTypeService.getPrize();
        List<PrizeActivityTimeEntity> activities=prizeActivityTimeService.getActivityTime(nowTime);
        for (PrizeTypeEntity type:prizes) {
            if(db <= type.getProbability() &&
                    type.getRemainNumber() > 0 &&
                    activities.size() > 0 &&
                    activities.get(0).getRemainNumber()>0){
                type.setRemainNumber(type.getRemainNumber()-1);
                activities.get(0).setRemainNumber( activities.get(0).getRemainNumber()-1);
                int flag1 = prizeTypeService.modify(type);
                int flag2 = prizeActivityTimeService.modify( activities.get(0));
                if(flag1 > 0 && flag2 > 0){
                    return new JsonResponse(0,"", JSONObject.toJSONString(type)).toJSONString();
                }
            }
        }
        return "";
    }
}
