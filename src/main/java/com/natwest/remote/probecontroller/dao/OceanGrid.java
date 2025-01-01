package com.natwest.remote.probecontroller.dao;

import java.util.Set;

public class OceanGrid {

    private int length;
    private int breadth;
    private Set<Point> obstacles;

    public OceanGrid(int length, int breadth, Set<Point> obstacles) {
        this.length = length;
        this.breadth = breadth;
        this.obstacles = obstacles;
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getBreadth() {
        return breadth;
    }

    public void setBreadth(int breadth) {
        this.breadth = breadth;
    }

    public Set<Point> getObstacles() {
        return obstacles;
    }

    public void setObstacles(Set<Point> obstacles) {
        this.obstacles = obstacles;
    }
}
