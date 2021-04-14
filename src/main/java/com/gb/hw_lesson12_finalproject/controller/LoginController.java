package com.gb.hw_lesson12_finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.SessionScope;

@Controller
@SessionScope
public class LoginController {
    @GetMapping("/login")
    public String showMyLoginPage(){
        return "login";
    }

    @GetMapping("/logout")
    public String showLogoutPage(){
        return "login";
    }

//    @GetMapping("/")
//    public String homePage(){
//        return "index";
//    }

}
