package org.softuini.exodia.web.controllers;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class BaseController {


    protected boolean hasLoggedInUser(HttpSession currentSession){
        return currentSession.getAttribute("currentUser") != null;
    }


    public ModelAndView render(String viewName){
        return new ModelAndView("base-layout")
                .addObject("view", viewName);
    }


    protected ModelAndView redirect(String url){
        return new ModelAndView("redirect:" + url);
    }


}
