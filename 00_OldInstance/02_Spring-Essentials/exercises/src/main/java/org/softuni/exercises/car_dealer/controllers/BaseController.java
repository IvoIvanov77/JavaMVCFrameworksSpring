package org.softuni.exercises.car_dealer.controllers;

import org.softuni.exercises.car_dealer.models.dtos.responce.LoginUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

public abstract class BaseController {

    @Autowired
    private  HttpSession currentSession;


    public ModelAndView render(String viewName, String pageTitle){
        return new ModelAndView("main_layout")
                .addObject("view", viewName)
                .addObject("pageTitle", pageTitle);
    }

    protected HttpSession getCurrentSession() {
        return currentSession;
    }

    protected LoginUserDto getCurrentUser(){
        return (LoginUserDto) this.currentSession.getAttribute("currentUser");
    }

    protected boolean hasLoggedInUser(){
        return this.currentSession.getAttribute("currentUser") != null;
    }

    protected ModelAndView redirect(String url){
        return new ModelAndView("redirect:" + url);
    }


}
