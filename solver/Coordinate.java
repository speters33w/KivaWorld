package solver;

/**
 * A Coordinate representing a location in (x,y) coordinate space, specified in integer precision.
 *
 * @version  20220608.2330
 */
public class Coordinate { //extends edu.duke.Point {
    int x;
    int y;
    Coordinate parent;

    /**
     * Constructs and initializes a Coordinate at the specified (x,y) location in the coordinate space.
     * @param x – the X Coordinate of the newly constructed Coordinate
     * @param y – the Y Coordinate of the newly constructed Coordinate
     */
    public Coordinate(int x, int y) {
        //super(x,y);
        this.x = x;
        this.y = y;
        this.parent = null;
    }
    /**
     * Constructs and initializes a Coordinate at the specified (x,y) location in the coordinate space.
     * @param x – the X Coordinate of the newly constructed Coordinate
     * @param y – the Y Coordinate of the newly constructed Coordinate
     * @param parent - the parent Coordinate
     */
    public Coordinate(int x, int y, Coordinate parent) {
        //super(x,y);
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    /**
     * Returns the X Coordinate of this Coordinate.
     * @return the X Coordinate of this Coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the Y Coordinate of this Coordinate.
     * @return the Y Coordinate of this Coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the parent node, i.e. from where a path was added to the queue.
     * Used in maze solving.
     * @return Coordinate parent node, i.e. from where a path was added to the queue.
     */
    Coordinate getParent() {
        return parent;
    }

    /**
     * Sets the location of the Coordinate to the specified location.
     * @param p a Coordinate, the new location for this Coordinate.
     */
    public void setLocation(Coordinate p) {
        setLocation(p.x, p.y);
    }

    /**
     * Changes the Coordinate to have the specified location.
     * @param x an integer, the new x location for the Coordinate.
     * @param y an integer, the new x location for the Coordinate.
     */
    public void setLocation(int x, int y) {
        move(x, y);
    }

    /**
     * Changes the x Coordinate to the specified location along the x plane.
     * @param x an integer, the new x location for the Coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Changes the y Coordinate to have the specified location along the y plane.
     * @param y an integer, the new y location for the Coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Moves this Coordinate to the specified location in the
     * {@code (x,y)} coordinate plane.
     */
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Translates this Coordinate, at location {@code (x,y)},
     * by {@code dx} along the {@code x} axis and {@code dy}
     * along the {@code y} axis so that it now represents the Coordinate
     * {@code (x+dx,y+dy)}.
     *
     * @param       dx   the distance to move this Coordinate
     *                            along the X axis
     * @param       dy    the distance to move this Coordinate
     *                            along the Y axis
     */
    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     *  Translates this Coordinate, at location {@code (x,y)},
     *  by {@code delta(x,y)} along both axes so that it now represents the Coordinate
     *      * {@code (x+delta.getX(),y+delta.getY()}.
     *      *
     * @param delta Coordinate to translate this Coordinate with.
     */
    public void translate(Coordinate delta) {
        this.x += delta.getX();
        this.y += delta.getY();
    }

    /**
     * Returns a Coordinate, moved from location {@code (x,y)},
     * by {@code xOffset} along the {@code x} axis and {@code yOffset}
     * along the {@code y} axis so that it now represents the Coordinate
     * {@code (x+xOffset,y+yOffset)}.
     *
     * @param       xOffset   the distance to move this Coordinate
     *                            along the X axis
     * @param       yOffset    the distance to move this Coordinate
     *                            along the Y axis
     * @return      A new Coordinate moved to new location
     */
    public Coordinate moveBy(int xOffset, int yOffset) {
        return new Coordinate(x + xOffset, y + yOffset);
    }

    /**
     * Determines if two Coordinates are equal. Two instances of
     * <code>Coordinate</code> are equal if the values of their
     * <code>x</code> and <code>y</code> member fields, representing
     * their position in the Coordinate space, are the same.
     * @param obj an object to be compared with this <code>Coordinate</code>
     * @return <code>true</code> if the object to be compared is
     *         an instance of <code>Coordinate</code> and has
     *         the same values; <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            Coordinate pt = (Coordinate)obj;
            return (x == pt.x) && (y == pt.y);
        }
        return super.equals(obj);
    }

    /**
     * Calculate and return the Euclidean distance from this Coordinate to another Coordinate.
     *
     * @param otherCoordinate the other Coordinate to which distance is calculated
     * @return the distance from this Coordinate to otherCoordinate
     */
    public double distance (Coordinate otherCoordinate) {
        int dx = x - otherCoordinate.getX();
        int dy = y - otherCoordinate.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returns a string representation of this Coordinate and its location in the (x,y) coordinate space.
     * @return  a string representation of this Coordinate.
     */
    public String toString(){
        return "("+x+","+y+")";
    }
}
