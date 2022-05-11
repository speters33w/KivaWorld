import edu.duke.Point;

/**
 * The test class KivaTest.
 */
public class KivaTest {

    FloorMap defaultMap = new CreateMap().defaultMap();

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

    public void testForwardFromUp(){
        // GIVEN
        // A Kiva built with the default map we defined earlier
        Kiva kiva = new Kiva(defaultMap);

        //WHEN
        //We move one space forward
        kiva.move(KivaCommand.FORWARD);

        //THEN
        //The Kiva has moved one space up
        verifyKivaState("testForwardFromUp",kiva,new Point(2,3),FacingDirection.UP,false,false);
    }

    // For you: create all the other tests and call verifyKivaState() for each

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

