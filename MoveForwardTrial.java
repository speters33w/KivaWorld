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
}
