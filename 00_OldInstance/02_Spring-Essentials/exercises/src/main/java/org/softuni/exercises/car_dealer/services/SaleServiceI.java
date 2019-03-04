package org.softuni.exercises.car_dealer.services;

import org.softuni.exercises.car_dealer.models.dtos.request.add_sell.AddSaleBindingModel;
import org.softuni.exercises.car_dealer.models.dtos.request.add_sell.ReviewSellBindingModel;
import org.softuni.exercises.car_dealer.models.dtos.responce.SaleInfoDto;
import org.softuni.exercises.car_dealer.models.entities.Sale;

import java.math.BigDecimal;
import java.util.List;

public interface SaleServiceI {
    default SaleInfoDto mapSaleToDto(Sale sale){
        return new SaleInfoDto()
                .setCustomerName(sale.getCustomer().getName())
                .setCarMake(sale.getCar().getMake())
                .setCarModel(sale.getCar().getModel())
                .setCarTraveledDistance(sale.getCar()
                        .getTravelledDistance())
                .setPrice(sale.getCar().getCarPrice())
                .setPriceWithDiscount(sale.getCar().getCarPrice()
                        .subtract(sale.getCar()
                                .getCarPrice()
                                .multiply(sale.getDiscount())))
                .setDiscountInPercent(sale.getDiscount()
                        .multiply(new BigDecimal(100)));
    }

    SaleInfoDto getSingleSale(Long id);

    List<SaleInfoDto> getAllSales();

    List<SaleInfoDto> getSalesWithDiscount();

    List<SaleInfoDto> getSalesWithDiscount(BigDecimal percent);

    ReviewSellBindingModel reviewSellBindingModel(AddSaleBindingModel addSaleBindingModel);

    Long createSale(ReviewSellBindingModel bindingModel);
}
