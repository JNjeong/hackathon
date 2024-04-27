package com.hackaton.hackaton.repository;

import com.hackaton.hackaton.domain.CourseTaken;

import java.util.List;

public interface CourseTakenRepository {
    List<CourseTaken> findByUserId(long user_id);

    List<CourseTaken> findByCourseId(long course_id);
}
