package org.softuni.exercises.controllers;

import org.softuni.exercises.domain.models.binding.FindOfferBindingModel;
import org.softuni.exercises.domain.models.binding.RegisterOfferBindingModel;
import org.softuni.exercises.services.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/register")
    public String registerView(){
        return "/register.html";
    }

    @GetMapping("/find")
    public String findView(){
        return "/find.html";
    }

    @PostMapping("/register")
    public String registerAction(@ModelAttribute RegisterOfferBindingModel registerBindingModel){
        if(this.offerService.registerOffer(registerBindingModel)){
            return "redirect:/";
        }
        return "redirect:/register";
    }

    @PostMapping("/find")
    public String findAction(@ModelAttribute FindOfferBindingModel findOfferModel){
        if(this.offerService.findOffer(findOfferModel)){
            return "redirect:/";
        }
        return "redirect:/find";
    }
}
