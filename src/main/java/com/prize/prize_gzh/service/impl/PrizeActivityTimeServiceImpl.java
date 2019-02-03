package com.prize.prize_gzh.service.impl;

import com.prize.prize_gzh.entity.PrizeActivityTimeEntity;
import com.prize.prize_gzh.mapper.PrizeActivityTimeMapper;
import com.prize.prize_gzh.service.PrizeActivityTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "prizeActivityTimeService")
public class PrizeActivityTimeServiceImpl implements PrizeActivityTimeService {

    private static final Logger logger = LoggerFactory.getLogger(PrizeActivityTimeServiceImpl.class);

    @Autowired
    private PrizeActivityTimeMapper prizeActivityTimeMapper;

    @Override
    public List<PrizeActivityTimeEntity> getActivityTime(String noeTime) {
        return prizeActivityTimeMapper.find(noeTime);
    }

    @Override
    public int modify(PrizeActivityTimeEntity entity) throws Exception {
        if(null == entity.getId()){
            logger.error("==》    修改活动时间异常：id为空！");
            throw new Exception("==》    修改活动时间异常：id为空！");
        }
        if(null == entity.getRemainNumber()){
            logger.error("==》    修改活动时间异常：剩余数量为空！");
            throw new Exception("==》    修改活动时间异常：剩余数量为空！");
        }
        return prizeActivityTimeMapper.modify(entity);
    }
}
