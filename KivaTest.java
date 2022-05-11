import edu.duke.Point;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * The test class KivaTest.
 */
public class KivaTest {
    KeyboardResource keyboardResource = new KeyboardResource();
    FloorMap defaultMap = new KivaCreateMap().defaultMap(); //todo debug null pointer exception where defaultMap is used

    public void testSingleArgumentConstructor() {
        // GIVEN
        // The default map we defined earlier

        // WHEN
        // We create a Kiva with the single argument constructor
        Kiva kiva = new Kiva(defaultMap);

        // THEN
        // The initial Kiva Location is (2,4)
        Point initialLocation = kiva.getCurrentLocation();
        Point expectedLocation = new Point(2, 4);
        if (sameLocation(initialLocation, expectedLocation)) {
            System.out.println("testSingleArgumentConstructor Success");
        } else {
            System.out.println(String.format
                    ("testSingleArgumentConstructor Fail: "
                            + "%s != (2,4)!", initialLocation));
        } //else
    } //testSingleArgumentConstructor

    public void testTwoArgumentConstructor() {
        // GIVEN
        // The default map we defined earlier

        // WHEN
        // We create a Kiva with the two argument constructor
        //WITH
        // The initial Kiva Location at (5,6)
        Point expectedLocation = new Point(5, 6);
        Kiva kiva = new Kiva(defaultMap, expectedLocation);

        Point initialLocation = kiva.getCurrentLocation();

        if (sameLocation(initialLocation, expectedLocation)) {
            System.out.println("testSingleArgumentConstructor Success");
        } else {
            System.out.println(String.format
                    ("testSingleArgumentConstructor Fail: "
                            + "%s != (2,4)!", initialLocation));
        } //else
    } //testTwoArgumentConstructor

    /**
     * Checks to see if two edu.duke.Point locations are the same.
     *
     * @param a Point to be tested against
     * @param b Point to be tested
     * @return Returns true if Points are the same.
     */
    private boolean sameLocation(Point a, Point b) {
        return a.getX() == b.getX() && a.getY() == b.getY();
    } //sameLocation

    /**
     * testForward creates a Kiva object using the default map.
     * testForward allows the user to turn the Kiva (unless it is facing the correct direction),
     * then moves the Kiva one square in that direction.
     * testForward then verifies the facing direction and location are correct using verifyKivaState()
     *
     * @see KivaCommand
     */
    public void testForward(){
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva();
        FacingDirection expectDirection = FacingDirection.UP;
        int x = kiva.getCurrentLocation().getX();
        int y = kiva.getCurrentLocation().getY();
        System.out.println("Kiva Initial Location Expected: (" + x + "," + y + ") Actual: " + kiva.getCurrentLocation());
        while(true) {
            System.out.println("The Kiva is facing : " + kiva.getDirectionFacing());
            System.out.println("Type a turning direction, L R, or F (Q Quits)");
            String testInput = keyboardResource.getLine().toUpperCase(Locale.ROOT); //todo grab first index of the string in testForward so user can type Left, Forward, etc.
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
            System.out.println("The Kiva is moving FORWARD");
            kiva.move(KivaCommand.FORWARD);

            //THEN
            //The Kiva has moved one space in the facing direction
            switch (kiva.getDirectionFacing()) {//Modify x or y for expected location
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

            verifyKivaState("testTurnThenForward", kiva, new Point(x, y), expectDirection, false, false);
        }
    }

    private void verifyKivaState(
            String testName,
            Kiva actual,
            Point expectLocation,
            FacingDirection expectDirection,
            boolean expectCarry,
            boolean expectDropped){

        Point actualLocation = actual.getCurrentLocation();
        if (sameLocation(actualLocation, expectLocation)){
            System.out.println(String.format("%s: current location SUCCESS", testName));
        } else {
            System.out.println(String.format("%s: current location FAIL!", testName));
            System.out.println(String.format("Expected %s, got %s",expectLocation, actualLocation));
        }

        FacingDirection actualDirection = actual.getDirectionFacing();
        if (actualDirection == expectDirection){
            System.out.println(String.format("%s: facing direction SUCCESS", testName));
        } else {
            System.out.println(String.format("%s: facing direction FAIL!", testName));
            System.out.println(String.format("Expected %s, got %s",expectDirection, actualDirection));
        }

        boolean actualCarry = actual.isCarryingPod();
        if (actualCarry == expectCarry) {
            System.out.println(String.format("%s: carrying pod SUCCESS", testName));
        } else {
            System.out.println(String.format("%s: facing direction FAIL!", testName));
            System.out.println(String.format("Expected %s, got %s",expectCarry, actualCarry));
        }

        boolean actualDropped = actual.isSuccessfullyDropped();
        if (actualDropped == expectDropped) {
            System.out.println(String.format("%s: successfully dropped SUCCESS", testName));
        } else {
            System.out.println(String.format("%s: successfully dropped FAIL!", testName));
            System.out.println(String.format("Expected %s, got %s",expectDropped, actualDropped));
        }
    }
}//KivaTest

