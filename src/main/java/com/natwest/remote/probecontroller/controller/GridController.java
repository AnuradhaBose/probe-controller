package com.natwest.remote.probecontroller.controller;


import com.natwest.remote.probecontroller.dao.OceanGrid;
import com.natwest.remote.probecontroller.service.ProbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grid")
public class GridController {

    @Autowired
    private ProbeService service;

    @PostMapping("/initialize")
    public ResponseEntity<String> initialize(@RequestBody OceanGrid grid){
        try {
            service.initializeGrid(grid);
            return new ResponseEntity<>("Ocean Grid Initialized",HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>("Invalid Grid Index", HttpStatus.BAD_REQUEST);
        }
    }


}
