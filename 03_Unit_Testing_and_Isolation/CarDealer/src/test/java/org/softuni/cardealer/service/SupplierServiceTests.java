package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.models.service.SupplierServiceModel;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SupplierServiceTests {

    private static final String NAME_IVAYLO = "Ivaylo";
    private static final String NAME_PESHO = "Pesho";
    private static final String INVALID_ID = "Invalid_id";

    @Autowired
    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;
    private SupplierServiceImpl supplierService;

    @Before
    public void init(){
        this.modelMapper = new ModelMapper();
        this.supplierService = new SupplierServiceImpl(this.supplierRepository, this.modelMapper);
    }

    private SupplierServiceModel initSupplierServiceModel(){
        SupplierServiceModel supplierServiceModel = new SupplierServiceModel();
        supplierServiceModel.setName(NAME_IVAYLO);
        supplierServiceModel.setImporter(true);
        return supplierServiceModel;
    }

    @Test
    public void supplierService_saveSupplierWithCorrectValues_ReturnCorrect(){
        SupplierServiceModel supplierServiceModel = this.initSupplierServiceModel();

        SupplierServiceModel actual = this.supplierService.saveSupplier(supplierServiceModel);
        SupplierServiceModel expected = this.modelMapper
                .map(this.supplierRepository.findAll().get(0), SupplierServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.isImporter(), actual.isImporter());
    }

    @Test(expected = Exception.class)
    public void supplierService_saveSupplierWithIncorrectValues_ThrowException(){
        SupplierServiceModel supplierServiceModel = new SupplierServiceModel();
        supplierServiceModel.setName(null);
        supplierServiceModel.setImporter(true);

        this.supplierService.saveSupplier(supplierServiceModel);
    }

    @Test
    public void supplierService_editSupplierWithCorrectValues_ReturnCorrect(){
        SupplierServiceModel supplierServiceModel = this.initSupplierServiceModel();

        SupplierServiceModel toBeEdited = this.supplierService.saveSupplier(supplierServiceModel);
        toBeEdited.setName(NAME_PESHO);
        SupplierServiceModel actual = this.supplierService.editSupplier(toBeEdited);
        SupplierServiceModel expected = this.modelMapper
                .map(this.supplierRepository.findAll().get(0), SupplierServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getName(), NAME_PESHO);
        Assert.assertEquals(expected.isImporter(), actual.isImporter());
    }

    @Test
    public void supplierService_deleteSupplierWithCorrectId_ReturnCorrect(){
        SupplierServiceModel supplierServiceModel = this.initSupplierServiceModel();

        SupplierServiceModel actual = this.supplierService.saveSupplier(supplierServiceModel);
        SupplierServiceModel expected = this.supplierService.deleteSupplier(actual.getId());


        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.isImporter(), actual.isImporter());
        Assert.assertEquals(this.supplierRepository.count(),0);
    }

    @Test(expected = Exception.class)
    public void supplierService_deleteSupplierWithIncorrectId_ThrowException(){
        SupplierServiceModel supplierServiceModel = this.initSupplierServiceModel();

        this.supplierService.saveSupplier(supplierServiceModel);

        this.supplierService.deleteSupplier(INVALID_ID);
    }

    @Test
    public void supplierService_findSupplierByCorrectId_ReturnCorrect(){
        SupplierServiceModel supplierServiceModel = this.initSupplierServiceModel();

        SupplierServiceModel tobeFind = this.supplierService.saveSupplier(supplierServiceModel);
        SupplierServiceModel actual = this.supplierService.findSupplierById(tobeFind.getId());
        SupplierServiceModel expected = this.modelMapper
                .map(this.supplierRepository.findAll().get(0), SupplierServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.isImporter(), actual.isImporter());
    }

    @Test(expected = Exception.class)
    public void supplierService_findSupplierWithIncorrectId_ThrowException(){
        SupplierServiceModel supplierServiceModel = this.initSupplierServiceModel();

        this.supplierService.saveSupplier(supplierServiceModel);

        this.supplierService.findSupplierById(INVALID_ID);
    }
}
