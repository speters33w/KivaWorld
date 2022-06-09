package solver;

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
 * @author Stephan Peters
 * @version 20200608.2330
 */
public class KivaCreateMap
{
    // instance variables - replace the example below with your own
    private final CardinalDirection[] cardinalDirection = CardinalDirection.values();
    Random random = new Random();
    private final LinkedList<Coordinate> obstacles = new LinkedList<>();

//    /**
//     * Returns a default map as a KivaWorld FloorMap
//     *
//     * @return default map as a FloorMap
//     */
//    public FloorMap defaultMap(){
//        FloorMap defaultMap = new FloorMap(defaultMapString());
//        return defaultMap;
//    }
//
//    /**
//     * Returns a random map as a KivaWorld FloorMap
//     *
//     * @return random map as a FloorMap
//     */
//    public FloorMap randomMap(){
//        FloorMap randomFloorMap = new FloorMap(randomMapString());
//        return randomFloorMap;
//    }

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
        StringBuilder mapFloor = new StringBuilder();
        int mapArea = mapHeight * mapWidth;

        //Create PKD (Philip K. Dick) Coordinates
        Coordinate pod = new Coordinate(random.nextInt(mapHeight-3)+1, random.nextInt(mapWidth-2)+1);
        Coordinate kiva = new Coordinate(random.nextInt(mapHeight-3)+1, random.nextInt(mapWidth-2)+1);
        Coordinate drop = new Coordinate(random.nextInt(mapHeight-3)+1, random.nextInt(mapWidth-2)+1);

        // Create obstacles over 17% of map area
        for (int obstaclesLeft = (mapArea * 17) / 100; obstaclesLeft > 0;) {

            // Randomly decide in which direction to build an obstacle wall.
            CardinalDirection facingDirection = cardinalDirection[random.nextInt(cardinalDirection.length)];
            int obstacleLength = 1;
            // Randomly decide how long the wall will be.
            if(facingDirection == CardinalDirection.NORTH || facingDirection == CardinalDirection.SOUTH) {
                obstacleLength = random.nextInt(mapHeight-5)+1;
            }
            if(facingDirection == CardinalDirection.WEST || facingDirection == CardinalDirection.EAST) {
                obstacleLength = random.nextInt(mapWidth-4)+1;
            }

            // Create anchor Coordinate for wall and add it to obstacles
            Coordinate obstacle = new Coordinate(random.nextInt(mapHeight-3)+1, random.nextInt(mapWidth-2)+1);
            obstacles.add(obstacle);
            obstaclesLeft--;

            // Create the obstacle wall
            if (obstacleLength > 1) {
                for (int i = 1; i < obstacleLength; i++) {
                    // Create the next Coordinate in the wall in the current facing direction //todo edit to Coordinate.translate
                    obstacle = new Coordinate (obstacles.getLast().getX() + facingDirection.getDelta().getX(),
                                    obstacles.getLast().getY() + facingDirection.getDelta().getY());
                    // Add the new Coordinate to the wall if it is within the map walls
                    if ((obstacle.getX() >= 0) && (obstacle.getX() < mapWidth)
                            && (obstacle.getY() > 0)&& (obstacle.getY() < mapHeight)){
                        obstacles.add(obstacle);
                        obstaclesLeft--;
                    }
                }
            }
            //System.out.println(obstacles);
        }

        // Create frame, add obstacles and PKD
        System.out.println("Width = " + mapWidth + " Height = " + mapHeight);

        // Create the basic map frame
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

                // Insert the PKD into the map NOTE: can create an invalid map if any of these share the same Coordinate.
                if (row == pod.getX()+1 && col == pod.getY()) {
                    mapFloor.replace(mapFloor.length()-1, mapFloor.length(), "P");
                }
                if (row == kiva.getX()+1 && col == kiva.getY()){
                    mapFloor.replace(mapFloor.length()-1, mapFloor.length(), "K");
                }
                if (row == drop.getX()+1 && col == drop.getY()) {
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
     * Opens a JFileChooser save dialog and allows the user to save a String to a file.
     *
     * @param map String to be saved to the file
     * @throws IOException if file can not be saved or written to.
     */
    void saveFile(String map) throws IOException {
        File path = null;
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
