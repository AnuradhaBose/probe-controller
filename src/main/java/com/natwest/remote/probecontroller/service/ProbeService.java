package com.natwest.remote.probecontroller.service;

import com.natwest.remote.probecontroller.dao.OceanGrid;
import com.natwest.remote.probecontroller.dao.Probe;
import org.springframework.stereotype.Service;

@Service
public class ProbeService {

    private OceanGrid grid;
    private Probe probe;

    public void initializeGrid(OceanGrid grid) {
        if(grid.getLength()<0 || grid.getBreadth()<0){
            throw new IllegalArgumentException();
        }
        this.grid=grid;

    }

    public void initializeProbe(Probe probe){
        if(probe.getPoint().getX()<0 || probe.getPoint().getY()<0){
            throw new IllegalArgumentException();
        }
        this.probe = probe;
    }

}
