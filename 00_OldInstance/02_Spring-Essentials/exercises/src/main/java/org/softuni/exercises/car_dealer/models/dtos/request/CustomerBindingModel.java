package org.softuni.exercises.car_dealer.models.dtos.request;

public class CustomerBindingModel {

    private String name;

    private String birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
