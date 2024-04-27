package com.hackaton.hackaton.controller;

import com.hackaton.hackaton.domain.Course;
import com.hackaton.hackaton.domain.CourseTaken;
import com.hackaton.hackaton.domain.User;
import com.hackaton.hackaton.service.CourseService;
import com.hackaton.hackaton.service.CourseTakenService;
import com.hackaton.hackaton.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    UserService userService;
    CourseService courseSerivce;
    CourseTakenService courseTakenService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    public void setCourseSerivce(CourseService courseSerivce){
        this.courseSerivce = courseSerivce;
    }

    @Autowired
    public void setCourseTakenService(CourseTakenService courseTakenService){
        this.courseTakenService = courseTakenService;
    }

    public HomeController(){}

    @RequestMapping("/")
    public String home(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
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
        else return "redirect:/sign-in";
    }

    @GetMapping("sign-in")
    public void signin(){}

    @PostMapping("sign-in")
    public String signined(String user_id, String user_pw, HttpSession session){
        User user = userService.findById(Long.parseLong(user_id));
        System.out.println(user.getUser_id());
        if (user.getUser_pw().equals(user_pw)) {
            session.setAttribute("user", user);
            return "redirect:/";
        }
        return "redirect:/sign-in";
    }


    @GetMapping("sign-up")
    public void signup(){}

    @PostMapping("sign-up")
    public String signuped(String user_id, String user_pw, String user_name, String user_email, String user_type, HttpSession session){
        User user = new User();
        user.setUser_id(Long.parseLong((user_id)));
        user.setUser_pw(user_pw);
        user.setUser_name(user_name);
        user.setUser_email(user_email);
        user.setUser_type(user_type);
        userService.register(user);
        session.setAttribute("user", user);
        return "index";
    }

    @GetMapping("table")
    public String table(@RequestParam("course_id") long course_id, Model model){
        List<User> users = new ArrayList<User>();
        List<CourseTaken> courseTakens = courseTakenService.findByCourseId(course_id);
        for(CourseTaken courseTaken : courseTakens) {
            users.add(userService.findById(courseTaken.getUser_id()));
        }
        model.addAttribute("students", users);
        return "profile";
    }

    @GetMapping("profile")
    public void profile(){}

}
