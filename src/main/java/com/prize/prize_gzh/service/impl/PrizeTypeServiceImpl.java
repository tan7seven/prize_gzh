package com.prize.prize_gzh.service.impl;

import com.prize.prize_gzh.entity.PrizeTypeEntity;
import com.prize.prize_gzh.mapper.PrizeTypeMapper;
import com.prize.prize_gzh.service.PrizeTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "prizeTypeService")
public class PrizeTypeServiceImpl implements PrizeTypeService {

    private static final Logger logger = LoggerFactory.getLogger(PrizeTypeServiceImpl.class);

    @Autowired
    private PrizeTypeMapper prizeTypeMapper;

    @Override
    public List<PrizeTypeEntity> getPrize() {
        return prizeTypeMapper.getPrize();
    }

    @Override
    public int modify(PrizeTypeEntity entity) throws Exception {
        if(null == entity.getId()){
            logger.error("==》    修改奖品信息异常：id为空！");
            throw new Exception("==》     修改奖品信息异常：id为空！");
        }
        if(null == entity.getRemainNumber()){
            logger.error("==》     修改奖品信息异常：剩余数量为空！");
            throw new Exception("==》     修改奖品信息异常：剩余数量为空！");
        }
        return prizeTypeMapper.modify(entity);
    }
}
