import edu.duke.FileResource;
import kivaworld.InvalidKivaCommandException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * This is the class that controls Kiva's actions. Implement the <code>run()</code>
 * method to deliver the pod and avoid the obstacles.
 *
 * This is starter code that may or may not work. You will need to update the code to
 * complete the project.
 */
public class RemoteControl {
    private KeyboardResource keyboardResource;
    private Kiva kiva;
    private static List<KivaCommand> kivaCommands = new LinkedList<KivaCommand>();

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
        for (int i = 0; i < kivaCommandCharacters.length; i++) {
            switch (kivaCommandCharacters[i]) {
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
                    throw new InvalidKivaCommandException("Invalid Kiva Command: \"" + String.valueOf(kivaCommandCharacters[i]) + "\"");
            }
        }
        return kivaCommands;
    }

    /**
     * Runs a kiva using a user selected map file and a string of single character Kiva commands.
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
     */
    public void run() {
        System.out.println("Please select a map file.");
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("FloorMap Files", "txt", "map", "maz", "maze", "fm", "FloorMap");
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.showDialog(null,"Select");
        fileChooser.setVisible(true);
        String mapFileName = fileChooser.getName(fileChooser.getSelectedFile());
        System.out.println("Please enter the directions for the Kiva Robot to take.");
        String directions = keyboardResource.getLine();
        run(mapFileName,directions);
    }

    /**
     * Runs a kiva using a map file and a string of single character Kiva commands.
     * The map file must be in the Default Package source directory and is inputted as a file name String.
     * The method will open and read the file, then convert it to a FloorMap object.
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
     * @param mapFileName String, the relative filename of the map file.
     * @param directions a string of single character Kiva commands for the Kiva to perform.
     */
    public void run(String mapFileName, String directions) {
        FileResource mapFile = new FileResource(mapFileName);
        String inputMap = mapFile.asString();
        FloorMap floorMap = new FloorMap(inputMap);
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
        System.out.println(floorMap);
        kiva = new Kiva(floorMap);
        kivaCommands = convertToKivaCommands(directions.toUpperCase());
        System.out.println("Commands you sent to the Kiva: " + kivaCommands);
        for (int i = 0; i < directions.length(); i++) {
            kiva.move(kivaCommands.get(i));
        }
        if (kiva.successfullyDropped && kivaCommands.get(directions.length()-1) == KivaCommand.DROP){
            System.out.println("Successfully picked up the pod and dropped it off. Thank you!");
        } else {
            System.out.println("I'm sorry. The Kiva Robot did not pick up the pod and then drop it off correctly.");
        }
    }
}

