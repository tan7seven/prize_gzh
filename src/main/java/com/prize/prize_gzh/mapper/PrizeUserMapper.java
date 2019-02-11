package com.prize.prize_gzh.mapper;

import com.prize.prize_gzh.dto.PrizeUserDto;
import com.prize.prize_gzh.entity.PrizeUserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 用户信息表
 */
public interface PrizeUserMapper {

    @Select("SELECT ID, openid, user_phone, user_name, user_address, is_award, award_id, award_name " +
            "FROM  prize_user WHERE openid = #{openid}")
    PrizeUserEntity getByOpenid(@Param("openid") String openid);

    @Update("UPDATE prize_user SET " +
            "user_phone = #{dto.userPhone}, " +
            "user_name = #{dto.userName}, " +
            "user_address = #{dto.userAddress} " +
            " where openid = #{dto.openid}")
    int updateUser(@Param("dto") PrizeUserDto dto);
    @Update("UPDATE prize_user SET " +
            "is_award = #{dto.isAward}, " +
            "award_id = #{dto.awardId}, " +
            "award_name = #{dto.awardName} " +
            " where openid = #{dto.openid}")
    int updateUserAward(@Param("dto") PrizeUserDto dto);
}
