package com.natwest.remote.probecontroller.dao;

public enum Direction {
    NORTH,EAST,SOUTH,WEST;

    public Direction turnLeft() {
        System.out.println(ordinal());
        System.out.println("new ordinal "+(ordinal() + 3) % 4);
        return values()[(ordinal() + 3) % 4];
    }

    public Direction turnRight() {
        System.out.println(ordinal());
        return values()[(ordinal() + 1) % 4];
    }

}
