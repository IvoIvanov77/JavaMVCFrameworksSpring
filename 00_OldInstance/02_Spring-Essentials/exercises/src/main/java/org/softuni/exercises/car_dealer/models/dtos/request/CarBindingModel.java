package org.softuni.exercises.car_dealer.models.dtos.request;

import java.math.BigDecimal;
import java.util.List;

public class CarBindingModel {
    private String make;

    private String model;

    private BigDecimal travelledDistance;

    private List<Long> partsIds;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(BigDecimal travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public List<Long> getPartsIds() {
        return partsIds;
    }

    public void setPartsIds(List<Long> partsIds) {
        this.partsIds = partsIds;
    }
}
