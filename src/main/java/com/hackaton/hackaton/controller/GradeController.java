package com.hackaton.hackaton.controller;

import com.hackaton.hackaton.domain.User;
import com.hackaton.hackaton.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/grade/*")
public class GradeController {
//    private UserService userService;
//
//    @Autowired
//    public void setUserService(UserService userService){
//        this.userService = userService;
//    }
//
//    public GradeController(){}
//
//    @GetMapping("login")
//    public void login(){}
//
//    @GetMapping("register")
//    public void register(){}
//
//    @PostMapping("register")
//    public String registered(User user, RedirectAttributes redirectAttributes){
//        //세션 User객체 저장
//
//        return "redirect:/main";
//    }
}