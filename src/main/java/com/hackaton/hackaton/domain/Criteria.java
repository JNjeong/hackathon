package com.hackaton.hackaton.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Criteria {
    private long course_id;
    private String criteria_field;
    private long criteria_percentage;
}