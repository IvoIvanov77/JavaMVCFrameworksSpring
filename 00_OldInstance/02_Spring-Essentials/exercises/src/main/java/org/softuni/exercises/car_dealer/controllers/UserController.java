package org.softuni.exercises.car_dealer.controllers;


import org.softuni.exercises.car_dealer.models.dtos.request.UserLoginBindingModel;
import org.softuni.exercises.car_dealer.models.dtos.request.UserRegisterBindingModel;
import org.softuni.exercises.car_dealer.models.dtos.responce.LoginUserDto;
import org.softuni.exercises.car_dealer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView loginView(){
        if(this.hasLoggedInUser()){
            return this.redirect("/");
        }
        return this.render("forms/user/login", "Login Page")
                .addObject("loginModel", new UserLoginBindingModel());
    }

    @GetMapping("/logout")
    public ModelAndView logoutAction(){
        this.getCurrentSession().setAttribute("currentUser", null);
        return this.redirect("/");
    }

    @GetMapping("/register")
    public ModelAndView registerView(){
        if(this.hasLoggedInUser()){
            return this.redirect("/");
        }
        return this.render("forms/user/register", "Register Page")
                .addObject("registerModel", new UserRegisterBindingModel());
    }

    @PostMapping("/login")
    public ModelAndView loginAction(UserLoginBindingModel model){
        LoginUserDto loginUser = this.userService.login(model);

        if(loginUser != null){
            this.getCurrentSession().setAttribute("currentUser", loginUser);
            return this.redirect("/");
        }

        return this.redirect("/user/login");
    }

    @PostMapping("/register")
    public ModelAndView registerAction(UserRegisterBindingModel model){
        boolean result = this.userService.register(model);

        if(result){
            return this.redirect("/user/login");
        }
        return this.redirect("/user/register");
    }


}
