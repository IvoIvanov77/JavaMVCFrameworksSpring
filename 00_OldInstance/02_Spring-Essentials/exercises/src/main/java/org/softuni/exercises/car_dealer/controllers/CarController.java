package org.softuni.exercises.car_dealer.controllers;

import org.softuni.exercises.car_dealer.models.dtos.request.CarBindingModel;
import org.softuni.exercises.car_dealer.models.entities.Car;
import org.softuni.exercises.car_dealer.services.CarService;
import org.softuni.exercises.car_dealer.services.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController extends BaseController{

    private final CarService carService;

    private final PartService partService;

    @Autowired
    public CarController(CarService carService, PartService partService) {
        this.carService = carService;
        this.partService = partService;
    }

//    @GetMapping("/all")
//    public ModelAndView getAllCars(){
//        List<Car> carsByMake = this.carService.getAllCars();
//        return render("cars/cars_list", "All Cars")
//                .addObject("cars", carsByMake);
//    }

    @GetMapping("/{make}")
    public ModelAndView getAllByMake(@PathVariable String make){
        List<Car> carsByMake = make.equals("all")
                ? this.carService.getAllCars()
                : this.carService.getCarsByMake(make) ;
        return render("cars/cars_list", "Cars: " + make)
                .addObject("cars", carsByMake);
    }

    @GetMapping("/{id}/parts")
    public ModelAndView getCarParts(@PathVariable Long id){
        Car car = this.carService.getCarById(id);
        String pageTitle = String.format("%s %s parts", car.getMake(), car.getModel());
        return render("cars/car_parts", pageTitle)
                .addObject("parts", this.partService.getPartsByCarId(id))
                .addObject("car", car);

    }

    @GetMapping("/create")
    public ModelAndView createCarView(){
        if(this.hasLoggedInUser()){
            return render("forms/add_car", "Add Car")
                    .addObject("model", new CarBindingModel())
                    .addObject("parts", partService.getAll());
        }
        return new ModelAndView("redirect:/user/login");

    }

    @PostMapping("/create")
    public ModelAndView createCarAction(@ModelAttribute CarBindingModel carBindingModel){
       Long createdCarId = this.carService.createCar(carBindingModel);
       String url = String.format("/cars/%d/parts", createdCarId);
       return this.redirect(url);
    }
}
