import edu.duke.FileResource;
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
        keyboardResource = new KeyboardResource();
    }

    public static List<KivaCommand> convertToKivaCommands(String directions) {
        char[] kivaCommandCharacters = directions.toCharArray();
        for (int i = 0;i < kivaCommandCharacters.length; i++) {
            switch(kivaCommandCharacters[i]){
                case 'F' :
                    kivaCommands.add(KivaCommand.FORWARD);
                    break;
                case 'R' :
                    kivaCommands.add(KivaCommand.TURN_RIGHT);
                    break;
                case 'L' :
                    kivaCommands.add(KivaCommand.TURN_LEFT);
                    break;
                case 'T' :
                    kivaCommands.add(KivaCommand.TAKE);
                    break;
                case 'D' :
                    kivaCommands.add(KivaCommand.DROP);
                    break;
            }
        }
        return kivaCommands;
    }

    /**
     * The controller that directs Kiva's activity. Prompts the user for the floor map
     * to load, displays the map, and asks the user for the commands for Kiva to execute.
     *
     * [Here's the method you'll execute from within BlueJ. It may or may not run successfully
     * as-is, but you'll definitely need to add more to complete the project.]
     */
    public void run() {
        System.out.println("Please select a map file.");
        FileResource mapFile = new FileResource();
        String inputMap = mapFile.asString();
        FloorMap floorMap = new FloorMap(inputMap);
        System.out.println(floorMap);
        kiva = new Kiva(floorMap);
        System.out.println("Please enter the directions for the Kiva Robot to take.");
        String directions = keyboardResource.getLine();
        System.out.println("Directions that you typed in: " + directions);
        kivaCommands = convertToKivaCommands(directions.toUpperCase());
        for (int i = 0; i < directions.length(); i++) {
            kiva.move(kivaCommands.get(i));
        }
    }
}
