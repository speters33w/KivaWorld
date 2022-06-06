package com.baeldung.algorithms.maze.solver;

/**
 * A point representing a location in (x,y) coordinate space, specified in integer precision.
 *
 * Extends edu.duke.point for integration into the ATA KivaWorld project.
 * Can be modified to extend java.awt.Point for other projects, returns integer for getX() and getY().
 *
 * @see edu.duke.Point
 * @see java.awt.Point
 */
public class Coordinate extends edu.duke.Point {
    int x;
    int y;
    Coordinate parent;

    public Coordinate(int x, int y) {
        super(x,y);
        this.x = x;
        this.y = y;
        this.parent = null;
    }

    public Coordinate(int x, int y, Coordinate parent) {
        super(x,y);
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    Coordinate getParent() {
        return parent;
    }
}
