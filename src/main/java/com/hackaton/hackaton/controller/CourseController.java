package com.hackaton.hackaton.controller;

import com.hackaton.hackaton.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService;


}
