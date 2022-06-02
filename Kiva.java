import edu.duke.Point;
import java.util.Objects;


/**
 * The Kiva class allows a Kiva robot object to navigate a map to pick up and drop pods.
 *
 * @author Stephan Peters (peterstz)
 * @version 20220601.2200
 * @see KivaCommand
 * @see FloorMap
 */
public class Kiva {
    boolean debugging = true;

    // Create and initialize class variables
    private Point currentLocation;// = new Point(-1, -1); //initializes currentLocation to (-1,-1)
    private FacingDirection directionFacing = FacingDirection.UP; //initializes initial facing direction to up
    private FloorMap map = new KivaCreateMap().defaultMap(); //initializes a default map
    boolean carryingPod = false; //initializes carryingPod
    boolean successfullyDropped = false; //initializes successfullyDropped
    private long motorLifetime = 0L; //motor lifetime in milliseconds. Max lifetime is 4320000000000 ms (20,000 Hours)
    private double motorLifetimeHours;
    /**
     * Creates Kiva using a provided map
     *
     * @param map KivaWorld FloorMap
     * @see FloorMap
     */
    public Kiva(FloorMap map) {
        this.map = map;
        this.currentLocation = map.getInitialKivaLocation();
        new Kiva(map, currentLocation);
    }

    /**
     * Creates Kiva using a user provided map and a defined Kiva location.
     *
     * @param map KivaWorld FloorMap
     * @param currentLocation edu.duke Point
     * @see edu.duke.Point
     * @see FloorMap
     */
    public Kiva(FloorMap map, Point currentLocation) {
        this.map = map;
        this.currentLocation = currentLocation;
        if (debugging) {
            System.out.println(map.toString());
            System.out.println("Current location: " + currentLocation + ".");
        }

    }

    /**
     * Creates Kiva using a default floor map from KivaCreateMap
     *
     * @see KivaCreateMap
     */
    public Kiva() {
        this.currentLocation = new Point(2, 4);
        new Kiva(map, currentLocation); // map is already defined as default map in field
    }

    /**
     * Returns the current Kiva location as an edu.duke.Point object.
     * <p>
     * Example usage:
     * <pre>
     *     Kiva kiva = new Kiva();
     *     kiva.move(KivaCommand.FORWARD);
     *     System.out.println("The Kiva is at location ("
     *         + kiva.getCurrentLocation().getX() + ", "
     *         + kiva.getCurrentLocation().getY() + ").");
     * </pre>
     *
     * @return edu.duke Point
     * @see edu.duke.Point
     */
    Point getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Returns the current Kiva facing direction, UP, DOWN, LEFT, RIGHT.
     * <p>
     * Example usage:
     * <pre>
     *     Kiva kiva = new Kiva();
     *     kiva.move(KivaCommand.TURN_LEFT);
     *     System.out.println("The Kiva is facing " + kiva.getDirectionFacing());
     * </pre>
     *
     * @return KivaWorld FacingDirection: UP, RIGHT, DOWN,
     * @see FacingDirection
     */
    FacingDirection getDirectionFacing() {
        return directionFacing;
    }

    public long getMotorLifetime(){
        return motorLifetime;
    }

    public boolean isCarryingPod() {
        return carryingPod;
    }

    public boolean isSuccessfullyDropped() {
        return successfullyDropped;
    }

    public void setDirectionFacing(String facingDirection) {
        switch (facingDirection.toUpperCase()) {
            case "UP":
            case "": //default is UP
                directionFacing = FacingDirection.UP;
                break;
            case "LEFT":
                directionFacing = FacingDirection.LEFT;
                break;
            case "DOWN":
                directionFacing = FacingDirection.DOWN;
                break;
            case "RIGHT":
                directionFacing = FacingDirection.RIGHT;
                break;
        }

    }

    public void setMotorLifetime(long motorLifetime){
        this.motorLifetime = motorLifetime;
    }

    private void incrementMotorLifetime(){
        motorLifetime = motorLifetime+1000L;
    }

    /**
     * Moves the Kiva FORWARD, turn RIGHT, turn LEFT, TAKE a pod, DROP a pod
     * <p>
     * Example usage:
     * <pre>
     *     Kiva kiva = new Kiva();
     *     kiva.move(KivaCommand.TURN_LEFT);
     *     kiva.move(KivaCommand.FORWARD);
     *     kiva.move(KivaCommand.TAKE);
     * </pre>
     *
     * @param command KivaCommand command
     * @see KivaCommand
     */
    public void move(KivaCommand command) {
        switch (command) {
            case FORWARD:
                moveForward();
                incrementMotorLifetime();
                break;
            case TURN_LEFT:
                turnWiddershins();
                incrementMotorLifetime();
                break;
            case TURN_RIGHT:
                turnSunwise();
                incrementMotorLifetime();
                break;
            case TAKE:
                if (map.getObjectAtLocation(currentLocation).equals(FloorMapObject.POD)) {
                    carryingPod = true;
                } else {
                    throw new NoPodException("The kiva attempted to TAKE a POD while there is no POD at location "
                            + currentLocation + ".");
                }
                break;
            case DROP:
                if (carryingPod && map.getObjectAtLocation(currentLocation).equals(FloorMapObject.DROP_ZONE)) {
                    carryingPod = false;
                    successfullyDropped = true;
                } else if (carryingPod) {
                    throw new IllegalDropZoneException("The kiva attempted to DROP not on a DROP_ZONE at location "
                            + currentLocation + ".");
                } else {
                    throw new NoPodException("The kiva attempted to DROP at a DROP_ZONE while not carrying a POD at location "
                            + currentLocation + ".");
                }
                break;
        }
    }

    /**
     * Moves the Kiva forward in the direction it is facing.
     * This is a helper method for Kiva.move().
     */
    private void moveForward() {

        if (debugging) {
            System.out.println("Current location before moving: " + currentLocation + ".");
        }
        int x = currentLocation.getX() + directionFacing.getDelta().getX();
        int y = currentLocation.getY() + directionFacing.getDelta().getY();
        currentLocation = new Point(x, y);
        checkForExceptions();
        if (debugging) {
            System.out.println("Current location after moving: " + currentLocation + ".");
        }
    }

    /**
     * Turns the Kiva counter-clockwise (left).
     * TurnWiddershins is a helper method for Kiva.move().
     */
    private void turnWiddershins() {
        if (debugging) {
            System.out.println("Facing location before turning: " + directionFacing + ".");
        }
        switch (directionFacing) {
            case UP:
                directionFacing = FacingDirection.LEFT;
                break;
            case LEFT:
                directionFacing = FacingDirection.DOWN;
                break;
            case DOWN:
                directionFacing = FacingDirection.RIGHT;
                break;
            case RIGHT:
                directionFacing = FacingDirection.UP;
                break;
        }
        if (debugging) {
            System.out.println("Facing location after turning: " + directionFacing + ".");
        }
    }

    /**
     * Turns the Kiva clockwise (right).
     * TurnSunwise is a helper method for Kiva.move().
     */
    private void turnSunwise() {
        if (debugging) {
            System.out.println("Facing location before turning: " + directionFacing + ".");
        }
        switch (directionFacing) {
            case UP:
                directionFacing = FacingDirection.RIGHT;
                break;
            case RIGHT:
                directionFacing = FacingDirection.DOWN;
                break;
            case DOWN:
                directionFacing = FacingDirection.LEFT;
                break;
            case LEFT:
                directionFacing = FacingDirection.UP;
                break;
        }
        if (debugging) {
            System.out.println("Facing location after turning: " + directionFacing + ".");
        }
    }

    /**
     * Checks for invalid moves, collisions, etc. and throws appropriate exceptions.
     *
     * @see FloorMap
     * @see FloorMapObject
     * @see java.lang.Exception
     */
    private void checkForExceptions() {
        if (currentLocation.getX() < map.getMinColNum() || currentLocation.getX() > map.getMaxColNum()
                || currentLocation.getY() < map.getMinRowNum() || currentLocation.getY() > map.getMaxRowNum()) {
            throw new IllegalMoveException("The Kiva left the defined FloorMap to location "
                    + currentLocation + ".");
        }
        if (map.getObjectAtLocation(currentLocation).equals(FloorMapObject.OBSTACLE)) {
            throw new IllegalMoveException("The kiva ran into an obstacle at location "
                    + currentLocation + ".");
        }
        if (carryingPod) {
            if (map.getObjectAtLocation(currentLocation).equals(FloorMapObject.POD)) {
                throw new IllegalMoveException("The kiva ran into a POD while carrying another POD at location "
                        + currentLocation + ".");
            }
        }
    }

    public void tempExceptionMessageTester() {
        FloorMapObject terrain = map.getObjectAtLocation(currentLocation);
        if (terrain != FloorMapObject.POD) {
            throw new NoPodException(String.format(
                    "Can't TAKE: Location %s is %s, not POD!", currentLocation, terrain));
        }
    }

}