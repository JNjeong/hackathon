package com.hackaton.hackaton.repository;

import com.hackaton.hackaton.domain.Criteria;

public interface CriteriaRepository {
    Criteria findByCourseId(long course_id);

    int save(long course_id);
}
