package solver;

/**
 * Enumeration describing which way a robot is facing, containing the delta required to transform the location in that direction when moving forward.
 * It provides more understandable names (e.g. better than (1,0)) for the directions and restricts the robot to the 4 valid orientations.
 *
 * The included Coordinate, called delta, can be added to the robot's current location to move one space in the indicated direction.
 *
 * The Coordinate mathematically indicates a direction on a single axis (the other dimension will always be zero)
 */
public enum CardinalDirection {
    NORTH(new Coordinate(0, -1)),
    EAST(new Coordinate(1, 0)),
    SOUTH(new Coordinate(0, 1)),
    WEST(new Coordinate(-1, 0));

    private final Coordinate delta;

    CardinalDirection(Coordinate delta) {
        this.delta = delta;
    }

    /**
     * public Coordinate getDelta()
     *
     * return the current facing direction as a Coordinate.
     *
     * @return Coordinate representation of the facing direction.
     */
    public Coordinate getDelta() {
        return this.delta;
    }
}
