package com.hackaton.hackaton.service;


import com.hackaton.hackaton.domain.Grade;
import com.hackaton.hackaton.domain.User;
import com.hackaton.hackaton.repository.GradeRepository;
import com.hackaton.hackaton.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(SqlSession sqlSession){
        gradeRepository = sqlSession.getMapper(GradeRepository.class);
    }

    public List<Grade> findAllByUserId(long user_id, long course_id){
        return gradeRepository.findAllByUserId(user_id,course_id);
    }


}