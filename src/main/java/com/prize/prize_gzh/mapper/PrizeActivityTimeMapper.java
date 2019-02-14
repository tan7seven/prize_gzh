package com.prize.prize_gzh.mapper;

import com.prize.prize_gzh.entity.PrizeActivityTimeEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PrizeActivityTimeMapper {
    /**
     *  获取当前时间的活动
     * @param nowTime
     * @return
     */
    @Select("SELECT id, start_time, end_time from prize_activity_time where start_time < #{nowTime} AND END_time > #{nowTime} ORDER BY start_time ASC")
    List<PrizeActivityTimeEntity> find(@Param("nowTime") String nowTime);

    /**
     * 根据活动分组获取活动时间
     * @param activityGroup
     * @return
     */
    @Select("SELECT id, start_time, end_time from prize_activity_time where activity_group = #{activityGroup}")
    PrizeActivityTimeEntity findByGroup(@Param("activityGroup") Integer activityGroup);
}
