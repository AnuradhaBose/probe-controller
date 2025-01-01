package com.natwest.remote.probecontroller.service;

import com.natwest.remote.probecontroller.dao.Command;
import com.natwest.remote.probecontroller.dao.OceanGrid;
import com.natwest.remote.probecontroller.dao.Point;
import com.natwest.remote.probecontroller.dao.Probe;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.MissingResourceException;
import java.util.Set;

@Service
public class ProbeService {

    private OceanGrid grid;
    private Probe probe;
    private Point point;

    public void initializeGrid(OceanGrid grid) {
        if(grid.getLength() < 0 || grid.getBreadth() < 0){
            throw new IllegalArgumentException();
        }
        this.grid = grid;

    }

    public void initializeProbe(Probe probe){
        //Throw error when grid is not initialized
        if(grid == null){
            throw new IllegalStateException();
        }
        //throw error if probe indices are not in range or probe indices are greater than grid index
        if(probe.getPoint().getX() < 0 || probe.getPoint().getY() < 0
                || probe.getPoint().getX() > grid.getLength() || probe.getPoint().getY() > grid.getBreadth()){
            throw new IllegalArgumentException();
        }
        this.probe = probe;
    }

    public Point move(Command command){
        Point nextPosition = calculateNextPosition(command);
        System.out.println("next "+nextPosition);
        // throw error if next position is not within grid or has obstacle
        if(!isWithinBoundary(nextPosition) || isObstacle(nextPosition)){
            throw new IllegalArgumentException();
        }
        probe.setPoint(nextPosition);
        probe.getVisited().add(nextPosition);
        return nextPosition;
    }

    private Point calculateNextPosition(Command command){
        int dx = 0, dy = 0;

        switch (command) {
            case FORWARD -> {
                switch (probe.getDirection()) {
                    case NORTH -> dy = 1;
                    case SOUTH -> dy = -1;
                    case EAST  -> dx = 1;
                    case WEST  -> dx = -1;
                }
            }
            case BACKWARD -> {
                switch (probe.getDirection()) {
                    case NORTH -> dy = -1;
                    case SOUTH -> dy = 1;
                    case EAST  -> dx = -1;
                    case WEST  -> dx = 1;
                }
            }
            case LEFT -> probe.setDirection(probe.getDirection().turnLeft());
            case RIGHT -> probe.setDirection(probe.getDirection().turnRight());
        }

        // Calculate the new position only for FORWARD and BACKWARD. Change directions for left and right commands
        return new Point(probe.getPoint().getX() + dx, probe.getPoint().getY() + dy);
    }

    private boolean isWithinBoundary(Point point){
        return point.getX() >= 0 && point.getY() >= 0 &&
                point.getX() < grid.getLength() && point.getY() < grid.getBreadth();
    }

    private boolean isObstacle(Point point){
        return grid.getObstacles().contains(point);
    }

    public Set<Point> getVisitedCoordinates() {
        return Collections.unmodifiableSet(probe.getVisited());
    }

}
