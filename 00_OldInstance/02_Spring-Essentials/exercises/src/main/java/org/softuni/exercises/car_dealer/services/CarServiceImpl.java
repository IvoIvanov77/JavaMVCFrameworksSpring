package org.softuni.exercises.car_dealer.services;

import org.softuni.exercises.car_dealer.models.dtos.request.CarBindingModel;
import org.softuni.exercises.car_dealer.models.dtos.request.add_sell.ChooseCarBindingModel;
import org.softuni.exercises.car_dealer.models.entities.Car;
import org.softuni.exercises.car_dealer.models.entities.Part;
import org.softuni.exercises.car_dealer.repositories.CarRepository;
import org.softuni.exercises.car_dealer.repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;
    private final PartRepository partRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
    }

    private Car saveCar(CarBindingModel model, Car car) {
        car.setMake(model.getMake());
        car.setModel(model.getModel());
        car.setTravelledDistance(model.getTravelledDistance());
        Set<Part> carParts = this.partRepository.findByIds(model.getPartsIds());
        car.setParts(carParts);
        return this.carRepository.saveAndFlush(car);
    }

    @Override
    public List<Car> getCarsByMake(String make){
        return this.carRepository.findAllByMake(make)
                .stream()
                .sorted(Comparator.comparing(Car::getModel)
                        .thenComparing((car1, car2) ->
                                car2.getTravelledDistance().compareTo(car1.getTravelledDistance())))
                .collect(Collectors.toList());
    }

    @Override
    public Car getCarById(Long id) {
        return this.carRepository.getOne(id);
    }

    @Override
    public List<Car> getAllCars() {
        return this.carRepository.findAll();
    }

    @Override
    public Long createCar(CarBindingModel bindingModel){
        return this.saveCar(bindingModel, new Car()).getId();
    }

    @Override
    public List<ChooseCarBindingModel> listAllCarsForSaleCreation(){
        return this.carRepository.findAll()
                .stream()
                .map(ChooseCarBindingModel::new)
                .collect(Collectors.toList());
    }
}
