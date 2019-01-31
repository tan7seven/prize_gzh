package com.prize.prize_gzh.service.impl;

import com.prize.prize_gzh.dto.PrizeUserDto;
import com.prize.prize_gzh.entity.PrizeUserEntity;
import com.prize.prize_gzh.repository.PrizeUserRepository;
import com.prize.prize_gzh.service.PrizeUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service(value = "prizeUserService")
public class PrizeUserServiceImpl implements PrizeUserService {

    @Autowired
    private PrizeUserRepository prizeUserRepository;

    @Override
    public PrizeUserEntity add(PrizeUserEntity entity) {
        return prizeUserRepository.save(entity);
    }

    @Override
    public Page<PrizeUserEntity> getPage(Integer rows, Integer page, PrizeUserDto dto) {
        Pageable pageable = new PageRequest(page-1, rows, Sort.Direction.DESC, "startTime");
        Page<PrizeUserEntity> pageInfo = prizeUserRepository.findAll((Specification<PrizeUserEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if(StringUtils.isNotBlank(dto.getUserPhone())){
                list.add(criteriaBuilder.like(root.get("userPhone").as(String.class), "%"+dto.getUserPhone()+"%"));
            }
            /*if(StringUtils.isNotBlank(dto.getStartAddress())){
                list.add(criteriaBuilder.like(root.get("startAddress").as(String.class), "%"+dto.getStartAddress()+"%"));
            }
            if(StringUtils.isNotBlank(dto.getStartName())){
                list.add(criteriaBuilder.like(root.get("startName").as(String.class), "%"+dto.getStartName()+"%"));
            }
            if(StringUtils.isNotBlank(dto.getEndName())){
                list.add(criteriaBuilder.like(root.get("endAddress").as(String.class), "%"+dto.getEndName()+"%"));
            }
            if(StringUtils.isNotBlank(dto.getEndName())){
                list.add(criteriaBuilder.like(root.get("endName").as(String.class), "%"+dto.getEndName()+"%"));
            }*/
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        },pageable);
        return pageInfo;
    }
}
