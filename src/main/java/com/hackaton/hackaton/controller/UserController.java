package com.hackaton.hackaton.controller;

import com.hackaton.hackaton.domain.User;
import com.hackaton.hackaton.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class UserController{
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    public UserController(){}

    @GetMapping("/sign-in")
    public void login(){
    }

    @PostMapping("/sign-in")
    public String login(String user_id, String user_pw){
        User foundUser = userService.findById(Long.parseLong(user_id));
        System.out.println("id : "+foundUser);
        return "redirect:/index";
    }

    @GetMapping("/sign-up")
    public void register(){}

    @PostMapping("/sign-up")
    public String registered(String user_id, String user_pw, String user_name, String user_email, String user_type, HttpServletRequest request){
        User user = new User();
        user.setUser_id(Long.parseLong(user_id));
        user.setUser_pw(user_pw);
        user.setUser_name(user_name);
        user.setUser_email(user_email);
        user.setUser_type(user_type);

        //세션 User객체 저장
        request.getSession().setAttribute("user", user);

        return "index";
    }
}