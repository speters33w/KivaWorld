package solver;

/**
 * Enumeration describing a facing direction, limiting the facing directions to up, down, left, and right.
 *
 * The included Point, called delta, can be added to another Point to move one space in the indicated direction.
 *
 * The Point mathematically indicates a direction on a single axis in the format {@code Point (x,y)} ([col][row]).
 */
public enum Direction {
    UP(new Point(0, -1)),
    RIGHT(new Point(1, 0)),
    DOWN(new Point(0, 1)),
    LEFT(new Point(-1, 0));

    private final Point delta;

    Direction(Point delta) {
        this.delta = delta;
    }

    /**
     * public Point getDelta()
     *
     * return the current facing direction as a {@code Point (x,y)}.
     *
     * @return {@code Point (x,y)} representation of the facing direction.
     */
    public Point getDelta() {
        return this.delta;
    }
}
