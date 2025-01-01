package com.natwest.remote.probecontroller.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProbeController.class)
class ProbeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void initializeProbe() throws Exception {
        mockMvc.perform(post("/probe/initialize")
                        .contentType("application/json")
                        .content("{\"point\": {\"x\":1 , \"y\":1}, \"direction\": \"NORTH\",\"visited\":[]}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Probe Initialized"));
    }

    @Test
    void executeCommands() throws Exception {
        mockMvc.perform(post("/probe/move")
                        .contentType("application/json")
                        .content("[\"RIGHT\",\"FORWARD\"]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.x").value(1))
                .andExpect(jsonPath("$.y").value(1));
    }

    @Test
    void getVisitedCoordinates() throws Exception{
        mockMvc.perform(get("/probe/visited"))
                .andExpect(status().isOk());

    }
}