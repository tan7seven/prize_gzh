package com.prize.prize_gzh.repository;

import com.prize.prize_gzh.entity.PrizeQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PrizeQuestionRepository extends JpaRepository<PrizeQuestionEntity, Integer>, JpaSpecificationExecutor<PrizeQuestionEntity>{
}
