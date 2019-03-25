package org.softuni.cardealer.web.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void index_ReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser
    public void home_ReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/home"))
                .andExpect(view().name("home"));
    }

}
