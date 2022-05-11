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
    private boolean sameLocation(Point a, Point b) {
        return a.getX() == b.getX() && a.getY() == b.getY();
    } //sameLocation

    public void testForward(){
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva();
        FacingDirection expectDirection = FacingDirection.UP;
        int x = kiva.getCurrentLocation().getX();
        int y = kiva.getCurrentLocation().getY();
        System.out.println("Kiva Initial Location Expected: (" + x + "," + y + ") Actual: " + kiva.getCurrentLocation());
        System.out.println("The Kiva is facing : " + kiva.getDirectionFacing());
        System.out.println("Type a facing direction, U D L R");
        String testInput = keyboardResource.getLine().toUpperCase(Locale.ROOT);
        if (Pattern.matches("[UDLR]+",testInput )) {
            switch (testInput) {
                case "U":
                    break;
                case "D":
                    kiva.move(KivaCommand.TURN_LEFT);
                    kiva.move(KivaCommand.TURN_LEFT); // never turn thrice widdershins, it's bad luck.
                    break;
                case "L":
                    kiva.move(KivaCommand.TURN_LEFT);
                    break;
                case "R":
                    kiva.move(KivaCommand.TURN_RIGHT);
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
        switch (testInput){//Modify x or y for expected location
            case "U":
                y--;
                break;
            case "D":
                y++;
                expectDirection = FacingDirection.DOWN;
                break;
            case "L":
                x--;
                expectDirection = FacingDirection.LEFT;
                break;
            case "R":
                x++;
                expectDirection = FacingDirection.RIGHT;
                break;
        }

        verifyKivaState("testForward",kiva,new Point(x,y),expectDirection,false, false);
    }
    // For you: create all the other tests and call verifyKivaState() for each

    public void testTurnLeft(){
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva();

        // WHEN
        // We turn left
        kiva.move(KivaCommand.TURN_LEFT);

        // THEN
        // The Kiva has turned widdershins
        verifyKivaState("testTurnLeft",kiva,new Point(2,4),FacingDirection.LEFT, false, false);

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

