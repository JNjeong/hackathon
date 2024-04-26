package com.hackaton.hackaton.controller;

import com.hackaton.hackaton.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    public HomeController(){}

    User user;

    @RequestMapping("/")
    public String home(HttpServletRequest request){
        user = (User) request.getSession().getAttribute("user");
        if(user==null) return "home";
        else return "user/login";
    }


}
