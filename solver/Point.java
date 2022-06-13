package solver;

import java.io.Serializable;


//This Point method works, but requires thorough testing.

/**
 * <p>
 * A Cartesian Coordinate point in integer resolution represented by a pair of numeric coordinates {@code (x,y)}.
 * The Point object can contain a separate reference point, parent.
 *</p><p>
 * A Cartesian coordinate system in two dimensions is defined by an ordered pair of perpendicular lines (axes),
 * a single unit of length for both axes, and an orientation for each axis.
 * The point where the axes meet is taken as the origin for both, thus turning each axis into a number line.
 * For any point {@code P}, a line is drawn through {@code P} perpendicular to each axis,
 * and the position where it meets the axis is interpreted as a number.
 * The two numbers, in that chosen order, are the Cartesian coordinates of {@code P}.
 * The reverse construction allows one to determine the point {@code P} given its coordinates.
 *</p><p>
 * The first and second coordinates are called the abscissa and the ordinate of {@code P}, respectively;
 * and the point where the axes meet is called the origin of the coordinate system.
 * The coordinates are usually written as two numbers in parentheses, in that order, separated by a comma,
 * as in {@code (3, −10)}.
 * Thus, the origin has coordinates {@code (0, 0)}, and the points on the positive half-axes,
 * one unit away from the origin, have coordinates {@code (1, 0)} and {@code (0, 1)}.
 *</p><p>
 * A Euclidean plane with a chosen Cartesian coordinate system is called a Cartesian plane.
 *</p><p>
 * If the coordinates of a point are {@code (x, y)},
 * then its distances from the x-axis and from the y-axis are {@code |y|} and {@code |x|}, respectively;
 * where {@code |n|} denotes the absolute value of a number.
 *</p>
 * @author StephanPeters (speters33w)
 * @version 20220613.1000
 */
public class Point implements Cloneable, Serializable {  //remove extends edu.duke.Point and remove super(x,y) from constructors to remove dependency.
    /**
     * The integer {@code (x)} abscissa of this Point.
     */
    int x;

    /**
     * The integer {@code (y)} ordinate of this Point.
     */
    int y;

    /**
     * A retrievable reference Point.
     */
    Point parent;

    /**
     * Constructs and initializes a Point at the specified {@code (x,y)} location in the plane.
     * @param x – the {@code x} abscissa of the newly constructed Point.
     * @param y – the {@code y} ordinate of the newly constructed Point.
     */
    public Point(int x, int y) {
        // super(x,y); //remove this line if removing extends edu.duke.Point.
        this.x = x;
        this.y = y;
        this.parent = null;
    }

    /**
     * Constructs and initializes a Point at the origin {@code (0,0)} of the plane.
     */
    public Point() {
        // super(0,0); //remove this line if removing extends edu.duke.Point.
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructs and initializes a Point at the specified {@code (x,y)} location in the plane and stores a reference coordinate (parent).
     * @param x – the {@code x} abscissa of the newly constructed Point.
     * @param y – the {@code y} ordinate of the newly constructed Point.
     * @param parent - a retrievable reference Point, designed for Breadth First Traversal search.
     */
    public Point(int x, int y, Point parent) {
        // super(x,y); //remove this line if removing extends edu.duke.Point.
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    /**
     * Returns the {@code x} abscissa of the Point as an integer.
     * @return the {@code x} abscissa of this Point.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the {@code y} ordinate of this Point.
     * @return the {@code y} ordinate of this Point.
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the reference Point parent.
     * @return reference Point parent.
     */
    Point getParent() {
        return parent;
    }

    /**
     * Sets the location of the Point to the location of another Point.
     * @param p a Point sharing new location for this Point.
     */
    public void setLocation(Point p) {
        setLocation(p.x, p.y);
    }

    /**
     * Changes the Point to have the specified location.
     * @param x an integer, the new {@code x} abscissa for the Point.
     * @param y an integer, the new {@code y} ordinate for the Point.
     */
    public void setLocation(int x, int y) {
        move(x, y);
    }

    /**
     * Changes the abscissa of the Point to a specified location along the x plane.
     * @param x an integer, the new {@code x} abscissa for the Point.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Changes the ordinate of the Point to a specified location along the y plane.
     * @param y an integer, the new {@code y} ordinate for the Point.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Moves this Point to the specified location in the
     * {@code (x,y)} coordinate plane.
     */
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Translates this Point, at location {@code (x,y)}, by {@code a} along the x-axis
     * and {@code b} along the y-axis
     * so that it now represents the Point {@code (x+a,y+b)}.
     *
     * @param a - the distance to move this Point along the x-axis.
     * @param b - the distance to move this Point along the y-axis.
     */
    public void translate(int a, int b) {
        this.x += a;
        this.y += b;
    }

    /**
     * Translates this Point, at location {@code (x,y)}, by {@code delta(x,y)}
     * along both axes so that it now represents the Point {@code (x+delta.getX(),y+delta.getY()}.
     * @param delta Point to translate this Point with.
     */
    public void translate(Point delta) {
        this.x += delta.getX();
        this.y += delta.getY();
    }

    /**
     * Reflects of the Point {@code (x,y)} across the line {@code y = x},
     * reversing the {@code (x,y)} coordinate values to {@code (y,x)}.
     */
    public void reflect(){
        int abscissa = x;
        int ordinate = y;
        this.x = ordinate;
        this.y = abscissa;
    }

    /**
     * Reflects of the Point {@code (x,y)} across the x-axis,
     * transforming the {@code (x,y)} coordinate values to {@code (x,-y)}.
     */
    public void reflectX(){
        this.y = -y;
    }

    /**
     * Reflects of the Point {@code (x,y)} across a horizontal line,
     * transforming the {@code y} coordinate across the line equidistant.
     * @param a - integer, the {@code y} location of the horizontal line.
     */
    public void reflectX(int a){
        int distanceY = Math.abs(y - a);
        if (a >= x) {
            y = y + (2 * distanceY);
        } else {
            y = y - (2 * distanceY);
        }
    }

    /**
     * Reflects of the Point {@code (x,y)} across the y-axis,
     * transforming the {@code (x,y)} coordinate values to {@code (-x,y)}.
     */
    public void reflectY(){
        this.x = -x;
    }

    /**
     * Reflects of the Point {@code (x,y)} across a vertical line,
     * transforming the {@code x} coordinate across the line equidistant.
     * @param a - integer, the {@code x} location of the vertical line.
     */
    public void reflectY(int a){
        int distanceY = Math.abs(y - a);
        if (a >= y) {
            x = x + (2 * distanceY);
        } else {
            x = x - (2 * distanceY);
        }
    }

    /**
     * Reflects of the Point {@code (x,y)} across the line {@code y = -x},
     * reversing and negating the {@code (x,y)} coordinate values to {@code (-y,-x)}.
     */
    public void reflectNegative(){
        int abscissa = -x;
        int ordinate = -y;
        this.x = ordinate;
        this.y = abscissa;
    }

    /**
     * Returns a new Point, offset from location {@code (x,y)},
     * by {@code deltaX} along the x-axis and by {@code deltaY} along the y-axis,
     * so it is at location {@code (x+deltaX,y+deltaY)}.
     *
     * @param deltaX - the distance from the Point along the x-axis
     *                where the new Point's abscissa will be.
     * @param deltaY - the distance from the Point along the y-axis
     *                where the new Point's ordinate will be.
     * @return A new Point moved to new location
     */
    public Point moveBy(int deltaX, int deltaY) {
        return new Point(x + deltaX, y + deltaY);
    }

    /**
     * Returns a new Point, offset from location {@code (x,y)},
     * by the x and y values of the delta Point provided,
     * so it is at location {@code (x + delta.getX(),y + delta.getY)}
     *
     * May be used with an enum or method of directions or edges.
     *
     * @param delta - Point with the {@code (x,y)} used for the distances to translate.
     * @return A new Point moved to new location
     */
    public Point moveBy(Point delta){
        return new Point(x + delta.x, y + delta.y);
    }

    /**
     * Create a new Point with the same values as this Point.
     * @return - a new Point with the same values as this Point
     * @see java.lang.Cloneable
     */
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    /**
     * Determines if two Points are equal. Two instances of
     * <code>Point</code> are equal if the values of their
     * {@code x}, {@code y} and {@code parent} member fields are the same.
     * @param obj - a Point to be compared with this {@code Point}.
     * @return {@code true} if the object to be compared is a{@code Point},
     * and has the same values; {@code false} otherwise.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point pt = (Point)obj;
            return (x == pt.x) && (y == pt.y);
        }
        return super.equals(obj);
    }

    /**
     * Calculate and return the Euclidean distance from this Point to another Point.
     *
     * @param q - the other Point to which distance is calculated.
     * @return - the distance from this Point to the other Point in double precision.
     */
    public double distance (Point q) {
        int dx = x - q.x;
        int dy = y - q.y;
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * Calculate and return the Euclidean distance from this Point
     * to the {@code (x,y)} values of a second coordinate.
     *
     * @param x2 - the {@code x} value of the second Point
     * @param y2 - the {@code y} value of the second Point
     * @return - the distance between this Point and the second coordinate location in double precision
     */
    public double distance (int x2, int y2) {
        int dx = x - x2;
        int dy = y - y2;
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * Calculate and return the Euclidean distance from the {@code (x,y)} values of one coordinate
     * to the {@code (x,y)} values of a second coordinate.
     *
     * @param x1 - the {@code x} value of the first Point
     * @param y1 - the {@code y} value of the first Point
     * @param x2 - the {@code x} value of the second Point
     * @param y2 - the {@code y} value of the second Point
     * @return - the distance between the two coordinate locations in double precision
     */
    public static double distance(int x1, int y1, int x2, int y2) {
        int dx = x1 - x2;
        int dy = y1 - y2;
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * Calculate and return the square of the distance from this Point to a second Point.
     *
     * @param q - the other Point to which distance is calculated.
     * @return - the square of the distance between the two Points in double precision
     */
    public double distanceSq (Point q) {
        int dx = x - q.x;
        int dy = y - q.y;
        return (dx * dx) + (dy * dy);
    }

    /**
     * Calculate and return the square of the distance from this Point
     * to the {@code (x,y)} values of a second coordinate.
     *
     * @param x2 - the {@code x} value of the second Point
     * @param y2 - the {@code y} value of the second Point
     * @return - the distance between the two coordinate locations in double precision
     */
    public double distanceSq(int x2, int y2) {
        int dx = x - x2;
        int dy = y - y2;
        return (dx * dx) + (dy * dy);
    }

    /**
     * Calculate and return the square of the distance from the {@code (x,y)} values of one coordinate
     * to the {@code (x,y)} values of a second coordinate.
     *
     * @param x1 - the {@code x} value of the first Point
     * @param y1 - the {@code y} value of the first Point
     * @param x2 - the {@code x} value of the second Point
     * @param y2 - the {@code y} value of the second Point
     * @return - the distance between the two coordinate locations in double precision
     */
    public static double distanceSq(int x1, int y1, int x2, int y2) {
        int dx = x1 - x2;
        int dy = y1 - y2;
        return (dx * dx) + (dy * dy);
    }

    /**
     * Returns a string representation of this Point and its location
     * in the {@code (x,y)} coordinate plane.
     * @return  a string representation of this Point in format {@code (x,y)}.
     */
    public String toString(){
        return "("+x+","+y+")";
    }
}
