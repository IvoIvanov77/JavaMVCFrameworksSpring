package org.softuni.exercises.car_dealer.controllers;


import org.softuni.exercises.car_dealer.models.dtos.responce.LoginUserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController{

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView){
        LoginUserDto currentUser = this.getCurrentUser();
        if(currentUser == null){
            modelAndView.addObject("user",null);
        }else{
            modelAndView.addObject("user", currentUser.getUsername());
        }
        modelAndView.addObject("view", "index");
//        modelAndView.addObject("pageTitle", null);
        modelAndView.setViewName("main_layout");
        return modelAndView;
    }
}
