package org.softuni.exercises.car_dealer.models.dtos.request;

import org.softuni.exercises.car_dealer.models.entities.Supplier;

public class SupplierBindingModel {

    private Long id;

    private String name;

    public SupplierBindingModel() {
    }

    public SupplierBindingModel(Supplier supplier) {
        this.id = supplier.getId();
        this.name = supplier.getName();
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
