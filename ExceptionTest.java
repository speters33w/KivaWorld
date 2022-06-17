import java.io.FileNotFoundException;
import java.util.List;

public class ExceptionTest {

    public static void main(String[] args) throws FileNotFoundException {
        testE1(); // Successful Drop
        testE2(); // Successful Drop
//        testE3(); // Throws InvalidKivaCommandException - Invalid Kiva Command
        testE4(); // Unsuccessful Drop - Move after Drop
        testE5(); // Unsuccessful Drop - Only one command issued
        testE6(); // Unsuccessful Drop - Take Pod, move to Drop Zone, but no Drop
//        testE7(); // Throws NoPodException - Attempt Take at empty location
//        testE8(); // Throws IllegalDropZoneException - Attempt Drop at empty location
//        testE9(); // Throws IllegalMoveException - Collision with an object
//        testE10(); // Throws IllegalMoveException - Collision with a Pod while carrying a Pod
//        testE11(); // Throws InvalidMapLayoutException: - Invalid map, two Pods
        testE12(); // Successful Drop
        testE13(); // Successful Drop - checks for erroneous pod collision.
//        testE14(); // Throws IllegalMoveException - Kiva leaves map in UP direction
//        testE15(); // Throws IllegalMoveException - Kiva leaves map in RIGHT direction
//        testE16(); // Throws IllegalMoveException - Kiva leaves map in DOWN direction
//        testE17(); // Throws IllegalMoveException - Kiva leaves map in LEFT direction
    }

    public static String exceptionTestMap() {
        return "" +
                "- ---\n" +
                "|   |\n" +
                "| * |\n" +
                " P   \n" +
                "| * |\n" +
                "|K D|\n" +
                "--- -";
    }

    public static String twoPodTestMap() {
        return "" +
                "-----\n" +
                "|   |\n" +
                "| * |\n" +
                "|P P|\n" +
                "| * |\n" +
                "|K D|\n" +
                "-----";
    }

    /**
     * Test E1 tests for successful drop.
     * <p>
     * Map: exception_test_map.txt
     * Input: <pre>FFTRFFRFFD</pre>
     * Expected Output: <pre>Successfully picked up the pod and dropped it off. Thank you!</pre>
     */
    public static void testE1() throws FileNotFoundException {
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case E1: \n Map: exception_test_map.txt \n Input: FFTRFFRFFD \n " +
                "Expected Output: Successfully picked up the pod and dropped it off. Thank you!");
        remoteControl.run("exception_test_map.txt", "FFTRFFRFFD");
    }

    /**
     * Test E2 tests for successful drop.
     * <p>
     * Map: exception_test_map.txt
     * Input: <pre>FFTFFRFFRFFFFD</pre>
     * Expected Output: <pre>Successfully picked up the pod and dropped it off. Thank you!</pre>
     */
    public static void testE2() throws FileNotFoundException {
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case E2: \n Map: exception_test_map.txt \n Input: FFTFFRFFRFFFFD \n " +
                "Expected Output: Successfully picked up the pod and dropped it off. Thank you!");
        remoteControl.run("exception_test_map.txt", "FFTFFRFFRFFFFD");
    }

    /**
     * Test E3 tests for invalid Kiva command
     * <p>
     * Map: exception_test_map.txt
     * Input: <pre>FFTRFFRFXD</pre>
     * Expected Output: <pre>Exception in thread "main" InvalidKivaCommandException: Invalid Kiva Command: X</pre>
     */
    public static void testE3() throws FileNotFoundException {
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case E3: \n Map: exception_test_map.txt \n Input: FFTRFFRFXD \n " +
                "Expected Output: Exception in thread \"main\" InvalidKivaCommandException: Invalid Kiva Command: X \n");
        remoteControl.run("exception_test_map.txt", "FFTRFFRFXD");
    }

    /**
     * Test E4 tests for move after drop.
     * <p>
     * Map: exception_test_map.txt
     * Input: <pre>FFTRFFRFFDR</pre>
     * Expected Output: <pre>I'm sorry. The Kiva Robot did not pick up the pod and then drop it off correctly</pre>
     */
    public static void testE4() throws FileNotFoundException {
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case E4: \n Map: exception_test_map.txt \n Input: FFTRFFRFFDR \n " +
                "Expected Output: I'm sorry. The Kiva Robot did not pick up the pod and then drop it off correctly, \n" +
                " (because dropping the pod at the drop zone was not the last command.)\n");
        remoteControl.run("exception_test_map.txt", "FFTRFFRFFDR");
    }

    /**
     * Test E5 tests for single command followed by return.
     * <p>
     * Map: exception_test_map.txt
     * Input: <pre>F</pre>
     * Expected Output: <pre>I'm sorry. The Kiva Robot did not pick up the pod and then drop it off correctly</pre>
     */
    public static void testE5() throws FileNotFoundException {
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case E5: \n Map: exception_test_map.txt \n Input: F \n " +
                "Expected Output: I'm sorry. The Kiva Robot did not pick up the pod and then drop it off correctly. \n");
        remoteControl.run("exception_test_map.txt", "F");
    }

    /**
     * Test E6 tests for successful take but no drop.
     * <p>
     * Map: exception_test_map.txt
     * Input: <pre>FFTRFFRFF</pre>
     * Expected Output: <pre>I'm sorry. The Kiva Robot did not pick up the pod and then drop it off correctly</pre>
     */
    public static void testE6() throws FileNotFoundException {
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case E6: \n Map: exception_test_map.txt \n Input: FFTRFFRFF \n " +
                "Expected Output: I'm sorry. The Kiva Robot did not pick up the pod and then drop it off correctly. \n");
        remoteControl.run("exception_test_map.txt", "FFTRFFRFF");
    }

    /**
     * Test E7 tests for attempt to take pod at an empty location.
     * <p>
     * Map: exception_test_map.txt
     * Input: <pre>FTFRFFRFFD</pre>
     * Expected Output: <pre>Exception in thread "main" NoPodException: The kiva attempted to TAKE a POD while there is no POD at location (1,4)</pre>
     */
    public static void testE7() throws FileNotFoundException {
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case E7: \n Map: exception_test_map.txt \n Input: FTFRFFRFFD \n " +
                "Expected Output: Exception in thread \"main\" NoPodException: The kiva attempted to TAKE a POD while there is no POD at location (1,4) \n");
        remoteControl.run("exception_test_map.txt", "FTFRFFRFFD");
    }

    /**
     * Test E8 tests for attempt to take drop a pod outside a drop zone.
     * <p>
     * Map: exception_test_map.txt
     * Input: <pre>FFTRFFRFD</pre>
     * Expected Output: <pre>Exception in thread "main" IllegalDropZoneException: The kiva attempted to DROP not on a DROP_ZONE at location (3,4)</pre>
     */
    public static void testE8() throws FileNotFoundException {
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case E8: \n Map: exception_test_map.txt \n Input: FFTRFFRFD \n " +
                "Expected Output: Exception in thread \"main\" IllegalDropZoneException: The kiva attempted to DROP not on a DROP_ZONE at location (3,4) \n");
        remoteControl.run("exception_test_map.txt", "FFTRFFRFD");
    }

    /**
     * Test E9 tests for a simple Kiva collision into an obstacle.
     * <p>
     * Map: exception_test_map.txt
     * Input: <pre>FFTRFRFFLD</pre>
     * Expected Output: <pre>Exception in thread "main" IllegalMoveException: The kiva ran into an obstacle at location (2,4)</pre>
     */
    public static void testE9() throws FileNotFoundException {
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case E9: \n Map: exception_test_map.txt \n Input: FFTRFRFFLD \n " +
                "Expected Output: Exception in thread \"main\" IllegalMoveException: The kiva ran into an obstacle at location (2,4)) \n");
        remoteControl.run("exception_test_map.txt", "FFTRFRFFLD");
    }

    /**
     * Test E10 tests for a Pod collision if Kiva is carrying a Pod.
     * <p>
     * Map: exceptionTestMap()
     * Input: <pre>FFTRFFRFFD</pre>
     * Expected Output: <pre>EException in thread "main" IllegalMoveException: The kiva ran into a POD while carrying another POD at location (1,3)</pre>
     */
    public static void testE10()  {
        List<KivaCommand> kivaCommands;
        FloorMap floorMap = new FloorMap(exceptionTestMap());
        Kiva kiva = new Kiva(floorMap);
        System.out.println("\n\n Test Case E10: \n Map: exceptionTestMap() \n Input: FFTRFFRFFD and carryingPod \n " +
                "Expected Output: Exception in thread \"main\" IllegalMoveException: The kiva ran into a POD while carrying another POD at location (1,3)) \n");
        String directions = "FFTRFFRFFD";
        kiva.carryingPod = true;
        kivaCommands = RemoteControl.convertToKivaCommands(directions);
        System.out.println("Commands you sent to the Kiva: " + kivaCommands);
        for (int i = 0; i < directions.length(); i++) {
            kiva.move(kivaCommands.get(i));
        }
        if (kiva.successfullyDropped && kivaCommands.get(directions.length() - 1) == KivaCommand.DROP) {
            System.out.println("Successfully picked up the pod and dropped it off. Thank you!\n");
        } else {
            System.out.println("I'm sorry. The Kiva Robot did not pick up the pod and then drop it off correctly.\n");
        }
    }

    /**
     * Test E11 tests for an invalid map with two Pods.
     * <p>
     * Map: twoPodTestMap()
     * Input: <pre>FFRFFRFFD</pre>
     * Expected Output: <pre>Exception in thread "main" InvalidMapLayoutException: Found a second POD at (3, 3)</pre>
     */
    public static void testE11()  {
        List<KivaCommand> kivaCommands;
        System.out.println("\n\n Test Case E11: \n Map: twoPodTestMap() \n Input: FFRFFRFFD and carryingPod \n " +
                "Expected Output: Exception in thread \"main\" InvalidMapLayoutException: Found a second POD at (3, 3) \n");
        System.out.println(twoPodTestMap());
        FloorMap floorMap = new FloorMap(twoPodTestMap());
        Kiva kiva = new Kiva(floorMap);
        String directions = "FFRFFRFFD";
        kiva.carryingPod = true;
        kivaCommands = RemoteControl.convertToKivaCommands(directions);
        System.out.println("Commands you sent to the Kiva: " + kivaCommands);
        for (int i = 0; i < directions.length(); i++) {
            kiva.move(kivaCommands.get(i));
        }
        if (kiva.successfullyDropped && kivaCommands.get(directions.length() - 1) == KivaCommand.DROP) {
            System.out.println("Successfully picked up the pod and dropped it off. Thank you!\n");
        } else {
            System.out.println("I'm sorry. The Kiva Robot did not pick up the pod and then drop it off correctly.\n");
        }
    }

    /**
     * Test E12 tests for successful drop.
     * <p>
     * Map: exception_test_map.txt
     * Input: <pre>FFFFRFFRFFRFFTLFFLFFD</pre>
     * Expected Output: <pre>Successfully picked up the pod and dropped it off. Thank you!</pre>
     */
    public static void testE12() throws FileNotFoundException {
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case E12: \n Map: exception_test_map.txt \n Input: FFFFRFFRFFRFFTLFFLFFD \n " +
                "Expected Output: Successfully picked up the pod and dropped it off. Thank you!");
        remoteControl.run("exception_test_map.txt", "FFFFRFFRFFRFFTLFFLFFD");
    }

    /**
     * Test E13 tests for erroneous pod collision if Kiva enters former pod location after taking the pod.
     * <p>
     * Map: exception_test_map.txt
     * Input: <pre>FFTFFRFFRFFRFFLFFLFFD</pre>
     * Expected Output: <pre>Successfully picked up the pod and dropped it off. Thank you!</pre>
     */
    public static void testE13() throws FileNotFoundException {
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case E12: \n Map: exception_test_map.txt \n Input: FFTFFRFFRFFRFFLFFLFFD \n " +
                "Expected Output: Successfully picked up the pod and dropped it off. Thank you!" +
                "\n tests for erroneous pod collision if Kiva enters former pod location (1,3) after taking the pod.");
        remoteControl.run("exception_test_map.txt", "FFTFFRFFRFFRFFLFFLFFD");
    }

    /**
     * Test E14 tests for Kiva leaves map in the UP direction.
     * <p>
     * Map: exception_test_map.txt
     * Input: <pre>FFTFFFFRFFFFD</pre>
     * Expected Output: <pre>Exception in thread "main" IllegalMoveException: The Kiva left the defined FloorMap to location (1,-1)</pre>
     */
    public static void testE14() throws FileNotFoundException {
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case E14: \n Map: exception_test_map.txt \n Input: FFTFFFFRFFFFD \n " +
                "Expected Output: Exception in thread \"main\" IllegalMoveException: The Kiva left the defined FloorMap to location (1,-1)) \n");
        remoteControl.run("exception_test_map.txt", "FFTFFFFRFFFFD");
    }

    /**
     * Test E15 tests for Kiva leaves map in the RIGHT direction.
     * <p>
     * Map: exception_test_map.txt
     * Input: <pre>FFTRFFFFD</pre>
     * Expected Output: <pre>Exception in thread "main" IllegalMoveException: The Kiva left the defined FloorMap to location (5,3)</pre>
     */
    public static void testE15() throws FileNotFoundException {
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case E15: \n Map: exception_test_map.txt \n Input: FFTRFFFFD \n " +
                "Expected Output: Exception in thread \"main\" IllegalMoveException: The Kiva left the defined FloorMap to location (5,3)) \n");
        remoteControl.run("exception_test_map.txt", "FFTRFFFFD");
    }

    /**
     * Test E16 tests for Kiva leaves map in the DOWN direction.
     * <p>
     * Map: exception_test_map.txt
     * Input: <pre>FFTRFFRFFFFD</pre>
     * Expected Output: <pre>Exception in thread "main" IllegalMoveException: The Kiva left the defined FloorMap to location (3,7)</pre>
     */
    public static void testE16() throws FileNotFoundException {
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case E16: \n Map: exception_test_map.txt \n Input: FFTRFFRFFFFD \n " +
                "Expected Output: Exception in thread \"main\" IllegalMoveException: The Kiva left the defined FloorMap to location (3,7)) \n");
        remoteControl.run("exception_test_map.txt", "FFTRFFRFFFFD");
    }

    /**
     * Test E17 tests for Kiva leaves map in the LEFT direction.
     * <p>
     * Map: exception_test_map.txt
     * Input: <pre>FFTLFFRFFD</pre>
     * Expected Output: <pre>Exception in thread "main" IllegalMoveException: The Kiva left the defined FloorMap to location (-1,3)</pre>
     */
    public static void testE17() throws FileNotFoundException {
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case E17: \n Map: exception_test_map.txt \n Input: FFTLFFRFFD \n " +
                "Expected Output: Exception in thread \"main\" IllegalMoveException: The Kiva left the defined FloorMap to location (-1,3)) \n");
        remoteControl.run("exception_test_map.txt", "FFTLFFRFFD");
    }
}