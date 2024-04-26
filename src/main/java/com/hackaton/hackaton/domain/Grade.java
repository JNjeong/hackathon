package com.hackaton.hackaton.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Grade {
    private long grade_id;
    private long user_id;
    private long course_id;
    private long grade_value;
    private String grade_type;
    private String grade_description;
    private String grade_feedback;
}