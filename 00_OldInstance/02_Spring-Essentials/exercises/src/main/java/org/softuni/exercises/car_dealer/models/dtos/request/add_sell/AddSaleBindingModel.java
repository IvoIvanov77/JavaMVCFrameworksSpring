package org.softuni.exercises.car_dealer.models.dtos.request.add_sell;

import java.math.BigDecimal;

public class AddSaleBindingModel {
    private Long customerId;

    private Long carId;

    private Byte discount;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Byte getDiscount() {
        return discount;
    }

    public void setDiscount(Byte discount) {
        this.discount = discount;
    }
}
