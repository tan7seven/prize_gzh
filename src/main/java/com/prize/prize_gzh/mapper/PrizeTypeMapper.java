package com.prize.prize_gzh.mapper;

import com.prize.prize_gzh.entity.PrizeTypeEntity;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PrizeTypeMapper {
    /**
     * 获取奖品信息（概率从小到大）
     * @return
     */
    @Select("SELECT prize_name, prize_sum, remain_number, probability FROM prize_type ORDER BY probability ASC")
    List<PrizeTypeEntity> getPrize();

    @Update("UPDATE prize_type SET remain_number = #{entity.remainNumber} where id = #{entity.id}")
    int modify(PrizeTypeEntity entity);
}
