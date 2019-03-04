package org.softuni.exercises.car_dealer.services;


import org.softuni.exercises.car_dealer.models.dtos.request.add_sell.AddSaleBindingModel;
import org.softuni.exercises.car_dealer.models.dtos.request.add_sell.ReviewSellBindingModel;
import org.softuni.exercises.car_dealer.models.entities.Car;
import org.softuni.exercises.car_dealer.models.entities.Customer;
import org.softuni.exercises.car_dealer.models.entities.Sale;
import org.softuni.exercises.car_dealer.models.dtos.responce.SaleInfoDto;
import org.softuni.exercises.car_dealer.repositories.CarRepository;
import org.softuni.exercises.car_dealer.repositories.CustomerRepository;
import org.softuni.exercises.car_dealer.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleServiceI {

    private final SaleRepository saleRepository;

    private final CustomerRepository customerRepository;

    private final CarRepository carRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository,
                           CustomerRepository customerRepository,
                           CarRepository carRepository) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

    @Override
    public SaleInfoDto getSingleSale(Long id){
        return this.mapSaleToDto(this.saleRepository.getOne(id));
    }

    @Override
    public List<SaleInfoDto> getAllSales(){
        List<Sale> allSales = this.saleRepository.findAll();

        return allSales
                .stream()
                .map(this::mapSaleToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SaleInfoDto> getSalesWithDiscount(){
        return this.getAllSales()
                .stream()
                .filter(s ->
                        s.getPrice().compareTo(s.getPriceWithDiscount()) != 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<SaleInfoDto> getSalesWithDiscount(BigDecimal percent){
        return this.getAllSales()
                .stream()
                .filter(s ->
                        s.getDiscountInPercent()
                                .compareTo(percent) == 0)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewSellBindingModel reviewSellBindingModel(AddSaleBindingModel addSaleBindingModel){
        Car car = this.carRepository.getOne(addSaleBindingModel.getCarId());
        Customer customer = this.customerRepository
                .getOne(addSaleBindingModel.getCustomerId());

        return new ReviewSellBindingModel(customer, car,
                addSaleBindingModel.getDiscount());
    }

    @Override
    public Long createSale(ReviewSellBindingModel bindingModel){
        Sale sale = new Sale();
        sale.setCustomer(this.customerRepository.getOne(bindingModel.getCustomerId()));
        sale.setCar(this.carRepository.getOne(bindingModel.getCarId()));
        sale.setDiscount(new BigDecimal(bindingModel.getDiscount()/ 100.0));

        Sale result = this.saleRepository.saveAndFlush(sale);

        return result != null ? result.getId() : null;
    }

}
