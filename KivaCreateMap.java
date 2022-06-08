import java.util.LinkedList;
import java.util.Random;
import edu.duke.Point;

/**
 * Creates a Kiva FloorMap
 * This can be a random map, or a default map as a string or FloorMap
 *
 * @author Stephan Peters
 * @version 20200608.2112
 */
public class KivaCreateMap
{
    // instance variables - replace the example below with your own
    private final FacingDirection[] facingDirections = FacingDirection.values();
    private FacingDirection facingDirection = FacingDirection.UP;
    Random random = new Random();
    private LinkedList<Point> obstacles = new LinkedList<>();

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
     * Returns a default map as a KivaWorld FloorMap
     *
     * @return default map as a FloorMap
     */
    public FloorMap defaultMap(){
        FloorMap defaultMap = new FloorMap(defaultMapString());
        return defaultMap;
    }

    /**
     * Create a map String that can be used with FloorMap
     *
     * @param mapWidth Width (x, col) of the map.
     * @param mapHeight Height (y, row) of the map.
     * @return The generated map in String format.
     */
    public String randomMap(int mapWidth, int mapHeight) {
        StringBuilder mapFrame = new StringBuilder();
        int mapArea = mapHeight * mapWidth;

        //Create PKD (Philip K. Dick) Points
        Point pod = new Point(random.nextInt(mapHeight-3)+1, random.nextInt(mapWidth-2)+1);
        Point kiva = new Point(random.nextInt(mapHeight-3)+1, random.nextInt(mapWidth-2)+1);
        Point drop = new Point(random.nextInt(mapHeight-3)+1, random.nextInt(mapWidth-2)+1);

        // Create obstacles over 17% of map area
        for (int obstaclesLeft = (int) (mapArea * 17) / 100; obstaclesLeft > 0;) {

            // Randomly decide in which direction to build an obstacle wall.
            facingDirection = facingDirections[random.nextInt(facingDirections.length)];
            int obstacleLength = 1;
            // Randomly decide how long the wall will be.
            if(facingDirection == FacingDirection.UP || facingDirection == FacingDirection.DOWN) {
                obstacleLength = random.nextInt(mapHeight-5)+1;
            }
            if(facingDirection == FacingDirection.LEFT || facingDirection == FacingDirection.RIGHT) {
                obstacleLength = random.nextInt(mapWidth-4)+1;
            }

            // Create anchor point for wall and add it to obstacles
            Point obstacle = new Point(random.nextInt(mapHeight-3)+1, random.nextInt(mapWidth-2)+1);
            obstacles.add(obstacle);
            obstaclesLeft--;

            // Create the obstacle wall
            if (obstacleLength > 1) {
                for (int i = 1; i < obstacleLength; i++) {
                    // Create the next point in the wall in the current facing direction
                    obstacle = new Point (obstacles.getLast().getX() + facingDirection.getDelta().getX(),
                                    obstacles.getLast().getY() + facingDirection.getDelta().getY());
                    // Add the new point to the wall if it is within the map walls
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
                        mapFrame.append("-\n");
                    } else {
                        mapFrame.append("-");
                    }
                } else if (col == 0) {
                    mapFrame.append("|");
                } else if (col == mapWidth-1) {
                    mapFrame.append("|\n");
                } else {
                    mapFrame.append(" ");
                }

                Point currentPoint = new Point(row, col);

                // Insert obstacles
                for (int i = 0; i < obstacles.size()-1; i++) {
                    if (row == obstacles.get(i).getX()+1 && col == obstacles.get(i).getY()){
                        mapFrame.replace(mapFrame.length()-1, mapFrame.length(), "*");
                        obstacles.remove(i);
                    }
                }

                // Insert the PKD into the map NOTE: can create an invalid map if any of these share the same point.
                if (row == pod.getX()+1 && col == pod.getY()) {
                    mapFrame.replace(mapFrame.length()-1, mapFrame.length(), "P");
                }
                if (row == kiva.getX()+1 && col == kiva.getY()){
                    mapFrame.replace(mapFrame.length()-1, mapFrame.length(), "K");
                }
                if (row == drop.getX()+1 && col == drop.getY()) {
                    mapFrame.replace(mapFrame.length()-1, mapFrame.length(), "D");
                }
            }
        }
        return String.valueOf(mapFrame);
    }
    /**
     * Create a map String that can be used with FloorMap
     *
     * @return The generated map in String format.
     */
    public String randomMap(){
        int mapWidth = random.nextInt(15) + 10;
        int mapHeight = random.nextInt(5) + 10;
        return randomMap(mapWidth, mapHeight);
    }

    public static void main(String[] args) {
        KivaCreateMap kivaCreateMap = new KivaCreateMap();
        System.out.println(kivaCreateMap.randomMap());
    }
}
