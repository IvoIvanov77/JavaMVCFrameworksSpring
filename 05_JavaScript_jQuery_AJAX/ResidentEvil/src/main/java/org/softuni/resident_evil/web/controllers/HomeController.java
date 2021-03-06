package org.softuni.resident_evil.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    @GetMapping("/")
    public ModelAndView homeView(){
        return this.view("home");
    }

    @GetMapping("/fetch-data")
    public ModelAndView fetchData(){
        return this.view("fetch-table");
    }
}
