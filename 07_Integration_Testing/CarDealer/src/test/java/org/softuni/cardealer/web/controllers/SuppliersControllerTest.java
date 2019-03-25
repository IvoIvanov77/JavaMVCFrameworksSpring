package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.domain.models.binding.AddSupplierBindingModel;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SuppliersControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    @WithMockUser
    public void addSupplier_addItemToDB() throws Exception {

        this.mockMvc
                .perform(post("/suppliers/add")
                        .param("name", "Megaparts")
                        .param("isImporter", "on"));

        Assert.assertEquals(1, this.supplierRepository.count());
    }

    @Test
    @WithMockUser
    public void addSupplier_ReturnCorrectView() throws Exception {

        this.mockMvc
                .perform(post("/suppliers/add")
                        .param("name", "Megaparts")
                        .param("isImporter", "on"))
                .andExpect(view().name("redirect:all"));
    }

    @Test
    @WithMockUser
    public void editSupplier_worksCorrectly() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("Ivo");
        supplier.setIsImporter(false);

        supplier = this.supplierRepository.saveAndFlush(supplier);

        this.mockMvc
                .perform(post("/suppliers/edit/" + supplier.getId())
                        .param("name", "Megaparts")
                        .param("isImporter", "on"));
        Supplier actual = this.supplierRepository.findAll().get(0);

        Assert.assertEquals("Megaparts", actual.getName());
        Assert.assertTrue(actual.getIsImporter());

    }

    @Test
    @WithMockUser
    public void deleteSupplier_worksCorrectly() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("Ivo");
        supplier.setIsImporter(false);

        supplier = this.supplierRepository.saveAndFlush(supplier);

        Assert.assertEquals(1, this.supplierRepository.count());

        this.mockMvc
                .perform(post("/suppliers/delete/" + supplier.getId()));


        Assert.assertEquals(0, this.supplierRepository.count());
    }
}
