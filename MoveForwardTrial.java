import edu.duke.Point;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
//requires KivaWorld.FacingDirection in default package;

public class MoveForwardTrial {
    // Create location and facing direction objects
    private Point currentLocation;
    private FacingDirection directionFacing;

    // Runs this trial as main
    public static void main(String[] args) {
        // Create MoveForwardTrial object
        MoveForwardTrial trial = new MoveForwardTrial();
        // Generate random start Point() and make it currentLocation for trial
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        trial.currentLocation = new Point(x,y);
        // Move around, repeating until user enters Q
        while(true){
            // Create Scanner object for user input.toUpperCase and print currentLocation
            Scanner scanner = new Scanner(System.in);
            System.out.println("trial.currentLocation = new Point(" + trial.currentLocation.getX() + ", "
                    + trial.currentLocation.getY() + ");");
            System.out.println("Type a direction, U D L R (Q quits):");
            String input = (scanner.nextLine().toUpperCase());
            // If user types Q
            if (input.equals("Q")) {
                scanner.close();
                break;
            }
            // Sets directionFacing based on user input,  ignores invalid input with Pattern.matches();
            if (Pattern.matches("[UDLR]+",input )){
                switch (input) {
                    case "U" :
                        trial.directionFacing = FacingDirection.UP;
                        break;
                    case "D" :
                        trial.directionFacing = FacingDirection.DOWN;
                        break;
                    case "L" :
                        trial.directionFacing = FacingDirection.LEFT;
                        break;
                    case "R" :
                        trial.directionFacing = FacingDirection.RIGHT;
                        break;
                }
                // Sets trial.currentLocation to new location after moving forward in facing direction
                x = trial.currentLocation.getX() + trial.directionFacing.getDelta().getX();
                y = trial.currentLocation.getY() + trial.directionFacing.getDelta().getY();
                trial.currentLocation = new Point(x,y);

            }
        }
    }
    /* MOVED FROM KivaTest for temporary storage
        /**
     * testForward creates a Kiva object using the default map.
     * testForward allows the user to turn the Kiva (unless it is facing the correct direction),
     * then moves the Kiva one square in that direction.
     * testForward then verifies the facing direction and location are correct using verifyKivaState()
     *
     * @see KivaCommand
     * @see FacingDirection
     * @see edu.duke.Point
     *
     *
    public void testForwardInteractive(){
        // GIVEN
        // A Kiva built with the default map we defined earlier
        FacingDirection expectDirection = FacingDirection.UP;
        int x = kiva.getCurrentLocation().getX();
        int y = kiva.getCurrentLocation().getY();
        System.out.println("Kiva Initial Location Expected: (" + x + "," + y + ") Actual: " + kiva.getCurrentLocation());
        while(true) {
            System.out.println("The Kiva is facing " + kiva.getDirectionFacing() + ".");
            System.out.println("Type a turning direction, L R, F (FORWARD without turning) (Q Quits)");
            String testInput = keyboardResource.getLine().toUpperCase(); //todo grab first index of the string in testForward so user can type Left, Forward, etc.
            if (testInput.equals("Q")) {
                break;
            }
            if (Pattern.matches("[LRF]+", testInput)) {
                switch (testInput) {
                    case "L":
                        System.out.println("The Kiva is turning LEFT");
                        kiva.move(KivaCommand.TURN_LEFT);
                        break;
                    case "R":
                        System.out.println("The Kiva is turning RIGHT");
                        kiva.move(KivaCommand.TURN_RIGHT);
                        break;
                    case "F":
                        break;
                }
            } else {
                System.out.print("Invalid facing direction input. ");
            }
            System.out.println("The Kiva is facing " + kiva.getDirectionFacing());

            //WHEN
            //We move one space forward
            System.out.println("The Kiva moves FORWARD");
            kiva.move(KivaCommand.FORWARD);
            System.out.println("currentLocation: (" + x + "," + y + ")");
            //THEN
            //The Kiva has moved one space in the facing direction
            switch (kiva.getDirectionFacing()) {//Modify x or y for expected location.
                // This is NOT a good test for facing direction, that should be done elsewhere.
                case UP:
                    y--;
                    break;
                case DOWN:
                    y++;
                    expectDirection = FacingDirection.DOWN;
                    break;
                case LEFT:
                    x--;
                    expectDirection = FacingDirection.LEFT;
                    break;
                case RIGHT:
                    x++;
                    expectDirection = FacingDirection.RIGHT;
                    break;
            }

            if (testMode) {
                verifyKivaState(testString, kiva, new Point(x, y), expectDirection, expectCarry, expectDropped);
            }
        }
    }
     */
}
