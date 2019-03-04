package org.softuni.exercises.car_dealer.models.dtos.responce;

import java.math.BigDecimal;

public class SaleInfoDto {
//    car (make, model, travelled distance), customer and price of the sale with and without discount
//    and the discount percent itself.
    private String carModel;
    private String carMake;
    private BigDecimal carTraveledDistance;
    private String customerName;
    private BigDecimal price;
    private BigDecimal priceWithDiscount;
    private BigDecimal discountInPercent;


    public String getCarModel() {
        return carModel;
    }

    public SaleInfoDto setCarModel(String carModel) {
        this.carModel = carModel;
        return this;
    }

    public String getCarMake() {
        return carMake;
    }

    public SaleInfoDto setCarMake(String carMake) {
        this.carMake = carMake;
        return this;
    }

    public BigDecimal getCarTraveledDistance() {
        return carTraveledDistance;
    }

    public SaleInfoDto setCarTraveledDistance(BigDecimal carTraveledDistance) {
        this.carTraveledDistance = carTraveledDistance;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public SaleInfoDto setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public BigDecimal getPrice() {
        return price;

    }

    public SaleInfoDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public SaleInfoDto setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
        return this;
    }

    public BigDecimal getDiscountInPercent() {
        return discountInPercent;
    }

    public SaleInfoDto setDiscountInPercent(BigDecimal discountInPercent) {
        this.discountInPercent = discountInPercent;
        return this;
    }
}

