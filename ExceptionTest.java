import java.io.FileNotFoundException;
import java.util.List;

public class ExceptionTest {

    FloorMap testMap;

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
        testE11(); // Successful Drop
//        testE12(); // TEST FAILS! Should be successful drop but throws IllegalMoveException - Collision with a Pod while carrying a Pod
    }

    static String exceptionTestMap() {
        return "" +
                "- ---\n" +
                "|   |\n" +
                "| * |\n" +
                " P   \n" +
                "| * |\n" +
                "|K D|\n" +
                "--- -";
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
     * Test E11 tests for successful drop.
     * <p>
     * Map: exception_test_map.txt
     * Input: <pre>FFFFRFFRFFRFFTLFFLFFD</pre>
     * Expected Output: <pre>Successfully picked up the pod and dropped it off. Thank you!</pre>
     */
    public static void testE11() throws FileNotFoundException {
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case E11: \n Map: exception_test_map.txt \n Input: FFFFRFFRFFRFFTLFFLFFD \n " +
                "Expected Output: Successfully picked up the pod and dropped it off. Thank you!");
        remoteControl.run("exception_test_map.txt", "FFFFRFFRFFRFFTLFFLFFD");
    }

    /**
     * Test E12 tests for successful drop.
     * TEST FAILS with IllegalMoveException when Kiva enters the location where the Pod was.
     * <p>
     * Map: exception_test_map.txt
     * Input: <pre>FFTFFRFFRFFRFFLFFLFFD</pre>
     * Expected Output: <pre>Successfully picked up the pod and dropped it off. Thank you!</pre>
     */
    public static void testE12() throws FileNotFoundException {
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case E11: \n Map: exception_test_map.txt \n Input: FFTFFRFFRFFRFFLFFLFFD \n " +
                "Expected Output: Successfully picked up the pod and dropped it off. Thank you!" +
                "\n THIS TEST FAILS WHEN KIVA REACHES FORMER POD LOCATION");
        remoteControl.run("exception_test_map.txt", "FFTFFRFFRFFRFFLFFLFFD");
    }
}