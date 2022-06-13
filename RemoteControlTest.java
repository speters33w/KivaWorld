
/**
 * Tests the remote control app for successful Kiva missions, unsuccessful Kiva missions, and command errors (exceptions).
 * This file should be accessed as an uncompiled java file.
 *
 * Each test can be run from main by uncommenting the method call line.
 *
 * @author Stephan Peters (peterstz)
 * @version 20220613.0900
 */
public class RemoteControlTest {
    public static void main(String[] args) {
//        testCommands(); // Will throw an InvalidKivaCommand exception and terminate the program
        testValidPaths();
        testFails(); // includes testMoveAfterDrop
//        testNoPodException(); // Will throw a NoPodException exception and terminate the program
//        testIllegalDropZoneException(); // Will throw an IllegalDropZone exception and terminate the program
//        testObstacleException(); // Will throw an IllegalMove exception and terminate the program
    }

    /**
     * Tests successful Kiva missions for maps sample_floor_map1.txt, sample_floor_map2.txt, and sample_floor_map3.txt.
     *
     * Expected Output: Successfully picked up the pod and dropped it off. Thank you!
     */
    public static void testValidPaths(){
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case 1: \n Map: sample_floor_map1.txt \n Input: RFFFFFTFFFFFFFD \n " +
                "Expected Output: Successfully picked up the pod and dropped it off. Thank you!");
        remoteControl.run("sample_floor_map1.txt", "RFFFFFTFFFFFFFD");
        System.out.println("\n\n Test Case 2: \n Map: sample_floor_map2.txt \n Input: RFFFFFFLFFFTRFFRFFFD \n " +
                "Expected Output: Successfully picked up the pod and dropped it off. Thank you!");
        remoteControl.run("sample_floor_map2.txt", "RFFFFFFLFFFTRFFRFFFD");
        System.out.println("\n\n Test Case 3: \n Map: sample_floor_map3.txt \n Input: RRFFFLFFFFLFFFRFTFRFFFLFFFFFFFFFFLFFFD \n " +
                "Expected Output: Successfully picked up the pod and dropped it off. Thank you!");
        remoteControl.run("sample_floor_map3.txt", "RRFFFLFFFFLFFFRFTFRFFFLFFFFFFFFFFLFFFD");
    }

    /**
     * Test 4 checks for extraneous commands after drop condition.
     *
     * Map: sample_floor_map1.txt
     * Input: <pre>RFFFFFTFFFFFFFDR</pre>
     * Expected Output: <pre>I'm sorry. The Kiva Robot did not pick up the pod and then drop it off correctly</pre>
     */
    public static void testMoveAfterDrop(){
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case 4: \n Map: sample_floor_map1.txt \n Input: RFFFFFTFFFFFFFDR \n " +
                "Expected Output: I'm sorry. The Kiva Robot did not pick up the pod and then drop it off correctly, \n" +
                " (because dropping the pod at the drop zone was not the last command.)\n");
        remoteControl.run("sample_floor_map1.txt", "RFFFFFTFFFFFFFDR");
    }

    /**
     * Checks for various failures that do not throw exceptions.
     *
     * Test 5 checks for single command followed by return.
     * Test 6 checks for successful take without drop
     *
     */
    public static void testFails(){
        testMoveAfterDrop();
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case 5: \n Map: sample_floor_map3.txt \n Input: R \n " +
                "Expected Output: I'm sorry. The Kiva Robot did not pick up the pod and then drop it off correctly \n");
        remoteControl.run("sample_floor_map3.txt", "R");
        System.out.println("\n\n Test Case 6: \n Map: sample_floor_map3.txt \n Input: RRFFFLFFFFLFFFRFT \n " +
                "Expected Output: I'm sorry. The Kiva Robot did not pick up the pod and then drop it off correctly \n");
        remoteControl.run("sample_floor_map3.txt", "RRFFFLFFFFLFFFRFT");
    }

    /**
     * Test 7 checks for attempt to take pod at an empty location.
     *
     * Map: sample_floor_map2.txt
     * Input: RFFFFFLFFFTRFFRFFFD
     * Expected Output: Exception in thread "main" NoPodException: The kiva attempted to TAKE a POD while there is no POD at location (8,1)
     */
    public static void testNoPodException(){
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case 7: \n Map: sample_floor_map2.txt \n Input: RFFFFFLFFFTRFFRFFFD \n " +
                "Expected Output: Exception in thread \"main\" NoPodException: The kiva attempted to TAKE a POD while there is no POD at location (8,1) \n");
        remoteControl.run("sample_floor_map2.txt", "RFFFFFLFFFTRFFRFFFD");
    }

    /**
     * Test 8 checks for attempt to take drop a pod outside a drop zone.
     *
     * Map: sample_floor_map2.txt
     * Input: RFFFFFFLFFFTRFFRFFD
     * Expected Output: Exception in thread "main" IllegalDropZoneException: The kiva attempted to DROP not on a DROP_ZONE at location (11,3)
     */
    public static void testIllegalDropZoneException(){
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case 8: \n Map: sample_floor_map2.txt \n Input: RFFFFFFLFFFTRFFRFFD \n " +
                "Expected Output: Exception in thread \"main\" IllegalDropZoneException: The kiva attempted to DROP not on a DROP_ZONE at location (11,3) \n");
        remoteControl.run("sample_floor_map2.txt", "RFFFFFFLFFFTRFFRFFD");
    }

    /**
     * Test 9 test for a simple Kiva collision into an obstacle.
     *
     * Map: sample_floor_map1.txt
     * Input: F
     * Expected Output: Exception in thread "main" IllegalMoveException: The kiva ran into an obstacle at location (1,0).
     */
    public static void testObstacleException(){
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case 9: \n Map: sample_floor_map1.txt \n Input: F \n " +
                "Expected Output: Exception in thread \"main\" IllegalMoveException: The kiva ran into an obstacle at location (1,0). \n");
        remoteControl.run("sample_floor_map1.txt", "F");
    }

    /**
     * This method performs two tests.
     * This method was used for initial testing of the RemoteControl class only.
     * Map files must be in the Default Package source directory.
     *
     * Input: <pre>RFFFFFTRF</pre>
     * Expected Output: <pre>{ TURN_RIGHT, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, TAKE, TURN_RIGHT, FORWARD }</pre>
     *
     * Input: <pre>RFFFFFTRB</pre>
     * Expected Output: <pre>Expected Output: Exception in thread "main" kivaworld.InvalidKivaCommandException: Invalid Kiva Command: B</pre>
     */
    public static void testCommands(){
        String testMapString ="" +
                        "-----\n" +
                        "|PD |\n" +
                        "| * |\n" +
                        "| * |\n" +
                        "|K* |\n" +
                        "-----";
        FloorMap testMap = new FloorMap(testMapString);
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("\n\n Test Case 1: \n Map: custom testMap \n Input: FFFTRF \n " +
                "Expected Output: FORWARD, FORWARD, FORWARD, TAKE, TURN_RIGHT, FORWARD");
        String directions = "FFFTRF";
        remoteControl.run(testMap,directions);
        System.out.println("\n\n Test Case 2: \nMap: sample_floor_map1.txt \n Input: RFFFFFTRB \n " +
                "Expected Output: Exception in thread \"main\" InvalidKivaCommandException: Invalid Kiva Command: B");
        String mapFileName = "sample_floor_map1.txt";
        directions = "RFFFFFTRB";
        remoteControl.run(mapFileName,directions);
    }
}
