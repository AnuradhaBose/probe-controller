package com.natwest.remote.probecontroller.dao;

import java.util.Set;

public class Probe {
    private Point point;
    private Direction direction;
    private Set<Point> visited;

    public Probe(Point point, Direction direction, Set<Point> visited) {
        this.point = point;
        this.direction = direction;
        this.visited = visited;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Set<Point> getVisited() {
        return visited;
    }

    public void setVisited(Set<Point> visited) {
        this.visited = visited;
    }
}
