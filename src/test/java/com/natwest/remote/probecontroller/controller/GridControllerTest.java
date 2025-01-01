package com.natwest.remote.probecontroller.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GridController.class)
class GridControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void initialize() throws Exception {
        mockMvc.perform(post("/grid/initialize")
                        .contentType("application/json")
                        .content("{\"length\": 10, \"breadth\": 10,\"obstacles\":[]}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Ocean Grid Initialized"));
    }
}