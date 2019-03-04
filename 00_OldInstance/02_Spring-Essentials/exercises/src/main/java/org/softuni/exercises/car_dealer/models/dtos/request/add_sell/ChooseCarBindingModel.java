package org.softuni.exercises.car_dealer.models.dtos.request.add_sell;

import org.softuni.exercises.car_dealer.models.entities.Car;

public class ChooseCarBindingModel {
    private Long id;

    private String makeAndModel;

    public ChooseCarBindingModel() {
    }

    public ChooseCarBindingModel(Car car) {
        this.initBindingModel(car);
    }

    private void initBindingModel(Car car){
        this.id = car.getId();
        this.makeAndModel = String.format("%s %s ", car.getMake(), car.getModel());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMakeAndModel() {
        return makeAndModel;
    }

    public void setMakeAndModel(String makeAndModel) {
        this.makeAndModel = makeAndModel;
    }
}
