package org.softuini.exodia.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuini.exodia.domain.models.binding.UserLoginBindingModel;
import org.softuini.exodia.domain.models.binding.UserRegisterBindingModel;
import org.softuini.exodia.domain.models.service.UserServiceModel;
import org.softuini.exodia.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController extends BaseController {
    private final UserService userService;

    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public ModelAndView login(HttpSession session){
        if(hasLoggedInUser(session)){
            return redirect("/");
        }
        return this.render("login");
    }

    @GetMapping("/register")
    public ModelAndView register(HttpSession session){
        if(hasLoggedInUser(session)){
            return redirect("/");
        }
        return this.render("register");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session){
        session.invalidate();
        return this.redirect("/");
    }

    @PostMapping("/register")
    public ModelAndView registerAction(@ModelAttribute UserRegisterBindingModel registerModel){
        UserServiceModel userServiceModel = this.modelMapper.map(registerModel, UserServiceModel.class);
        if(this.userService.registerUser(userServiceModel)){
            return this.redirect("/login");
        }
        return this.redirect("/register");
    }

    @PostMapping("/login")
    public ModelAndView loginAction(@ModelAttribute UserLoginBindingModel loginModel, HttpSession session){
        if(this.userService.loginUser(loginModel)){
            session.setAttribute("currentUser", loginModel);
            return this.redirect("/");
        }
        return this.redirect("/login");
    }
}
