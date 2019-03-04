package org.softuni.exercises.car_dealer.services;

import org.softuni.exercises.car_dealer.models.dtos.request.CustomerBindingModel;
import org.softuni.exercises.car_dealer.models.dtos.request.add_sell.ChooseCustomerBindingModel;
import org.softuni.exercises.car_dealer.models.dtos.responce.CustomerInfoDto;
import org.softuni.exercises.car_dealer.models.entities.Customer;
import org.softuni.exercises.car_dealer.models.entities.Part;
import org.softuni.exercises.car_dealer.models.entities.Sale;
import org.softuni.exercises.car_dealer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final DateFormat SIMPLE_DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private BigDecimal getSalePrice(Sale sale) {
        BigDecimal totalSellPrice = new BigDecimal(0);
        for (Part part : sale.getCar().getParts()) {
            totalSellPrice = totalSellPrice.add(part.getPrice());
        }
        return totalSellPrice
                .subtract(totalSellPrice
                        .multiply(sale.getDiscount()));
    }

    @Override
    public List<Customer> getAllOrderedByBirthDate(){
        return this.customerRepository.findAllByOrderByBirthDateAsc();
    }

    @Override
    public CustomerInfoDto getCustomerSalesInfo(Long id) {
        Customer customer = this.customerRepository.getOne(id);
        Set<Sale> customerSales = customer.getSales();
        Integer totalSales = customerSales.size();
        BigDecimal totalSpentMoney = new BigDecimal(0);
        for (Sale sale : customerSales) {
            totalSpentMoney = totalSpentMoney.add(this.getSalePrice(sale));
        }
        return new CustomerInfoDto(customer.getName(), totalSales, totalSpentMoney);
    }

    @Override
    public CustomerBindingModel getEditBindingModel(Long id) {
        Customer customer = this.customerRepository.getOne(id);
        CustomerBindingModel editCustomerBindingModel = new CustomerBindingModel();
        editCustomerBindingModel.setName(customer.getName());
        editCustomerBindingModel.setBirthDate(
                SIMPLE_DATE_FORMAT.format(customer.getBirthDate()));
        return editCustomerBindingModel;
    }


    @Override
    public Long addCustomer(CustomerBindingModel model) {

        Customer customer = new Customer();
        return saveCustomer(model, customer);
    }

    @Override
    public Long editCustomer(CustomerBindingModel model, Long id) {
        Customer customer = this.customerRepository.getOne(id);
        return saveCustomer(model, customer);
    }

    @Override
    public List<ChooseCustomerBindingModel> listAllCustomersForSaleCreation(){
        return this.customerRepository.findAll()
                .stream()
                .map(ChooseCustomerBindingModel::new)
                .collect(Collectors.toList());
    }

    private Long saveCustomer(CustomerBindingModel model, Customer customer) {
        Date date = null;
        try {
            date = SIMPLE_DATE_FORMAT.parse(model.getBirthDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        customer.setName(model.getName());
        customer.setBirthDate(date);

        return this.customerRepository.saveAndFlush(customer).getId();
    }


}
