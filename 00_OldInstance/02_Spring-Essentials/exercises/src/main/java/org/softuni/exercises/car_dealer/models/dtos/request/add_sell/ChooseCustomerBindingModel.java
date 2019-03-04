package org.softuni.exercises.car_dealer.models.dtos.request.add_sell;

import org.softuni.exercises.car_dealer.models.entities.Customer;

public class ChooseCustomerBindingModel {
    private Long id;

    private String name;

    public ChooseCustomerBindingModel() {
    }

    public ChooseCustomerBindingModel(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
