import edu.duke.Point;
import java.util.Random;

/**
 * Write a description of class Kiva here.
 */
public class Kiva {
    // Create and initialize class variables
    private Point currentLocation;// = new Point(-1, -1); //initializes currentLocation to (-1,-1)
    private FacingDirection directionFacing = FacingDirection.UP; //initializes initial facing direction to up
    private FloorMap map = new KivaCreateMap().defaultMap(); //initializes a default map
    private boolean carryingPod = false; //initializes carryingPod
    private boolean successfullyDropped = false; //initializes successfullyDropped
    //private motorLifetime;

    Point getCurrentLocation() { //todo finish getCurrentLocation, currently initializes to random
//        if (currentLocation.equals(new Point(-1, -1))) { //this line causes compiler problems if point is not (-1,-1)
//            Random random = new Random();
//            int x = random.nextInt(10);
//            int y = random.nextInt(10);
//            currentLocation = new Point(x, y);
//            System.out.println("Random Current Location = " + currentLocation);
//        }
        return currentLocation;
    }

    FacingDirection getDirectionFacing(){
        return directionFacing;
    }

    public Kiva(FloorMap map) {
        new Kiva(map, getCurrentLocation());
    }

    public Kiva(FloorMap map, Point currentLocation) {

    }

    public Kiva() {
        currentLocation = new Point(2,4);
        new Kiva(map,currentLocation);
    }

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
        }
        //System.out.println("todo"); //todo finish move command
    }

    private void moveForward(){ //todo moveForward is not moving current location on an object
        int x = currentLocation.getX() + directionFacing.getDelta().getX();
        int y = currentLocation.getY() + directionFacing.getDelta().getY();
        this.currentLocation = new Point(x,y);
        System.out.println("currentLocation: (" + currentLocation.getX() + ", " + currentLocation.getY() + ")");
    }

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

    public boolean isCarryingPod(){
        return carryingPod;
    }

    public boolean isSuccessfullyDropped(){
        return successfullyDropped;
    }
}