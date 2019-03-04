package org.softuni.exercises.car_dealer.models.dtos.request;

import java.math.BigDecimal;

public class EditPartBindingModel {

    private BigDecimal price;

    private Integer quantity;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
