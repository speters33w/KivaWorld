import solver.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Creates a Kiva FloorMap
 * This can be a random map, or a default map as a string or FloorMap
 *
 * @author Stephan Peters (peterstz)
 * @version 20220613.0900
 */
public class KivaCreateMap
{
    private final FacingDirection[] directions = FacingDirection.values();
    Random random = new Random();
    private final LinkedList<Point> obstacles = new LinkedList<>();

    /**
     * public static default floor map, returns as a String
     * Can be accessed to print the default map, or whatever.
     *
     * @return default map as a string
     */
    public static String defaultMapString(){
        return ""
                + "-------------\n"
                + "        P   *\n"
                + "   **       *\n"
                + "   **       *\n"
                + "  K       D *\n"
                + " * * * * * **\n"
                + "-------------\n";
    }

    /**
     * Create a random map (String) that can be used with FloorMap
     *
     * @param mapWidth Width (x, col) of the map.
     * @param mapHeight Height (y, row) of the map.
     * @return The generated map in String format.
     */
    public String randomMapString(int mapWidth, int mapHeight) {
        Point pod = new Point();
        Point kiva = new Point();
        Point drop = new Point();

        //Create PKD (Philip K. Dick) Points and ensure none are the same.
        do {
            pod.move(random.nextInt(mapWidth - 2) + 1,random.nextInt(mapHeight - 3) + 1);
            kiva.move(random.nextInt(mapWidth - 2) + 1, random.nextInt(mapHeight - 3) + 1);
            drop.move(random.nextInt(mapWidth - 2) + 1, random.nextInt(mapHeight - 3) + 1);
        } while (pod.equals(kiva) || drop.equals(pod) || kiva.equals(drop));

        /* todo put obstacle generator and following into a do while loop that checks with solver.BFSMazeSolver
         * and determine if map is solvable, regenerate obstacles if not.
         * do {
         * // <generate obstacles and map>
         * } while (!unsolvable);
         */

        // Create obstacles over a random % from 15 to 20% of usable map area
        for (int obstaclesLeft = ((mapWidth-2) * (mapHeight-2) * (random.nextInt(10)+15)) / 100; obstaclesLeft > 0;) {

            // Randomly decide in which direction to build an obstacle wall.
            FacingDirection direction = directions[random.nextInt(directions.length)];
            int obstacleLength = 1;

            // Randomly decide how long the wall will be.
            if(direction == FacingDirection.UP || direction == FacingDirection.DOWN) {
                obstacleLength = random.nextInt(mapHeight-2)+1;
            }
            if(direction == FacingDirection.LEFT || direction == FacingDirection.RIGHT) {
                obstacleLength = random.nextInt(mapWidth-2)+1;
            }

            // Create anchor Point for wall and add it to obstacles
            Point obstacle = new Point(random.nextInt(mapHeight-2)+1, random.nextInt(mapWidth-2)+1);
            obstacles.add(obstacle);
            obstaclesLeft--;

            // Create an obstacle wall from the anchor point
            if (obstacleLength > 1) {
                for (int i = 1; i < obstacleLength; i++) {
                    // Create the next Point in the wall in the current facing direction
                    edu.duke.Point getDelta = direction.getDelta();
                    obstacle = obstacle.moveBy(getDelta.getX(),getDelta.getY());
                    // Add the new Point to the wall if it is within the map walls
                    if ((obstacle.getX() >= 0) && (obstacle.getX() < mapWidth)
                            && (obstacle.getY() > 0)&& (obstacle.getY() < mapHeight)){
                        obstacles.add(obstacle);
                        obstaclesLeft--;
                    }
                }
            }
            //System.out.println(obstacles);
        }


        // Create the basic map frame
        System.out.println("Width = " + mapWidth + " Height = " + mapHeight);

        StringBuilder mapFloor = new StringBuilder();
        for (int row = 0; row < mapHeight; row++) {
            for (int col = 0; col < mapWidth; col++) {
                if(row == 0 || row == mapHeight-1) {
                    if(col == mapWidth-1) {
                        mapFloor.append("-\n");
                    } else {
                        mapFloor.append("-");
                    }
                } else if (col == 0) {
                    mapFloor.append("|");
                } else if (col == mapWidth-1) {
                    mapFloor.append("|\n");
                } else {
                    mapFloor.append(" ");
                }

                // Insert obstacles
                for (int i = 0; i < obstacles.size()-1; i++) {
                    if (row == obstacles.get(i).getX()+1 && col == obstacles.get(i).getY()){
                        if(row != mapHeight-1) {
                            if (mapFloor.charAt(mapFloor.length() - 1) != '\n') {
                                mapFloor.replace(mapFloor.length() - 1, mapFloor.length(), "*");
                            }
                        }
                        obstacles.remove(i);
                    }
                }

                // Insert the PKD into the map
                if (row == pod.getY()+1 && col == pod.getX()) {
                    mapFloor.replace(mapFloor.length()-1, mapFloor.length(), "P");
                }
                if (row == kiva.getY()+1 && col == kiva.getX()){
                    mapFloor.replace(mapFloor.length()-1, mapFloor.length(), "K");
                }
                if (row == drop.getY()+1 && col == drop.getX()) {
                    mapFloor.replace(mapFloor.length()-1, mapFloor.length(), "D");
                }
            }
        }

        return String.valueOf(mapFloor);
    }
    /**
     * Create a random map (String) that can be used with FloorMap
     *
     * @return The generated map in String format.
     */
    public String randomMapString(){
        int mapWidth = random.nextInt(15) + 10;
        int mapHeight = random.nextInt(5) + 10;
        return randomMapString(mapWidth, mapHeight);
    }

    /**
     * Returns a default map as a KivaWorld FloorMap
     *
     * @return default map as a FloorMap
     */
    public FloorMap defaultMap(){
        return new FloorMap(defaultMapString());
    }

    /**
     * Returns a random map as a KivaWorld FloorMap
     *
     * @return random map as a FloorMap
     */
    public FloorMap randomMap(){
        return new FloorMap(randomMapString());
    }

    /**
     * Opens a JFileChooser save dialog and allows the user to save a String to a file.
     *
     * @param map String to be saved to the file
     * @throws IOException if file can not be saved or written to.
     */
    void saveFile(String map) throws IOException {
        File path;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("FloorMap Files", "txt", "map", "maz", "maze", "fm", "FloorMap");
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setSelectedFile(new File("random_floor_map.txt"));
        int option = fileChooser.showSaveDialog(null);
        fileChooser.setVisible(true);
        if(option == JFileChooser.APPROVE_OPTION){
            path = new File(fileChooser.getSelectedFile().getAbsolutePath());
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(map);
            fileWriter.flush();
            fileWriter.close();
            System.out.println("File saved.");
        }else {
            System.out.println("Save canceled");
        }
    }

    /**
     * Returns true if input string begins with a Y or y.
     * Y, y, yes, yepper, yup, yak and yoyo will all return true.
     *
     * @param yesNo String input from user
     * @return true if input begins with a Y or y
     */
    public boolean yes(String yesNo){
        return yesNo.toUpperCase().charAt(0) == 'Y';
    }

    public static void main(String[] args) throws IOException {
        KivaCreateMap kivaCreateMap = new KivaCreateMap();
        String map = kivaCreateMap.randomMapString();
        System.out.println(map);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Save this map? (y/n)");
        if(kivaCreateMap.yes(scanner.nextLine())){
            kivaCreateMap.saveFile(map);
        }
        scanner.close();
    }
}
