package org.softuni.exercises.car_dealer.controllers;

import org.softuni.exercises.car_dealer.models.dtos.request.add_sell.AddSaleBindingModel;
import org.softuni.exercises.car_dealer.models.dtos.request.add_sell.ReviewSellBindingModel;
import org.softuni.exercises.car_dealer.services.CarService;
import org.softuni.exercises.car_dealer.services.CustomerService;
import org.softuni.exercises.car_dealer.services.SaleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@RequestMapping("/sales")
public class SaleController extends BaseController{
    private final SaleServiceI saleService;

    private final CustomerService customerService;

    private final CarService carService;

    @Autowired
    public SaleController(SaleServiceI saleService, CustomerService customerService, CarService carService) {
        this.saleService = saleService;
        this.customerService = customerService;
        this.carService = carService;
    }

    @GetMapping("")
    public ModelAndView getAllSales(){
        return render("sales/sales_list", "All sales")
                .addObject("sales", this.saleService.getAllSales());

    }

    @GetMapping("/discounted")
    public ModelAndView getDiscountedSales(){
        return render("sales/sales_list", "Discounted sales")
                .addObject("sales", this.saleService.getSalesWithDiscount());
    }

    @GetMapping("/discounted/{percent}")
    public ModelAndView getDiscountedSalesWithGivenPercent(@PathVariable String percent){
        return render("sales/sales_list", "Discount" + percent)
                .addObject("sales",
                this.saleService.getSalesWithDiscount(new BigDecimal(percent)));
    }

    @GetMapping("/{id}")
    public ModelAndView getSingleSaleDetails(@PathVariable Long id){
        return render("sales/details", "Sale details")
            .addObject("sale", this.saleService.getSingleSale(id));
    }

    @GetMapping("/create")
    public ModelAndView createSaleView(){
        if(!this.hasLoggedInUser()){
            return this.redirect("/user/login");
        }
        return render("forms/add_sale", "Add Sale")
                .addObject("model", new AddSaleBindingModel())
                .addObject("carsList", this.carService.listAllCarsForSaleCreation())
                .addObject("customersList", this.customerService.listAllCustomersForSaleCreation());
    }

    @PostMapping("/review")
    public ModelAndView reviewSaleAction(AddSaleBindingModel model){
        if(!this.hasLoggedInUser()){
            return this.redirect("/user/login");
        }

        return render("forms/confirm_sale_creation", "Review Sale Creation")
                .addObject("model", this.saleService.reviewSellBindingModel(model));

    }

    @PostMapping("/create")
    public ModelAndView reviewSaleAction(@ModelAttribute ReviewSellBindingModel model){
        if(!this.hasLoggedInUser()){
            return this.redirect("/user/login");
        }
        System.out.println(model.getCarId());
        Long id = this.saleService.createSale(model);
        return id != null
                ? this.redirect("/sales/" + id)
                : this.redirect("/sales");

    }
}
