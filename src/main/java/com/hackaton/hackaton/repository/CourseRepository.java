package com.hackaton.hackaton.repository;

import com.hackaton.hackaton.domain.Course;

import java.util.List;

public interface CourseRepository {
    Course findByCourseId(long course_id);

    List<Course> findByUserId(long user_id);
}
