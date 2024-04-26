package com.hackaton.hackaton.service;

import com.hackaton.hackaton.domain.CourseTaken;
import com.hackaton.hackaton.repository.CourseTakenRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseTakenService {

    @Autowired
    private CourseTakenRepository courseTakenRepository;

    @Autowired
    public CourseTakenService(SqlSession sqlSession){
        courseTakenRepository = sqlSession.getMapper(CourseTakenRepository.class);
    }

    public List<CourseTaken> findByUserId(long user_id){
        return courseTakenRepository.findByUserId(user_id);
    }
}
