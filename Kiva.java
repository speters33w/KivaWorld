import edu.duke.Point;

import java.util.Random;

/**
 * The Kiva class allows a Kiva robot object to navigate a map to pick up and drop pods.
 *
 * @author Stephan Peters (peterstz)
 * @version 20220511
 *
 * @see KivaCommand
 * @see FloorMap
 */
public class Kiva {
    // Create and initialize class variables
    private Point currentLocation;// = new Point(-1, -1); //initializes currentLocation to (-1,-1)
    private FacingDirection directionFacing = FacingDirection.UP; //initializes initial facing direction to up
    private FloorMap map = new KivaCreateMap().defaultMap(); //initializes a default map
    private boolean carryingPod = false; //initializes carryingPod
    boolean successfullyDropped = false; //initializes successfullyDropped
    //private motorLifetime;

    /**
     * Returns the current Kiva location as an edu.duke.Point object.
     *
     * Example usage:
     *
     *     Kiva kiva = new Kiva();
     *     kiva.move(KivaCommand.FORWARD);
     *     System.out.println("The Kiva is at location ("
     *         + kiva.getCurrentLocation().getX() + ", "
     *         + kiva.getCurrentLocation().getY() + ").");
     *
     * @return edu.duke Point
     *
     * @see edu.duke.Point
     */
    Point getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Returns the current Kiva facing direction, UP, DOWN, LEFT, RIGHT.
     *
     * Example usage:
     *
     *     Kiva kiva = new Kiva();
     *     kiva.move(KivaCommand.TURN_LEFT);
     *     System.out.println("The Kiva is facing " + kiva.getDirectionFacing());
     *
     * @return KivaWorld FacingDirection: UP, RIGHT, DOWN,
     *
     * @see FacingDirection
     */
    FacingDirection getDirectionFacing(){
        return directionFacing;
    }

    public boolean isCarryingPod(){
        return carryingPod;
    }

    public boolean isSuccessfullyDropped(){
        return successfullyDropped;
    }

    public void setDirectionFacing(String facingDirection){
        switch(facingDirection.toUpperCase()) {
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

    /**
     *  Creates Kiva using a provided map
     *
     * @param map KivaWorld FloorMap
     *
     * @see FloorMap
     */
    public Kiva(FloorMap map) {
        new Kiva(map, getCurrentLocation());
    }

    /**
     * Creates Kiva using a user provided map and a defined Kiva location.
     *
     * @param map KivaWorld FloorMap
     * @param currentLocation edu.duke Point
     *
     * @see edu.duke.Point
     * @see FloorMap
     */
    public Kiva(FloorMap map, Point currentLocation) {
    }

    /**
     * Creates Kiva using a default floor map from KivaCreateMap
     *
     * @see KivaCreateMap
     */
    public Kiva() {
        currentLocation = new Point(2,4);
        new Kiva(map,currentLocation);
    }

    /**
     * Moves the Kiva FORWARD, turn RIGHT, turn LEFT, TAKE a pod, DROP a pod
     *
     * Example usage:
     *     Kiva kiva = new Kiva();
     *     kiva.move(KivaCommand.TURN_LEFT);
     *     kiva.move(KivaCommand.FORWARD);
     *     kiva.move(KivaCommand.TAKE);
     *
     * @param command KivaCommand command
     *
     * @see KivaCommand
     */
    public void move(KivaCommand command){
        switch(command) {
            case FORWARD:
                moveForward();
                break;
            case TURN_LEFT:
                turnWiddershins();
                break;
            case TURN_RIGHT:
                turnSunwise();
                break;
            case TAKE: //todo only take at pod location in move()
                carryingPod = true;
                successfullyDropped = false; //in case a Kiva drops the pod and picks it up again right away.
                break;
            case DROP: //todo only drop at drop zone location in move()
                carryingPod = false;
                successfullyDropped = true;
                break;
        }
    }

    /**
     * Moves the Kiva forward in the direction it is facing.
     * This is a helper method for Kiva.move().
     */
    private void moveForward(){ //todo moveForward is not consistently moving current location on an object created in BlueJ with the default map
        int x = currentLocation.getX() + directionFacing.getDelta().getX();
        int y = currentLocation.getY() + directionFacing.getDelta().getY();
        currentLocation = new Point(x,y);
    }

    /**
     * Turns the Kiva counter-clockwise (left).
     * TurnWiddershins is a helper method for Kiva.move().
     */
    private void turnWiddershins(){
        switch(directionFacing){
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
    }

    /**
     * Turns the Kiva clockwise (right).
     * TurnSunwise is a helper method for Kiva.move().
     */
    private void turnSunwise(){
        switch(directionFacing){
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
    }
}