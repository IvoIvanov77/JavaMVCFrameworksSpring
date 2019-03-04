package org.softuni.exercises.car_dealer.services;

import org.softuni.exercises.car_dealer.models.dtos.request.CustomerBindingModel;
import org.softuni.exercises.car_dealer.models.dtos.request.add_sell.ChooseCustomerBindingModel;
import org.softuni.exercises.car_dealer.models.entities.Customer;
import org.softuni.exercises.car_dealer.models.dtos.responce.CustomerInfoDto;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllOrderedByBirthDate();

    CustomerInfoDto getCustomerSalesInfo(Long id);

    CustomerBindingModel getEditBindingModel(Long id);

    Long addCustomer(CustomerBindingModel model);

    Long editCustomer(CustomerBindingModel model, Long id);

    List<ChooseCustomerBindingModel> listAllCustomersForSaleCreation();
}
