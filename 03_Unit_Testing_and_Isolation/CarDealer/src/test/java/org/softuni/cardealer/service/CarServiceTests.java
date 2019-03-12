package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.models.service.CarServiceModel;
import org.softuni.cardealer.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CarServiceTests {

    private static final String MAKE_FORD = "Ford";
    private static final String MAKE_AUDI = "Audi";
    private static final String INVALID_ID = "Invalid_id";
    private static final String MODEL_MONDEO = "Mondeo";
    private static final long TRAVELLED_DISTANCE = 0L;
    @Autowired
    private CarRepository carRepository;
    private ModelMapper modelMapper;
    private CarServiceImpl carService;

    @Before
    public void init(){
        this.modelMapper = new ModelMapper();
        this.carService = new CarServiceImpl(this.carRepository, this.modelMapper);
    }

    private CarServiceModel initCarServiceModel(){
        CarServiceModel carServiceModel = new CarServiceModel();
        carServiceModel.setMake(MAKE_FORD);
        carServiceModel.setModel(MODEL_MONDEO);
        carServiceModel.setTravelledDistance(TRAVELLED_DISTANCE);
        return carServiceModel;
    }

    @Test
    public void carService_saveCarWithCorrectValues_ReturnCorrect(){
        CarServiceModel carServiceModel = this.initCarServiceModel();

        CarServiceModel actual = this.carService.saveCar(carServiceModel);
        CarServiceModel expected = this.modelMapper
                .map(this.carRepository.findAll().get(0), CarServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getMake(), actual.getMake());
        Assert.assertEquals(expected.getModel(), actual.getModel());
        Assert.assertEquals(expected.getTravelledDistance(), actual.getTravelledDistance());
    }

    @Test(expected = Exception.class)
    public void carService_saveCarWithIncorrectValues_ThrowException(){
        CarServiceModel carServiceModel = this.initCarServiceModel();
        carServiceModel.setMake(null);

        this.carService.saveCar(carServiceModel);
    }

    @Test
    public void carService_editCarWithCorrectValues_ReturnCorrect(){
        CarServiceModel carServiceModel = this.initCarServiceModel();

        CarServiceModel toBeEdited = this.carService.saveCar(carServiceModel);
        toBeEdited.setMake(MAKE_AUDI);
        CarServiceModel actual = this.carService.editCar(toBeEdited);
        CarServiceModel expected = this.modelMapper
                .map(this.carRepository.findAll().get(0), CarServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getMake(), actual.getMake());
        Assert.assertEquals(expected.getModel(), actual.getModel());
        Assert.assertEquals(expected.getTravelledDistance(), actual.getTravelledDistance());
        Assert.assertEquals(expected.getMake(), MAKE_AUDI);

    }

    @Test
    public void carService_deleteCarWithCorrectId_ReturnCorrect(){
        CarServiceModel carServiceModel = this.initCarServiceModel();

        CarServiceModel actual = this.carService.saveCar(carServiceModel);
        CarServiceModel expected = this.carService.deleteCar(actual.getId());

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getMake(), actual.getMake());
        Assert.assertEquals(expected.getModel(), actual.getModel());
        Assert.assertEquals(expected.getTravelledDistance(), actual.getTravelledDistance());
        Assert.assertEquals(this.carRepository.count(),0);
    }

    @Test(expected = Exception.class)
    public void carService_deleteCarWithIncorrectId_ThrowException(){
        CarServiceModel carServiceModel = this.initCarServiceModel();

        this.carService.saveCar(carServiceModel);

        this.carService.deleteCar(INVALID_ID);
    }

    @Test
    public void carService_findCarByCorrectId_ReturnCorrect(){
        CarServiceModel carServiceModel = this.initCarServiceModel();

        CarServiceModel tobeFind = this.carService.saveCar(carServiceModel);
        CarServiceModel actual = this.carService.findCarById(tobeFind.getId());
        CarServiceModel expected = this.modelMapper
                .map(this.carRepository.findAll().get(0), CarServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getMake(), actual.getMake());
        Assert.assertEquals(expected.getModel(), actual.getModel());
        Assert.assertEquals(expected.getTravelledDistance(), actual.getTravelledDistance());
    }

    @Test(expected = Exception.class)
    public void carService_findCarWithIncorrectId_ThrowException(){
        CarServiceModel carServiceModel = this.initCarServiceModel();

        this.carService.saveCar(carServiceModel);

        this.carService.findCarById(INVALID_ID);
    }
}
