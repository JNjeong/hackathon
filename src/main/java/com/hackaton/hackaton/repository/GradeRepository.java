package com.hackaton.hackaton.repository;


import com.hackaton.hackaton.domain.Grade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository {
    List<Grade> findAllByUserId(long user_id, long course_id);

}