import java.util.Random;
import edu.duke.Point;

/**
 * Creates a Kiva FloorMap
 * This can be a random map, or a default map as a string or FloorMap
 *
 * @author Stephan Peters
 * @version 20200607.1700
 */
public class KivaCreateMap
{
    // instance variables - replace the example below with your own
    private boolean isKivaPlaced = false;
    private boolean isPodPlaced = false;
    private boolean isDopzonePlaced = false;

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
    public String randomMap(int mapWidth, int mapHeight) { //todo finish random map generator
        Random random = new Random();
        Point kiva = new Point(random.nextInt(mapHeight-3)+1, random.nextInt(mapWidth-2)+1);
        Point pod = new Point(random.nextInt(mapHeight-3)+1, random.nextInt(mapWidth-2)+1);
        Point drop = new Point(random.nextInt(mapHeight-3)+1, random.nextInt(mapWidth-2)+1);
        StringBuilder mapFrame = new StringBuilder();
        System.out.println("Width = " + mapWidth + " Height = " + mapHeight);
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
                    } else if (random.nextInt(100)+1 <= 15){ //todo obstacles should be a little less random
                    mapFrame.append("*");
                } else {
                    mapFrame.append(" ");
                }
                if (row == kiva.getX()+1 && col == kiva.getY()){
                    mapFrame.replace(mapFrame.length()-1, mapFrame.length(),"K");
                }
                if (row == pod.getX()+1 && col == pod.getY()) {
                    mapFrame.replace(mapFrame.length() - 1, mapFrame.length(), "P");
                }
                if (row == drop.getX()+1 && col == drop.getY()) {
                    mapFrame.replace(mapFrame.length() - 1, mapFrame.length(), "D");
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
        Random random = new Random();
        int mapWidth = random.nextInt(15) + 10;
        int mapHeight = random.nextInt(5) + 10;
        return randomMap(mapWidth, mapHeight);
    }

    public static void main(String[] args) {
        KivaCreateMap kivaCreateMap = new KivaCreateMap();
        System.out.println(kivaCreateMap.randomMap());
    }
}
