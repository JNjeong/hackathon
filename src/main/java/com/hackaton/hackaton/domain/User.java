package com.hackaton.hackaton.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User{
    private long user_id;
    private String user_pw;
    private String user_name;
    private String user_email;
    private String user_type;
}