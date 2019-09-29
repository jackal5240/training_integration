package com.education.edu.controller;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.education.edu.EduApplicationTests;
import com.education.edu.model.Dto.FoodDto;
import com.google.gson.Gson;

@EnableWebMvc
@DisplayName("Test FoodController")
@SpringBootTest(classes = {FoodController.class })
public class FoodControllerTest extends EduApplicationTests {
    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void mockInit() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetFood() throws Exception {
        FoodDto foodDto = new FoodDto();
        foodDto.setFoodType("seaFood");

         Gson gson = new Gson();
         String json = gson.toJson(foodDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/getFood")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertEquals("{\"name\":\"魚\",\"price\":50}", mvcResult.getResponse().getContentAsString());
    }

}
