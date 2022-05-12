import edu.duke.Point;
import java.util.regex.Pattern;

/**
 * The test class KivaTest.
 */
public class KivaTest {
    private final KeyboardResource keyboardResource = new KeyboardResource();
    private final Kiva kiva = new Kiva(); //not final because of constructor tests
    private String testString = "testKiva";
    private FacingDirection expectDirection = FacingDirection.UP;
    private Point expectLocation = new Point(2,4); //starting location in the default map
    private boolean expectCarry = false;
    private boolean expectDropped = false;
    private boolean testMode = true;

    public void setTestMode(boolean testMode){
        this.testMode = testMode;
//        if(testMode){
//            System.out.println("Verify Kiva State Active");
//        } else {
//            System.out.println("Verify Kiva State Off");
//        }
    }

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
            System.out.println("The Kiva moves FORWARD.");
            kiva.move(KivaCommand.FORWARD);
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
            System.out.println("currentLocation: (" + x + "," + y + ")");
            if (testMode){
                verifyKivaState(testString, kiva, new Point(x,y), expectDirection, expectCarry, expectDropped);
            }
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
        System.out.println ("The Kiva is facing " + kiva.getDirectionFacing() + ".");
        expectDirection = FacingDirection.UP;
        testForward();
    }

    /**
     * Faces the Kiva LEFT and moves it FORWARD once.
     */
    public void testForwardFromLeft(){
        testString = "testForwardFromLeft";
        kiva.setDirectionFacing("LEFT");
        System.out.println ("The Kiva is facing " + kiva.getDirectionFacing() + ".");
        expectDirection = FacingDirection.LEFT;
        testForward();
    }

    /**
     * Faces the Kiva DOWN and moves it FORWARD once.
     */
    public void testForwardFromDown(){
        testString = "testForwardFromDown";
        kiva.setDirectionFacing("DOWN");
        System.out.println ("The Kiva is facing " + kiva.getDirectionFacing() + ".");
        expectDirection = FacingDirection.DOWN;
        testForward();
    }

    /**
     * Faces the Kiva DOWN and moves it FORWARD once.
     */
    public void testForwardFromRight(){
        testString = "testForwardFromRight";
        kiva.setDirectionFacing("RIGHT");
        System.out.println ("The Kiva is facing " + kiva.getDirectionFacing() + ".");
        expectDirection = FacingDirection.RIGHT;
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
            System.out.println ("The Kiva is facing " + kiva.getDirectionFacing() + ".");
            if (testMode){
                verifyKivaState(testString, kiva, expectLocation, expectDirection, expectCarry, expectDropped);
            }
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
        System.out.println ("The Kiva is facing " + kiva.getDirectionFacing() + ".");
        testTurnLeft();
    }

    /**
     * Faces the Kiva LEFT and TURNs it LEFT once.
     */
    public void testTurnLeftFromLeft(){
        testString = "testTurnLeftFromLeft";
        kiva.setDirectionFacing("LEFT");
        System.out.println ("The Kiva is facing " + kiva.getDirectionFacing() + ".");
        testTurnLeft();
    }

    /**
     * Faces the Kiva DOWN and TURNs it LEFT once.
     */
    public void testTurnLeftFromDown(){
        testString = "testTurnLeftFromDown";
        kiva.setDirectionFacing("DOWN");
        System.out.println ("The Kiva is facing " + kiva.getDirectionFacing() + ".");
        testTurnLeft();
    }

    /**
     * Faces the Kiva RIGHT and TURNs it LEFT once.
     */
    public void testTurnLeftFromRight(){
        testString = "testTurnLeftFromRight";
        kiva.setDirectionFacing("RIGHT");
        System.out.println ("The Kiva is facing " + kiva.getDirectionFacing() + ".");
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
            System.out.println("The Kiva TURNs_RIGHT");
            kiva.move(KivaCommand.TURN_RIGHT);
            System.out.println ("The Kiva is facing " + kiva.getDirectionFacing() + ".");
            if (testMode){
                verifyKivaState(testString, kiva, expectLocation, expectDirection, expectCarry, expectDropped);
            }
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
        System.out.println ("The Kiva is facing " + kiva.getDirectionFacing() + ".");
        testTurnRight();
    }

    /**
     * Faces the Kiva LEFT and TURNs it RIGHT once.
     */
    public void testTurnRightFromLeft(){
        testString = "testTurnRightFromLeft";
        kiva.setDirectionFacing("LEFT");
        System.out.println ("The Kiva is facing " + kiva.getDirectionFacing() + ".");
        testTurnRight();
    }

    /**
     * Faces the Kiva DOWN and TURNs it RIGHT once.
     */
    public void testTurnRightFromDown(){
        testString = "testTurnRightFromDown";
        kiva.setDirectionFacing("DOWN");
        System.out.println ("The Kiva is facing " + kiva.getDirectionFacing() + ".");
        testTurnRight();
    }

    /**
     * Faces the Kiva RIGHT and TURNs it RIGHT once.
     */
    public void testTurnRightFromRight(){
        testString = "testTurnRightFromRight";
        kiva.setDirectionFacing("RIGHT");
        System.out.println ("The Kiva is facing " + kiva.getDirectionFacing() + ".");
        testTurnRight();
    }

    /**
     * Allows the Kiva to TAKE a pod.
     */
    public void testTake(){
        testString = "testTake";
        Point expectLocation = kiva.getCurrentLocation();
        FacingDirection expectDirection = kiva.getDirectionFacing();
        System.out.println("The Kiva is TAKEs a pod");
        kiva.move(KivaCommand.TAKE);
        expectCarry = true;
        if (testMode){
                verifyKivaState(testString, kiva, expectLocation, expectDirection, expectCarry, expectDropped);
            }
    }

    /**
     * Tests TAKE at pod location using default map (8,1).
     * Assumes start at default map start location (2,4).
     */
    public void testTakeOnPod(){
        verifyKivaState();
        setTestMode(false);
        testForward(3);
        testTurnRight();
        testForward(6);
        setTestMode(true);
        testTake();
    }

    /**
     * Allows the Kiva to DROP a pod.
     * If the Kiva is not carrying a pod, it TAKEs one first.
     */
    public void testDrop(){ //todo DROP allows drop anywhere, regardless of default map, should be testDropOnDropZone()
        testString = "testDrop";
        Point expectLocation = kiva.getCurrentLocation();
        FacingDirection expectDirection = kiva.getDirectionFacing();
        if (!kiva.isCarryingPod()) {
            System.out.println("The Kiva is TAKEs a pod");
            kiva.move(KivaCommand.TAKE);
        }
        System.out.println("The Kiva DROPs the pod");
        kiva.move(KivaCommand.DROP);
        expectCarry = false;
        expectDropped = true;
        if (testMode){
                verifyKivaState(testString, kiva, expectLocation, expectDirection, expectCarry, expectDropped);
            }
    }

    /**
     * Tests DROP on drop zone using default map (10,4).
     * Assumes start at default map pod location (8,1).
     */
    public void testDropOnDropZone(){
        verifyKivaState();
        setTestMode(false);
        testTurnRight();
        testForward();
        testTurnLeft();
        testForward(2);
        testTurnRight();
        testForward(2);
        setTestMode(true);
        testDrop();
    }

    /**
     * Tests expected Kiva attributes against actual Kiva state for a pass/fail test
     * and outputs the result to the console.
     *
     * @param testName String The test name provided by the test performed, default is "testKiva".
     * @param actual Kiva object The Kiva object to test
     * @param expectLocation Point The expected Kiva location, default map location (2,4)
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
            System.out.println(testName + ": current location (" + actualLocation.getX() + ","
                    + actualLocation.getY() + ") SUCCESS");
        } else {
            System.out.printf("%s: current location FAIL!%n", testName);
            System.out.printf("Expected %s, got %s%n",expectLocation, actualLocation);
        }

        FacingDirection actualDirection = actual.getDirectionFacing();
        if (actualDirection == expectDirection){
            System.out.println(testName + ": facing direction " + actualDirection + " SUCCESS");
        } else {
            System.out.printf("%s: facing direction FAIL!%n", testName);
            System.out.printf("Expected %s, got %s%n",expectDirection, actualDirection);
        }

        boolean actualCarry = actual.isCarryingPod();
        if (actualCarry == expectCarry) {
            System.out.println(testName + ": carrying pod " + actualCarry + " SUCCESS");
        } else {
            System.out.printf("%s: facing direction FAIL!%n", testName);
            System.out.printf("Expected %s, got %s%n",expectCarry, actualCarry);
        }

        boolean actualDropped = actual.isSuccessfullyDropped();
        if (actualDropped == expectDropped) {
            System.out.println(testName +": successfully dropped " + actualDropped + " SUCCESS");
        } else {
            System.out.printf("%s: successfully dropped FAIL!%n", testName);
            System.out.printf("Expected %s, got %s%n",expectDropped, actualDropped);
        }
//        kiva.successfullyDropped = false;
//        expectDropped = false; //todo make kiva.successfullyDropped at end of verifyKivaState work right
        testString = "testKiva";
        System.out.println();
    }

    /**
     * Displays the current Kiva state on the console.
     * This can not be used as a verification test, all tests will pass with SUCCESS.
     */
    void verifyKivaState(){
        verifyKivaState("currentKivaState", kiva, kiva.getCurrentLocation(), kiva.getDirectionFacing(), kiva.isCarryingPod(), kiva.isSuccessfullyDropped());
    }
}//KivaTest

