package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String teacherLogin(){
        return "login";
    }

//    @RequestMapping(value="/api/login", method = RequestMethod.POST)
//    public ModelAndView studentLogin(){
//
//    }
}
