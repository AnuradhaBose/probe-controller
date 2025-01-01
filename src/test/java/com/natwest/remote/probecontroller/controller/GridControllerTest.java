package com.natwest.remote.probecontroller.controller;

import com.natwest.remote.probecontroller.dao.OceanGrid;
import com.natwest.remote.probecontroller.service.ProbeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GridController.class)
class GridControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProbeService service;

    @Test
    void initialize_success() throws Exception {
        doNothing().when(service).initializeGrid(new OceanGrid(5, 5,null));
        mockMvc.perform(post("/grid/initialize")
                        .contentType("application/json")
                        .content("{\"length\": 10, \"breadth\": 10,\"obstacles\":[]}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Ocean Grid Initialized"));
    }

    @Test
    void initialize_failure() throws Exception {
        doThrow(new IllegalArgumentException("Index not in range"))
                .when(service).initializeGrid(any(OceanGrid.class));
        mockMvc.perform(post("/grid/initialize")
                        .contentType("application/json")
                        .content("{\"length\": -1, \"breadth\": 5,\"obstacles\":[]}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Index not in range"));
    }
}