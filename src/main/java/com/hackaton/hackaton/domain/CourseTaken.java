package com.hackaton.hackaton.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseTaken {
    private long user_id;
    private long course_id;
    private long semester_id;
}