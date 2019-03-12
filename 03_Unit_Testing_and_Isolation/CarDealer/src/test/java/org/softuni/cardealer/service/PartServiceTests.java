package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.models.service.PartServiceModel;
import org.softuni.cardealer.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PartServiceTests {

    private static final String NAME_FENDER = "Fender";
    private static final String NAME_SPOILER = "Spoiler";
    private static final String INVALID_ID = "Invalid_id";
    private static final BigDecimal PRICE = new BigDecimal(10);

    @Autowired
    private PartRepository partRepository;
    private ModelMapper modelMapper;
    private PartServiceImpl partService;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        this.partService = new PartServiceImpl(this.partRepository, this.modelMapper);
    }

    private PartServiceModel initPartServiceModel() {
        PartServiceModel partServiceModel = new PartServiceModel();
        partServiceModel.setName(NAME_FENDER);
        partServiceModel.setPrice(PRICE);
        return partServiceModel;
    }

    @Test
    public void partService_savePartWithCorrectValues_ReturnCorrect() {
        PartServiceModel partServiceModel = this.initPartServiceModel();

        PartServiceModel actual = this.partService.savePart(partServiceModel);
        PartServiceModel expected = this.modelMapper
                .map(this.partRepository.findAll().get(0), PartServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
    }

    @Test(expected = Exception.class)
    public void partService_savePartWithIncorrectValues_ThrowException() {
        PartServiceModel partServiceModel = this.initPartServiceModel();
        partServiceModel.setName(null);

        this.partService.savePart(partServiceModel);
    }

    @Test
    public void partService_editPartWithCorrectValues_ReturnCorrect() {
        PartServiceModel partServiceModel = this.initPartServiceModel();

        PartServiceModel toBeEdited = this.partService.savePart(partServiceModel);
        toBeEdited.setName(NAME_SPOILER);
        PartServiceModel actual = this.partService.editPart(toBeEdited);
        PartServiceModel expected = this.modelMapper
                .map(this.partRepository.findAll().get(0), PartServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(expected.getName(), NAME_SPOILER);
    }

    @Test
    public void partService_deletePartWithCorrectId_ReturnCorrect() {
        PartServiceModel partServiceModel = this.initPartServiceModel();

        PartServiceModel actual = this.partService.savePart(partServiceModel);
        PartServiceModel expected = this.partService.deletePart(actual.getId());

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(0L, this.partRepository.count());
    }

    @Test(expected = Exception.class)
    public void partService_deletePartWithIncorrectId_ThrowException() {
        PartServiceModel partServiceModel = this.initPartServiceModel();

        this.partService.savePart(partServiceModel);
        this.partService.deletePart(INVALID_ID);
    }

    @Test
    public void partService_findPartByCorrectId_ReturnCorrect() {
        PartServiceModel partServiceModel = this.initPartServiceModel();

        PartServiceModel toBeFind = this.partService.savePart(partServiceModel);

        PartServiceModel actual = this.partService.findPartById(toBeFind.getId());
        PartServiceModel expected = this.modelMapper
                .map(this.partRepository.findAll().get(0), PartServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
    }

    @Test(expected = Exception.class)
    public void partService_findPartByIncorrectId_ThrowException() {
        PartServiceModel partServiceModel = this.initPartServiceModel();

        this.partService.savePart(partServiceModel);

        this.partService.findPartById(INVALID_ID);
    }
}
