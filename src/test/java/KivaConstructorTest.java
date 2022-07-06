import edu.duke.Point;

/**
 * Test Kiva constructors
 *
 * @author Stephan Peters (peterstz)
 * @version 20220607.1300
 */
public class KivaConstructorTest{

    private final FloorMap defaultMap = new KivaCreateMap().defaultMap();

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
            System.out.println("testTwoArgumentConstructor Success");
        } else {
            System.out.printf("testTwoArgumentConstructor Fail: "
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
     * Runs the Kiva Constructor tests.
     */
    public static void main(String[] args) {
        KivaConstructorTest kivaConstructorTest = new KivaConstructorTest();
        kivaConstructorTest.testSingleArgumentConstructor();
        kivaConstructorTest.testTwoArgumentConstructor();
    }
}
