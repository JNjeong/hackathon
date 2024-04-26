package com.hackaton.hackaton.repository;

import com.hackaton.hackaton.domain.Course;

public interface CourseRepository {
    Course findByUserId(long user_id);
}
