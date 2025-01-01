package com.natwest.remote.probecontroller.service;

import com.natwest.remote.probecontroller.dao.Direction;
import com.natwest.remote.probecontroller.dao.OceanGrid;
import com.natwest.remote.probecontroller.dao.Point;
import com.natwest.remote.probecontroller.dao.Probe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class ProbeServiceTest {

    ProbeService service;
    OceanGrid grid;
    Probe probe;

    @BeforeEach
    void setUp(){
        service = new ProbeService();
        grid = new OceanGrid(10,10,null);
    }

    @Test
    void initializeGrid_valid() {
        OceanGrid grid = new OceanGrid(5,5,null);
        service.initializeGrid(grid);
        assertEquals(5,grid.getLength());
        assertEquals(5,grid.getBreadth());
    }

    @Test
    void initializeGrid_invalidIndex(){
        OceanGrid grid = new OceanGrid(-1, 8, null);
        assertThrows(IllegalArgumentException.class, ()->service.initializeGrid(grid));
    }

    @Test
    void initializeProbe_success() {
        OceanGrid grid = new OceanGrid(10,10,null);
        this.grid = grid;
        service.initializeGrid(grid);
        Point p = new Point(1,1);
        Probe probe = new Probe(p, Direction.NORTH,null);
        service.initializeProbe(probe);
        assertEquals(1,probe.getPoint().getX());
    }

    @Test
    void initializeProbe_gridNotInitialized_failure() {
        Point p = new Point(1,1);
        Probe probe = new Probe(p, Direction.NORTH,null);
        assertThrows(IllegalStateException.class,()->service.initializeProbe(probe));
        assertEquals(1,probe.getPoint().getX());
    }

    @Test
    void initializeProbe_invalidProbeIndex_failure(){
        OceanGrid grid = new OceanGrid(10,10,null);
        this.grid = grid;
        service.initializeGrid(grid);
        Point p = new Point(-1,-1);
        assertThrows(IllegalArgumentException.class, ()-> service.initializeProbe(new Probe(p,Direction.NORTH,null)));
    }

    @Test
    void move() {
    }
}