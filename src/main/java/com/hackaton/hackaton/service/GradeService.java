package com.hackaton.hackaton.service;


import com.hackaton.hackaton.domain.User;
import com.hackaton.hackaton.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeService {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public GradeService(SqlSession sqlSession){
//        userRepository = sqlSession.getMapper(UserRepository.class);
//    }
//
//    public User findById(long user_id){
//        return userRepository.findById(user_id);
//    }
//
//    public int register(User user){
//        //유효성검사??
//        return userRepository.save(user);
//    }

}