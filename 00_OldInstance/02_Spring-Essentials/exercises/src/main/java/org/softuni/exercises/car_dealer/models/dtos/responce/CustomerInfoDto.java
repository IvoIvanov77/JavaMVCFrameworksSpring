package org.softuni.exercises.car_dealer.models.dtos.responce;

import java.math.BigDecimal;

public class CustomerInfoDto {

    private String name;

    private Integer boughtCarsCount;

    private BigDecimal totalSpentMoney;

    public CustomerInfoDto() {
    }

    public CustomerInfoDto(String name, Integer boughtCarsCount, BigDecimal totalSpentMoney) {
        this.name = name;
        this.boughtCarsCount = boughtCarsCount;
        this.totalSpentMoney = totalSpentMoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBoughtCarsCount() {
        return boughtCarsCount;
    }

    public void setBoughtCarsCount(Integer boughtCarsCount) {
        this.boughtCarsCount = boughtCarsCount;
    }

    public BigDecimal getTotalSpentMoney() {
        return totalSpentMoney;
    }

    public void setTotalSpentMoney(BigDecimal totalSpentMoney) {
        this.totalSpentMoney = totalSpentMoney;
    }
}
