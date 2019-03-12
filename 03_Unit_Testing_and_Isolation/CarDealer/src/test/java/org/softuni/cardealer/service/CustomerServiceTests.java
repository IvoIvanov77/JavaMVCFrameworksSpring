package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.models.service.CustomerServiceModel;
import org.softuni.cardealer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerServiceTests {

    private static final String NAME_IVAYLO = "Ivaylo";
    private static final String NAME_PESHO = "Pesho";
    private static final String INVALID_ID = "Invalid_id";
    private static final LocalDate BIRTH_DATE = LocalDate.parse("2019-02-10");
    @Autowired
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    private CustomerServiceImpl customerService;

    @Before
    public void init(){
        this.modelMapper = new ModelMapper();
        this.customerService = new CustomerServiceImpl(this.customerRepository, this.modelMapper);
    }

    private CustomerServiceModel initCustomerServiceModel(){
        CustomerServiceModel customerServiceModel = new CustomerServiceModel();
        customerServiceModel.setName(NAME_IVAYLO);
        customerServiceModel.setBirthDate(BIRTH_DATE);
        customerServiceModel.setYoungDriver(true);
        return customerServiceModel;
    }

    @Test
    public void customerService_saveCustomerWithCorrectValues_ReturnCorrect(){
        CustomerServiceModel customerServiceModel = this.initCustomerServiceModel();

        CustomerServiceModel actual = this.customerService.saveCustomer(customerServiceModel);
        CustomerServiceModel expected = this.modelMapper
                .map(this.customerRepository.findAll().get(0), CustomerServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getBirthDate(), actual.getBirthDate());
        Assert.assertEquals(expected.isYoungDriver(), actual.isYoungDriver());
    }

    @Test(expected = Exception.class)
    public void customerService_saveCustomerWithIncorrectValues_ThrowException(){
        CustomerServiceModel customerServiceModel = this.initCustomerServiceModel();
        customerServiceModel.setName(null);

        this.customerService.saveCustomer(customerServiceModel);
    }

    @Test
    public void customerService_editCustomerWithCorrectValues_ReturnCorrect(){
        CustomerServiceModel customerServiceModel = this.initCustomerServiceModel();

        CustomerServiceModel toBeEdited = this.customerService.saveCustomer(customerServiceModel);
        toBeEdited.setName(NAME_PESHO);
        CustomerServiceModel actual = this.customerService.editCustomer(toBeEdited);
        CustomerServiceModel expected = this.modelMapper
                .map(this.customerRepository.findAll().get(0), CustomerServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getBirthDate(), actual.getBirthDate());
        Assert.assertEquals(expected.isYoungDriver(), actual.isYoungDriver());
        Assert.assertEquals(expected.getName(), NAME_PESHO);

    }

    @Test
    public void customerService_deleteCustomerWithCorrectId_ReturnCorrect(){
        CustomerServiceModel customerServiceModel = this.initCustomerServiceModel();

        CustomerServiceModel actual = this.customerService.saveCustomer(customerServiceModel);
        Assert.assertEquals(1L, this.customerRepository.count());
        CustomerServiceModel expected = this.customerService.deleteCustomer(actual.getId());

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getBirthDate(), actual.getBirthDate());
        Assert.assertEquals(expected.isYoungDriver(), actual.isYoungDriver());
        Assert.assertEquals(0L, this.customerRepository.count());
    }

    @Test(expected = Exception.class)
    public void customerService_customerSupplierWithIncorrectId_ThrowException(){
        CustomerServiceModel customerServiceModel = this.initCustomerServiceModel();

        this.customerService.saveCustomer(customerServiceModel);
       this.customerService.deleteCustomer(INVALID_ID);
    }

    @Test
    public void customerService_findCustomerByCorrectId_ReturnCorrect(){
        CustomerServiceModel customerServiceModel = this.initCustomerServiceModel();

        CustomerServiceModel toBeFind = this.customerService.saveCustomer(customerServiceModel);

        CustomerServiceModel actual = this.customerService.findCustomerById(toBeFind.getId());
        CustomerServiceModel expected = this.modelMapper
                .map(this.customerRepository.findAll().get(0), CustomerServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getBirthDate(), actual.getBirthDate());
        Assert.assertEquals(expected.isYoungDriver(), actual.isYoungDriver());

    }

    @Test(expected = Exception.class)
    public void customerService_findCustomerByIncorrectId_ThrowException(){
        CustomerServiceModel customerServiceModel = this.initCustomerServiceModel();

       this.customerService.saveCustomer(customerServiceModel);

        this.customerService.findCustomerById(INVALID_ID);
    }
}
