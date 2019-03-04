package org.softuni.exercises.car_dealer.services;

import org.softuni.exercises.car_dealer.models.dtos.request.CarBindingModel;
import org.softuni.exercises.car_dealer.models.dtos.request.add_sell.ChooseCarBindingModel;
import org.softuni.exercises.car_dealer.models.entities.Car;

import java.util.List;

public interface CarService {

    List<Car> getCarsByMake(String make);

    Car getCarById(Long id);

    List<Car> getAllCars();

    Long createCar(CarBindingModel bindingModel);

    List<ChooseCarBindingModel> listAllCarsForSaleCreation();
}
