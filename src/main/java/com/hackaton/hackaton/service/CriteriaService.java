package com.hackaton.hackaton.service;

import com.hackaton.hackaton.repository.CriteriaRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriteriaService {
    @Autowired
    CriteriaRepository criteriaRepository;

    @Autowired
    public CriteriaService(SqlSession sqlSession){
        criteriaRepository = sqlSession.getMapper(CriteriaRepository.class);
    }




}
