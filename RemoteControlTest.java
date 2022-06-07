public class RemoteControlTest {
    public static void main(String[] args) {
        testCommands();
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
     * Expected Output: <pre>Expected Output: Exception in thread “main” kivaworld.InvalidKivaCommandException: Invalid Kiva Command: B</pre>
     */
    public static void testCommands(){
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("Test Case 1: \nMap: sample_floor_map1.txt \n Input: RFFFFFTRF \n " +
                "Expected Output: TURN_RIGHT, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, TAKE, TURN_RIGHT, FORWARD");
        String mapFileName = "sample_floor_map1.txt";
        String directions = "RFFFFFTRF";
        remoteControl.run(mapFileName,directions);
        System.out.println("Test Case 2: \nMap: sample_floor_map1.txt \n Input: RFFFFFTRB \n " +
                "Expected Output: Exception in thread \"main\" InvalidKivaCommandException: Invalid Kiva Command: B");
        directions = "RFFFFFTRB";
        remoteControl.run(mapFileName,directions);
    }
}
