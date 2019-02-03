package com.prize.prize_gzh.service.impl;

import com.prize.prize_gzh.dto.PrizeQuestionDto;
import com.prize.prize_gzh.entity.PrizeAnswerEntry;
import com.prize.prize_gzh.entity.PrizeQuestionEntity;
import com.prize.prize_gzh.mapper.PrizeQuestionMapper;
import com.prize.prize_gzh.repository.PrizeQuestionRepository;
import com.prize.prize_gzh.service.PrizeQuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service(value = "prizeQuestionService")
public class PrizeQuestionServiceImpl implements PrizeQuestionService {

    @Autowired
    private PrizeQuestionRepository prizeQuestionRepository;

    @Autowired
    private PrizeQuestionMapper prizeQuestionMapper;

    @Override
    public Page<PrizeQuestionEntity> getPage(PrizeQuestionDto dto, Integer rows, Integer page) {
        Pageable pageable = new PageRequest(page-1, rows, Sort.Direction.DESC, "startTime");
        Page<PrizeQuestionEntity> pageInfo = prizeQuestionRepository.findAll((Specification<PrizeQuestionEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
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

    @Override
    public List<PrizeQuestionEntity> getRand(Integer num) {
        List<PrizeQuestionEntity> list = prizeQuestionMapper.getRand(num);
        for (PrizeQuestionEntity entity : list) {
            List<PrizeAnswerEntry> answerEntries = new ArrayList<>();
            if(StringUtils.isNotBlank(entity.getOptionA())){
                PrizeAnswerEntry answerName = new PrizeAnswerEntry();
                answerName.setOption("A");
                answerName.setAnswerName(entity.getOptionA());
                answerEntries.add(answerName);
            }
            if(StringUtils.isNotBlank(entity.getOptionB())){
                PrizeAnswerEntry answerName = new PrizeAnswerEntry();
                answerName.setOption("B");
                answerName.setAnswerName(entity.getOptionB());
                answerEntries.add(answerName);
            }
            if(StringUtils.isNotBlank(entity.getOptionC())){
                PrizeAnswerEntry answerName = new PrizeAnswerEntry();
                answerName.setOption("C");
                answerName.setAnswerName(entity.getOptionC());
                answerEntries.add(answerName);
            }
            if(StringUtils.isNotBlank(entity.getOptionD())){
                PrizeAnswerEntry answerName = new PrizeAnswerEntry();
                answerName.setOption("D");
                answerName.setAnswerName(entity.getOptionD());
                answerEntries.add(answerName);
            }
            entity.setTopicAnswer(answerEntries);
        }
        return list;
    }
}
