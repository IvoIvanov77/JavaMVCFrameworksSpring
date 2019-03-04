package org.softuni.exercises.car_dealer.controllers;


import org.softuni.exercises.car_dealer.models.dtos.request.CustomerBindingModel;
import org.softuni.exercises.car_dealer.models.entities.Customer;
import org.softuni.exercises.car_dealer.models.dtos.responce.CustomerInfoDto;
import org.softuni.exercises.car_dealer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController extends BaseController{

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all/ascending")
    public ModelAndView getAllAscending(){
        return render("customers/all", "All ascending")
                .addObject("customers",
                        this.customerService.getAllOrderedByBirthDate());
    }

    @GetMapping("/all/descending")
    public ModelAndView getAllDescending(){
        List<Customer> result =  this.customerService.getAllOrderedByBirthDate() ;
        Collections.reverse(result);

        return render("customers/all", "All descending")
                .addObject("customers", result);
    }

    @GetMapping("/{id}")
    public ModelAndView getAllCustomerInfo(@PathVariable Long id){
        CustomerInfoDto customer = this.customerService.getCustomerSalesInfo(id);

        return render("customers/details", customer.getName())
                .addObject("customer",customer);
    }

    @GetMapping("/create")
    public ModelAndView createCustomer(){
        return render("forms/add_customer","Add Customer")
                .addObject("model", new CustomerBindingModel());
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCustomer(@PathVariable Long id){
        return render("forms/edit_customer", "Edit Customer")
                .addObject("customer", this.customerService.getEditBindingModel(id))
                .addObject("id", id);
    }

    @PostMapping("/create")
    public ModelAndView addCustomer(CustomerBindingModel model){
        Long id = this.customerService.addCustomer(model);
        if(id == null){
            return null;
        }
        return this.redirect("/customers/" + id);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editCustomer(CustomerBindingModel model, @PathVariable("id")Long id){

        Long customerId = this.customerService.editCustomer(model, id);
        if(customerId == null){
            return null;
        }
        return this.redirect("/customers/" + customerId);
    }


}
