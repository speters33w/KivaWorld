import edu.duke.Point;
import java.util.regex.Pattern;

/**
 * The test class KivaTest.
 */
public class KivaTest {
    private final KeyboardResource keyboardResource = new KeyboardResource();
    private final FloorMap defaultMap = new KivaCreateMap().defaultMap(); //todo debug null pointer exception where defaultMap is used
    private final Kiva kiva = new Kiva();
    private String testString = "testKiva";
    private FacingDirection expectDirection = FacingDirection.UP;
    private Point expectLocation = new Point(2,4); //starting location in the default map
    private boolean expectCarry = false;
    private boolean expectDropped = false;

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
            System.out.printf("testSingleArgumentConstructor Fail: "
                    + "%s != (2,4)!%n", initialLocation);
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
            System.out.printf("testSingleArgumentConstructor Fail: "
                    + "%s != (2,4)!%n", initialLocation);
        } //else
    } //testTwoArgumentConstructor

    /**
     * Checks to see if two edu.duke.Point locations are the same.
     *
     * @param a Point to be tested against
     * @param b Point to be tested
     * @return Returns true if Points are the same.
     *
     * @see edu.duke.Point
     *
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
     * @see FacingDirection
     * @see edu.duke.Point
     *
     */
    public void testForwardInteractive(){
        // GIVEN
        // A Kiva built with the default map we defined earlier
        FacingDirection expectDirection = FacingDirection.UP;
        int x = kiva.getCurrentLocation().getX();
        int y = kiva.getCurrentLocation().getY();
        System.out.println("Kiva Initial Location Expected: (" + x + "," + y + ") Actual: " + kiva.getCurrentLocation());
        while(true) {
            System.out.println("The Kiva is facing : " + kiva.getDirectionFacing());
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

            verifyKivaState("testForward", kiva, new Point(x,y), expectDirection, expectCarry, expectDropped);
        }
    }

    /**
     * Moves the Kiva object FORWARD the amount of times given in the times parameter.
     * Runs a test at each FORWARD movement.
     *
     * @param times number of times to move the Kiva forward, integer.
     *
     * @see KivaCommand
     * @see edu.duke.Point
     *
     * Example Usage:
     *     KivaTest testKiva = new KivaTest();
     *     testKiva.testForward(3);
     */
    public void testForward(int times){
        int x = kiva.getCurrentLocation().getX();
        int y = kiva.getCurrentLocation().getY();
        for(int i=1;(i<=times);i++){
            System.out.println("The Kiva moves FORWARD");
            kiva.move(KivaCommand.FORWARD);
            System.out.println("currentLocation: (" + x + "," + y + ")");
            switch (kiva.getDirectionFacing()) {//Modify x or y for expectedLocation
                case UP:
                    y--;
                    break;
                case DOWN:
                    y++;
                    break;
                case LEFT:
                    x--;
                    break;
                case RIGHT:
                    x++;
                    break;
            }
            verifyKivaState(testString, kiva, new Point(x,y), expectDirection, expectCarry, expectDropped);
        }
    }

    /**
     * Moves the Kiva FORWARD once.
     */
    public void testForward(){
        testForward(1);
    }

    /**
     * Faces the Kiva UP and moves it FORWARD once.
     */
    public void testForwardFromUp(){
        testString = "testForwardFromUp";
        kiva.setDirectionFacing("UP");
        expectDirection = FacingDirection.UP;
        testForward();
    }

    /**
     * TURNs the Kiva object LEFT the amount of times given in the times parameter.
     * Runs a test at each TURN_LEFT.
     *
     * @param times number of times to turn the Kiva left, integer.
     *
     * @see KivaCommand
     * @see FacingDirection
     *
     * Example Usage:
     *     KivaTest testKiva = new KivaTest();
     *     testKiva.testTurnLeft(3);
     */
    public void testTurnLeft(int times) {
        expectLocation = kiva.getCurrentLocation();
        for (int i = 1; (i <= times); i++) {
            System.out.println("The Kiva is facing " + kiva.getDirectionFacing());
            switch (kiva.getDirectionFacing()) {//correct expectDirection before turning first time
                case UP:
                    expectDirection = FacingDirection.LEFT;
                    break;
                case DOWN:
                    expectDirection = FacingDirection.RIGHT;
                    break;
                case LEFT:
                    expectDirection = FacingDirection.DOWN;
                    break;
                case RIGHT:
                    expectDirection = FacingDirection.UP;
            }
            System.out.println("The Kiva TURNs_LEFT");
            kiva.move(KivaCommand.TURN_LEFT);
            verifyKivaState(testString, kiva, expectLocation, expectDirection, expectCarry, expectDropped);
        }
    }

    /**
     * TURNs the Kiva LEFT once.
     */
    public void testTurnLeft(){
        testTurnLeft(1);
    }

    /**
     * Faces the Kiva UP and TURNs it LEFT once.
     */
    public void testTurnLeftFromUP(){
        testString = "testTurnLeftFromUp";
        kiva.setDirectionFacing("UP");
        testTurnLeft();
    }

    /**
     * Faces the Kiva LEFT and TURNs it LEFT once.
     */
    public void testTurnLeftFromLeft(){
        testString = "testTurnLeftFromLeft";
        kiva.setDirectionFacing("LEFT");
        testTurnLeft();
    }

    /**
     * Faces the Kiva DOWN and TURNs it LEFT once.
     */
    public void testTurnLeftFromDown(){
        testString = "testTurnLeftFromDown";
        kiva.setDirectionFacing("DOWN");
        testTurnLeft();
    }

    /**
     * Faces the Kiva RIGHT and TURNs it LEFT once.
     */
    public void testTurnLeftFromRight(){
        testString = "testTurnLeftFromRight";
        kiva.setDirectionFacing("RIGHT");
        testTurnLeft();
    }

    /**
     * TURNs the Kiva object RIGHT the amount of times given in the times parameter.
     * Runs a test at each TURN_RIGHT.
     *
     * @param times number of times to turn the Kiva right, integer.
     *
     * Example Usage:
     *     KivaTest testKiva = new KivaTest();
     *     testKiva.testRight(3);
     */
    public void testTurnRight(int times) {
        expectLocation = kiva.getCurrentLocation();
        for (int i = 1; (i <= times); i++) {
            System.out.println("The Kiva is facing " + kiva.getDirectionFacing());
            switch (kiva.getDirectionFacing()) {//correct expectDirection before turning first time
                case UP:
                    expectDirection = FacingDirection.RIGHT;
                    break;
                case DOWN:
                    expectDirection = FacingDirection.LEFT;
                    break;
                case LEFT:
                    expectDirection = FacingDirection.UP;
                    break;
                case RIGHT:
                    expectDirection = FacingDirection.DOWN;
            }
            System.out.println("The Kiva TURNs_LEFT");
            kiva.move(KivaCommand.TURN_LEFT);
            verifyKivaState(testString, kiva, expectLocation, expectDirection, expectCarry, expectDropped);
        }
    }

    /**
     * TURNs the Kiva RIGHT once.
     */

    public void testTurnRight(){
        testTurnRight(1);
    }

    /**
     * Faces the Kiva UP and TURNs it RIGHT once.
     */
    public void testTurnRightFromUP(){
        testString = "testTurnRightFromUp";
        kiva.setDirectionFacing("UP");
        testTurnRight();
    }

    /**
     * Faces the Kiva LEFT and TURNs it RIGHT once.
     */
    public void testTurnRightFromLeft(){
        testString = "testTurnRightFromLeft";
        kiva.setDirectionFacing("LEFT");
        testTurnRight();
    }

    /**
     * Faces the Kiva DOWN and TURNs it RIGHT once.
     */
    public void testTurnRightFromDown(){
        testString = "testTurnRightFromDown";
        kiva.setDirectionFacing("DOWN");
        testTurnRight();
    }

    /**
     * Faces the Kiva RIGHT and TURNs it RIGHT once.
     */
    public void testTurnRightFromRight(){
        testString = "testTurnRightFromRight";
        kiva.setDirectionFacing("RIGHT");
        testTurnRight();
    }

    /**
     * Allows the Kiva to TAKE a pod.
     */
    public void testTake(){
        testString = "testTake";
        Point expectLocation = kiva.getCurrentLocation();
        FacingDirection expectDirection = kiva.getDirectionFacing();
        System.out.println("\nThe Kiva is TAKEs a pod");
        kiva.move(KivaCommand.TAKE);
        expectCarry = true;
        verifyKivaState(testString, kiva, expectLocation, expectDirection, expectCarry, expectDropped);
    }

    /**
     * Allows the Kiva to DROP a pod.
     * If the Kiva is not carrying a pod, it TAKEs one first.
     */
    public void testDrop(){
        testString = "testDrop";
        Point expectLocation = kiva.getCurrentLocation();
        FacingDirection expectDirection = kiva.getDirectionFacing();
        if (!kiva.isCarryingPod()) {
            System.out.println("\nThe Kiva is TAKEs a pod");
            kiva.move(KivaCommand.TAKE);
        }
        System.out.println("The Kiva DROPs the pod");
        kiva.move(KivaCommand.DROP);
        expectCarry = false;
        expectDropped = false;
        verifyKivaState(testString, kiva, expectLocation, expectDirection, expectCarry, expectDropped);
    }

    /**
     * Tests expected Kiva attributes against actual Kiva state for a pass/fail test
     * and outputs the result to the console.
     *
     * @param testName String The test name provided by the test performed, default is "testKiva".
     * @param actual Kiva object The Kiva object to test
     * @param expectLocation Point The expected Kiva location
     * @param expectDirection FacingDirection The expected Kiva facing direction
     * @param expectCarry boolean If the Kiva is expected to be carrying a pod
     * @param expectDropped boolean If the kiva is expected to have dropped a pod
     */
    private void verifyKivaState(
            String testName,
            Kiva actual,
            Point expectLocation,
            FacingDirection expectDirection,
            boolean expectCarry,
            boolean expectDropped){

        Point actualLocation = actual.getCurrentLocation();
        if (sameLocation(actualLocation, expectLocation)){
            System.out.println("(" + actualLocation.getX() + "," + actualLocation.getY() + ")           " + testName
                    + ": current location     SUCCESS");
        } else {
            System.out.printf("%s: current location FAIL!%n", testName);
            System.out.printf("Expected %s, got %s%n",expectLocation, actualLocation);
        }

        FacingDirection actualDirection = actual.getDirectionFacing();
        if (actualDirection == expectDirection){
            System.out.println(actualDirection + "            " + testName
                    + ": facing direction     SUCCESS");
        } else {
            System.out.printf("%s: facing direction FAIL!%n", testName);
            System.out.printf("Expected %s, got %s%n",expectDirection, actualDirection);
        }

        boolean actualCarry = actual.isCarryingPod();
        if (actualCarry == expectCarry) {
            System.out.println("Carrying: " + actualCarry + " " + testName + ": carrying pod         SUCCESS");
        } else {
            System.out.printf("%s: facing direction FAIL!%n", testName);
            System.out.printf("Expected %s, got %s%n",expectCarry, actualCarry);
        }

        boolean actualDropped = actual.isSuccessfullyDropped();
        if (actualDropped == expectDropped) {
            System.out.println("Dropped: " + actualDropped + "  " + testName +": successfully dropped SUCCESS");
        } else {
            System.out.printf("%s: successfully dropped FAIL!%n", testName);
            System.out.printf("Expected %s, got %s%n",expectDropped, actualDropped);
        }
        testString = "testKiva";
        System.out.println();
    }
}//KivaTest

