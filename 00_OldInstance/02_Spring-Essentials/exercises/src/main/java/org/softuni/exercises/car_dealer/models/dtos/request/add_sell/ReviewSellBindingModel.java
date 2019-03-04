package org.softuni.exercises.car_dealer.models.dtos.request.add_sell;

import org.softuni.exercises.car_dealer.models.entities.Car;
import org.softuni.exercises.car_dealer.models.entities.Customer;

import java.math.BigDecimal;

public class ReviewSellBindingModel {
    private Long customerId;

    private Long carId;

    private String customerName;

    private String carMakeAndModelName;

    private Byte discount;

    private BigDecimal carPrice;

    private BigDecimal finalCarPrice;

    public ReviewSellBindingModel() {
    }

    public ReviewSellBindingModel(Customer customer, Car car, Byte discount) {
        this.customerId = customer.getId();
        this.carId = car.getId();
        this.customerName = customer.getName();
        this.carMakeAndModelName = String.format("%s %s", car.getMake(), car.getModel());
        this.discount = discount;
        this.carPrice = car.getCarPrice();
        this.finalCarPrice = car.getCarPrice()
                .subtract(car.getCarPrice()
                        .multiply(new BigDecimal(discount))
                        .divide(new BigDecimal(100), 2));
    }

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCarMakeAndModelName() {
        return carMakeAndModelName;
    }

    public void setCarMakeAndModelName(String carMakeAndModelName) {
        this.carMakeAndModelName = carMakeAndModelName;
    }

    public Byte getDiscount() {
        return discount;
    }

    public void setDiscount(Byte discount) {
        this.discount = discount;
    }

    public BigDecimal getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(BigDecimal carPrice) {
        this.carPrice = carPrice;
    }

    public BigDecimal getFinalCarPrice() {
        return finalCarPrice;
    }

    public void setFinalCarPrice(BigDecimal finalCarPrice) {
        this.finalCarPrice = finalCarPrice;
    }
}
