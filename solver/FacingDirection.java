//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package solver;
import edu.duke.Point;

/**
 * Enumeration describing which way a robot is facing, containing the delta required to transform the location in that direction when moving forward.
 * It provides more understandable names (e.g. better than (1,0)) for the directions and restricts the robot to the 4 valid orientations.
 *
 * The included Point, called delta, can be added to the robot's current location to move one space in the indicated direction.
 *
 * The Point mathematically indicates a direction on a single axis (the other dimension will always be zero)
 */
public enum FacingDirection {
    UP(new Point(0, -1)),
    RIGHT(new Point(1, 0)),
    DOWN(new Point(0, 1)),
    LEFT(new Point(-1, 0));

    private Point delta;

    private FacingDirection(Point delta) {
        this.delta = delta;
    }

    /**
     * public edu.duke.Point getDelta()
     *
     * return the current facing direction as a Point.
     *
     * @return Point representation of the facing direction.
     */
    public Point getDelta() {
        return this.delta;
    }
}
