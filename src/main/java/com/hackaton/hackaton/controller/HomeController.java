package com.hackaton.hackaton.controller;

import com.hackaton.hackaton.domain.Course;
import com.hackaton.hackaton.domain.CourseTaken;
import com.hackaton.hackaton.domain.User;
import com.hackaton.hackaton.service.CourseService;
import com.hackaton.hackaton.service.CourseTakenService;
import com.hackaton.hackaton.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    UserService userService;
    CourseService courseSerivce;
    CourseTakenService courseTakenService;

    public HomeController(){}

    User user;

    @RequestMapping("/")
    public String home(HttpServletRequest request, Model model){
        user = (User) request.getSession().getAttribute("user");

        /*
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

         */
        return "index";
    }

    @GetMapping("/sign-in")
    public void signin(){}

    @PostMapping("/sign-in")
    public String signined(String user_id, String user_pw, HttpServletRequest request){
        User user = userService.findById(Long.parseLong(user_id));
        if (user.getUser_pw().equals(user_pw)) {
            request.getSession().setAttribute("user", user);
            return "index";
        }
        return "redirect:/sign-in";
    }


    @GetMapping("/sign-up")
    public void signup(){}

    @PostMapping("/sign-up")
    public String signuped(String user_id, String user_pw, String user_name, String user_email, String user_type, HttpServletRequest request){
        User user = new User();
        user.setUser_id(Long.parseLong((user_id)));
        user.setUser_pw(user_pw);
        user.setUser_name(user_name);
        user.setUser_email(user_email);
        user.setUser_type(user_type);
        userService.register(user);
        request.getSession().setAttribute("user", user);
        return "index";
    }


}
