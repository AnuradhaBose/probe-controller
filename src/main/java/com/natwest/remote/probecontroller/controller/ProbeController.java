package com.natwest.remote.probecontroller.controller;

import com.natwest.remote.probecontroller.dao.Command;
import com.natwest.remote.probecontroller.dao.Point;
import com.natwest.remote.probecontroller.dao.Probe;
import com.natwest.remote.probecontroller.service.ProbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/probe")
public class ProbeController {

    @Autowired
    private ProbeService probeService;

    @PostMapping("/initialize")
    public ResponseEntity<String> initializeProbe(@RequestBody Probe probe){
        try {
            probeService.initializeProbe(probe);
            return new ResponseEntity<>("Probe Initialized", HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>("Probe Initialization Failed. Invalid Probe Points",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/move")
    public ResponseEntity<Point> executeCommands(@RequestBody List<Command> commands){
        return new ResponseEntity<>(new Point(1,1),HttpStatus.OK);
    }

    @GetMapping("/visited")
    public ResponseEntity<Set<Point>> getVisitedCoordinates() {
        return new ResponseEntity<>(new HashSet<>(),HttpStatus.OK);
    }
}


