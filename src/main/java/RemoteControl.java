import edu.duke.FileResource;
import solver.MapResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

/**
 * This is the class that controls Kiva's actions.
 * The user may select a map file and issue a string of commands to the Kiva to move the Kiva to the pod.
 * Then the Kiva can take on the pod, move to the drop zone, and drop the pod.
 *
 * The map file should be in the default package source directory.
 *
 * Example usage:
 * <pre>
 * Please select a map file.
 * -- sample_floor_map2.txt
 * Please enter the directions for the Kiva Robot to take.
 * -- RFFFFFFLFFFTRFFRFFFD
 * ---------------
 * |        P   *|
 * |   **       *|
 * |   **       *|
 * | *K       D *|
 * ---------------
 * Commands you sent to the Kiva: [TURN_RIGHT, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, FORWARD, TURN_LEFT,
 * FORWARD, FORWARD, FORWARD, TAKE, TURN_RIGHT, FORWARD, FORWARD, TURN_RIGHT, FORWARD, FORWARD, FORWARD, DROP]
 * Successfully picked up the pod and dropped it off. Thank you!
 * </pre>
 *
 * @author Stephan Peters (peterstz)
 * @version 20220613.2330
 */
public class RemoteControl {
    private final boolean duke = true;
    private static List<KivaCommand> kivaCommands = new LinkedList<>();

    /**
     * Build a new RemoteControl.
     */
    public RemoteControl() {
    }

    /**
     * Converts a string of one character Kiva commands to a list of executable Kiva commands.
     *
     * @param directions a string of single character Kiva commands for the Kiva to perform. Example: <pre>RFFFFFTRF</pre>
     * @return Returns a linked list of kiva commands that can be executed by the Kiva one at a time.
     */
    public static List<KivaCommand> convertToKivaCommands(String directions) {
        kivaCommands.clear(); // clears previous kivaCommands from list
        char[] kivaCommandCharacters = directions.toCharArray();
        for (char kivaCommandCharacter : kivaCommandCharacters) {
            switch (kivaCommandCharacter) {
                case 'F':
                    kivaCommands.add(KivaCommand.FORWARD);
                    break;
                case 'R':
                    kivaCommands.add(KivaCommand.TURN_RIGHT);
                    break;
                case 'L':
                    kivaCommands.add(KivaCommand.TURN_LEFT);
                    break;
                case 'T':
                    kivaCommands.add(KivaCommand.TAKE);
                    break;
                case 'D':
                    kivaCommands.add(KivaCommand.DROP);
                    break;
                default:
                    throw new InvalidKivaCommandException("Invalid Kiva Command: \"" + kivaCommandCharacter + "\"");
            }
        }
        return kivaCommands;
    }

    /**
     * Runs a Kiva
     * In overloaded methods, prompts the user to select a map file and a string of single character Kiva commands.
     */
    public void run() throws FileNotFoundException {
        // in case solver.MapResource is not accepted 
        // and project requres edu.duke.FileResource
        if(duke){
            FileResource fileResource = new FileResource();
            String mapString = fileResource.asString();
            FloorMap floorMap = new FloorMap(mapString);
            run(floorMap);
        } else {
            File mapFile = MapResource.selectMap();
            run(mapFile);
        }
    }

    /**
     * Runs a kiva using a FloorMap object and a string of single character Kiva commands.
     * The FloorMap file can be created from a string in an external method calling remoteControl.run().
     * This method will not open a map file.
     *
     * Commands:
     * <pre>
     * F = FORWARD
     * R = TURN_RIGHT
     * L = TURN_LEFT
     * T = TAKE (a pod)
     * D = DROP (a pod)
     * </pre>
     *
     * Sample command string:
     * <pre>RFFFFFTRF</pre>
     *
     * @param mapFileName The filename of the map file
     * @param directions a string of single character Kiva commands for the Kiva to perform.
     *
     * @see FloorMap
     */
    public void run(String mapFileName, String directions) throws FileNotFoundException {
        String mapString;
        // in case solver.MapResource is not accepted
        // and project requres edu.duke.FileResource
        if(duke){
            FileResource fileResource = new FileResource(); 
            mapString = fileResource.asString();
        } else {
            File mapFile = MapResource.getFile(mapFileName);
            mapString = MapResource.getMapString(mapFile);
        }
        FloorMap floorMap = new FloorMap(mapString);
        run(floorMap, directions);
    }

    /**
     * Runs a kiva using a map file.
     * In overloaded methods, for string of single character Kiva commands.
     * The map file must be in the Default Package source directory and is inputted as a file name String.
     * The method will open and read the file, then convert it to a FloorMap object.
     * @param mapFile the map File to be read.
     */
    public void run(File mapFile) throws FileNotFoundException {
        String mapString;
        // in case solver.MapResource is not accepted 
        // and project requres edu.duke.FileResource
        if(duke){
            FileResource fileResource = new FileResource(); 
            mapString = fileResource.asString();
        } else {
            mapString = MapResource.getMapString(mapFile);
        }
        FloorMap floorMap = new FloorMap(mapString);
        run(floorMap);
    }

    /**
     * Runs a kiva using a FloorMap object.
     * In overloaded methods, for string of single character Kiva commands.
     * The FloorMap file can be created from a string in an external method calling remoteControl.run().
     * This method will not open a map file.
     *
     * @param floorMap a FloorMap object
     *
     * @see FloorMap
     */
    public void run(FloorMap floorMap){
        System.out.println(floorMap);
        System.out.println("Please enter the directions for the Kiva Robot to take.");
        KeyboardResource keyboardResource = new KeyboardResource();
        String directions = keyboardResource.getLine();
        run(floorMap, directions);
    }

    /**
     * Runs a kiva using a FloorMap object and a string of single character Kiva commands.
     * The FloorMap file can be created from a string in an external method calling remoteControl.run().
     * This method will not open a map file.
     *
     * Commands:
     * <pre>
     * F = FORWARD
     * R = TURN_RIGHT
     * L = TURN_LEFT
     * T = TAKE (a pod)
     * D = DROP (a pod)
     * </pre>
     *
     * Sample command string:
     * <pre>RFFFFFTRF</pre>
     *
     * @param floorMap a FloorMap object
     * @param directions a string of single character Kiva commands for the Kiva to perform.
     *
     * @see FloorMap
     */
    public void run(FloorMap floorMap, String directions){
        Kiva kiva = new Kiva(floorMap);
        kivaCommands = convertToKivaCommands(directions.toUpperCase());
        System.out.println("Commands you sent to the Kiva: " + kivaCommands);
        for (int i = 0; i < directions.length(); i++) {
            kiva.move(kivaCommands.get(i));
        }
        if (kiva.successfullyDropped && kivaCommands.get(directions.length()-1) == KivaCommand.DROP){
            System.out.println("Successfully picked up the pod and dropped it off. Thank you!\n");
        } else {
            System.out.println("I'm sorry. The Kiva Robot did not pick up the pod and then drop it off correctly.\n");
        }
    }

    /**
     * Runs the RemoteControl.run() method.
     */
    public static void main(String[] args) throws FileNotFoundException {
        RemoteControl remotecontrol = new RemoteControl();
        remotecontrol.run();
    }
}

