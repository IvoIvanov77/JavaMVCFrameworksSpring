package com.example.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mypage")
public class HomeController {


    @GetMapping("")
    public String index(){
        return "index";
    }

    @ResponseBody
    @GetMapping("/dog")
    public String getDog(){
        return "I am dog";
    }
}
