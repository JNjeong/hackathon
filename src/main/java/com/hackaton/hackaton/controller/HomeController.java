package com.hackaton.hackaton.controller;

import com.hackaton.hackaton.domain.User;
import com.hackaton.hackaton.service.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    CourseService courseSerivce;

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
                //model.addAttribute("courseTaken", //todo);
            }
            return "home";
        }
        else return "user/login";
    }


}
