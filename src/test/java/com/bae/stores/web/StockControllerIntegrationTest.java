package com.bae.stores.web;

import com.bae.stores.domain.Stock;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:storesSchema.sql", "classpath:storesData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class StockControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testCreate() throws Exception{
        Stock testStock = new Stock("Round Bar", "Metal", 2);
        String testStockAsJSON = this.mapper.writeValueAsString(testStock);
        RequestBuilder req = post("/create").content(testStockAsJSON).contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkStatus = status().isCreated();

        Stock testStockCreated = new Stock(2,"Round Bar", "Metal", 2);
        String testStockCreatedJSON = this.mapper.writeValueAsString(testStockCreated);
        ResultMatcher checkBody = content().json(testStockCreatedJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testGetAll () throws Exception{
        Stock firstStock = new Stock (1, "Round Bar", "Metal", 3);
        String firstStockAsJSON = this.mapper.writeValueAsString(List.of(firstStock));
        RequestBuilder req = get("/getAll").contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkBody = content().json(firstStockAsJSON);
        ResultMatcher checkStatus = status().isOk();

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testGetId () throws Exception{
        Stock firstStock = new Stock(1,"Round Bar", "Metal", 3);
        String firstStockAsJSON = this.mapper.writeValueAsString(firstStock);
        RequestBuilder req = get("/get/" + firstStock.getId()).content(firstStockAsJSON).contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkBody = content().json(firstStockAsJSON);
        ResultMatcher checkStatus = status().isOk();

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testGetAllCategory () throws Exception{
        Stock firstStock = new Stock (1,"Round Bar", "Metal", 3);
        String firstStockAsJSON = this.mapper.writeValueAsString(List.of(firstStock));
        RequestBuilder req = get("/getAllCategory/" + firstStock.getCategory()).contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkBody = content().json(firstStockAsJSON);
        ResultMatcher checkStatus = status().isOk();

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }


    @Test
    void testReplace () throws Exception {
        Stock firstStock = new Stock (1,"Round Bar", "Metal", 3);

        Stock updatedStock = new Stock (1,"Round Bar", "Metal", 5);
        String updatedStockAsJSON = this.mapper.writeValueAsString(updatedStock);

        RequestBuilder req = put("/replace/" + firstStock.getId())
                .content(updatedStockAsJSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkBody = content().json(updatedStockAsJSON);
        ResultMatcher checkStatus = status().isAccepted();

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testRemove () throws Exception {
        this.mvc.perform(delete("/remove/1")).andExpect(status().isNoContent());
    }
}
