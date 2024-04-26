package com.hackaton.hackaton.service;

import com.hackaton.hackaton.domain.Course;
import com.hackaton.hackaton.domain.User;
import com.hackaton.hackaton.repository.CourseRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {


    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    public CourseService(SqlSession sqlSession){
        courseRepository = sqlSession.getMapper(CourseRepository.class);
    }

    public List<Course> findByUserId(User user){
        if(user.getUser_type().equals("Professor")) {
           return courseRepository.findByUserId(user.getUser_id());
        }

        return null;
    }

    public Course findByCourseId(long course_id){
        return courseRepository.findByCourseId(course_id);
    }


}
