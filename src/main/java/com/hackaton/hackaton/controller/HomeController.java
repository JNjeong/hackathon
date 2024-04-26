package com.hackaton.hackaton.controller;

import com.hackaton.hackaton.domain.Course;
import com.hackaton.hackaton.domain.CourseTaken;
import com.hackaton.hackaton.domain.User;
import com.hackaton.hackaton.service.CourseService;
import com.hackaton.hackaton.service.CourseTakenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    CourseService courseSerivce;
    CourseTakenService courseTakenService;

    public HomeController(){}

    User user;

    @RequestMapping("/")
    public String home(HttpServletRequest request, Model model){
        user = (User) request.getSession().getAttribute("user");

        if(user!=null) {
            if(user.getUser_type().equals("Professor")){
                model.addAttribute("course", courseSerivce.findByUserId(user));
            }
            else if (user.getUser_type().equals("Student")){
                List<Course> courses = new ArrayList<Course>();
                List<CourseTaken> courseTakens = courseTakenService.findByUserId(user.getUser_id());
                for (CourseTaken courseTaken : courseTakens) {
                    courses.add(courseSerivce.findByCourseId(courseTaken.getCourse_id()));
                }
                model.addAttribute("courseTaken", courses);
            }
            return "index";
        }
        else return "sign-in";
    }


}
