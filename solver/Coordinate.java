package solver;

/**
 * A point representing a location in (x,y) coordinate space, specified in integer precision.
 *
 *  returns integer for getX() and getY().
 */
public class Coordinate { //extends edu.duke.Point {
    int x;
    int y;
    Coordinate parent;

    /**
     * Constructs and initializes a point at the specified (x,y) location in the coordinate space.
     * @param x – the X coordinate of the newly constructed Coordinate
     * @param y – the Y coordinate of the newly constructed Coordinate
     */
    public Coordinate(int x, int y) {
        //super(x,y);
        this.x = x;
        this.y = y;
        this.parent = null;
    }
    /**
     * Constructs and initializes a point at the specified (x,y) location in the coordinate space.
     * @param x – the X coordinate of the newly constructed Coordinate
     * @param y – the Y coordinate of the newly constructed Coordinate
     * @param parent - the parent Coordinate
     */
    public Coordinate(int x, int y, Coordinate parent) {
        //super(x,y);
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    /**
     * Returns the X coordinate of this Coordinate.
     * @return the X coordinate of this Coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the Y coordinate of this Coordinate.
     * @return the Y coordinate of this Coordinate.
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
     * Sets the location of the point to the specified location.
     * @param p a point, the new location for this point.
     */
    public void setLocation(Coordinate p) {
        setLocation(p.x, p.y);
    }

    /**
     * Changes the point to have the specified location.
     */
    public void setLocation(int x, int y) {
        move(x, y);
    }

    /**
     * Moves this point to the specified location in the
     * {@code (x,y)} coordinate plane.
     */
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Translates this point, at location {@code (x,y)},
     * by {@code dx} along the {@code x} axis and {@code dy}
     * along the {@code y} axis so that it now represents the point
     * {@code (x+dx,y+dy)}.
     *
     * @param       dx   the distance to move this point
     *                            along the X axis
     * @param       dy    the distance to move this point
     *                            along the Y axis
     */
    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Determines whether or not two points are equal. Two instances of
     * <code>Coordinate2D</code> are equal if the values of their
     * <code>x</code> and <code>y</code> member fields, representing
     * their position in the coordinate space, are the same.
     * @param obj an object to be compared with this <code>Coordinate2D</code>
     * @return <code>true</code> if the object to be compared is
     *         an instance of <code>Coordinate2D</code> and has
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
     * Calculate and return the Euclidean distance from this point to another point.
     *
     * @param otherCoordinate the other point to which distance is calculated
     * @return the distance from this point to otherCoordinate
     */
    public double distance (Coordinate otherCoordinate) {
        int dx = x - otherCoordinate.getX();
        int dy = y - otherCoordinate.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returns a string representation of this point and its location in the (x,y) coordinate space.
     * This method is intended to be used only for debugging purposes.
     * The content and format of the returned string may vary between implementations.
     * The returned string may be empty but may not be null.
     * @return  a string representation of this point.
     */
    public String toString(){
        return "("+x+","+y+")";
    }
}
