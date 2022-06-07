import java.util.Random;
import java.util.StringJoiner;
import kivaworld.*;

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
    public String createMap(int mapWidth, int mapHeight) { //todo finish random map generator
        //obstruction = "*" 17% should be *
        //kiva = "K"
        //pod = "P"
        //dropzone = "D"
        StringJoiner createdMap = new StringJoiner("");
        //create upper wall
        for(int i=0;(i<=mapWidth-1);i++){
            createdMap.add("-");
        }
        createdMap.add("\n|");
        for(int i=1;(i<=mapHeight);i++){
            for (int j=1;(j<=mapWidth-2);j++){
                //create string "|" + random + "|\n"
            }
        }
        return String.valueOf(createdMap);
        //write the string to file using FileResource;
    }

    public static void main(String[] args) {
        int mapWidth = 20;
        int mapHeight = 10;
        KivaCreateMap testCreateMap = new KivaCreateMap();
        Random random = new Random();
            mapWidth = random.nextInt(15) + 10;
            mapHeight = random.nextInt(5) + 10;
            System.out.println("Width = " + mapWidth + " Height = " + mapHeight);
        for (int i = 0; i < mapHeight; i++) {

        }
    }
}
