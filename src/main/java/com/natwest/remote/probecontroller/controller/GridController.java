package com.natwest.remote.probecontroller.controller;


import com.natwest.remote.probecontroller.dao.OceanGrid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grid")
public class GridController {

    @PostMapping("/initialize")
    public ResponseEntity<String> initialize(@RequestBody OceanGrid grid){
        return new ResponseEntity("Ocean Grid Initialized", HttpStatus.OK);
    }


}
