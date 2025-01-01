package com.natwest.remote.probecontroller.service;

import com.natwest.remote.probecontroller.dao.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProbeServiceTest {

    ProbeService service;
    OceanGrid grid;
    Probe probe;

    @BeforeEach
    void setUp(){
        service = new ProbeService();
        grid = new OceanGrid(10,10, Set.of(new Point(2,2),new Point(3,3)));
        probe = new Probe(new Point(0,0),Direction.NORTH,new HashSet<>());
        probe.setPoint(new Point(0, 0));
        probe.setDirection(Direction.NORTH);
        service.initializeGrid(grid);
        service.initializeProbe(probe);
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
        ProbeService s = new ProbeService();
        Point p = new Point(1,1);
        Probe probe = new Probe(p, Direction.NORTH,null);
        assertThrows(IllegalStateException.class,()->s.initializeProbe(probe));
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
    void moveforward_success() {
        Point p = service.move(Command.FORWARD);
        System.out.println(p);
        // added hascode and equals for this
        assertEquals(new Point(0, 1), probe.getPoint());
    }

    @Test
    void move_changeDirection_outOfGrid_shouldThrowIllegalArgument(){
        service.move(Command.LEFT);
        assertThrows(IllegalArgumentException.class,()->service.move(Command.FORWARD));
    }

    @Test
    void move_changeDirection_success(){
        Probe probe = new Probe(new Point(0,0),Direction.NORTH,new HashSet<>());
        this.probe = probe;
        service.initializeProbe(probe);
        service.move(Command.FORWARD);
        service.move(Command.RIGHT);//should face east
        service.move(Command.FORWARD);
        assertEquals(new Point(1,1),probe.getPoint());
    }
    @Test
    void move_shouldNotMoveIntoObstacle() {

        probe.setPoint(new Point(1, 2));
        probe.setDirection(Direction.EAST);
        assertThrows(IllegalArgumentException.class,()->service.move(Command.FORWARD));//Obstacle at (2, 2)
        assertEquals(new Point(1, 2), probe.getPoint());
    }

    @Test
    void getVisitedCoordinates_success(){
        Probe probe = new Probe(new Point(0,0),Direction.NORTH,new HashSet<>());
        this.probe = probe;
        service.initializeProbe(probe);
        service.move(Command.FORWARD);
        service.move(Command.RIGHT);//should face east
        service.move(Command.FORWARD);
        assertEquals(2,probe.getVisited().size());
    }
}