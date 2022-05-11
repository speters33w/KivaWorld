import java.util.Random;
import java.util.StringJoiner;

/**
 * Creates a Kiva FloorMap
 * This can be a random map, or a default map as a string or FloorMap
 *
 * @author Stephan Peters
 * @version (0.1)
 */
public class KivaCreateMap
{
    // instance variables - replace the example below with your own
    private boolean isKivaPlaced = false;
    private boolean isPodPlaced = false;
    private boolean isDopzonePlaced = false;
    private int mapWidth = 20;
    private int mapHeight = 10;

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
     * create randomized map
     */
    public StringJoiner createMap(int mapWidth, int mapHeight) { //todo finish random map
        //obstruction = "*" 17% should be *
        //kiva = "K"
        //pod = "P"
        //dropzone = "D"
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
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
        return createdMap;
        //write the string to file using FileResource;
    }

    public static void main(String[] args) {
        KivaCreateMap testCreateMap = new KivaCreateMap();
        Random testRandom = new Random();
        int min = 5;
        int max = 10;
        System.out.println("0        1         2");
        System.out.println("12345678901234567890");
        System.out.println(testCreateMap.createMap
                (testRandom.nextInt(10) + 10,
                        testRandom.nextInt(max - min + 1) + min).toString());

    }
}
