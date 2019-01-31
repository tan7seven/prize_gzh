package com.prize.prize_gzh.repository;

import com.prize.prize_gzh.entity.PrizeUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PrizeUserRepository extends JpaRepository<PrizeUserEntity, Integer>, JpaSpecificationExecutor<PrizeUserEntity> {
}
