package solver;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Utility to open and read Kiva map files.
 *
 * @author StephanPeters (peterstz)
 * @version 20220614.1830
 */
public class MapResource {

    /**
     * Opens a GUI file selector dialog in the source directory where the user can select a Maze or FloorMap file.
     * Restricts extensions to text files (*.txt).
     *
     * @return java.io.file the selected file.
     *
     * @throws NullPointerException if user cancels the file open operation.
     */
    public static String getFileName(){
        System.out.println("Please select a map file.");
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("FloorMap Files", "txt", "map", "maz", "maze", "fm", "FloorMap");
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.showDialog(null,"Select");
        fileChooser.setVisible(true);
        return fileChooser.getName(fileChooser.getSelectedFile());
    }

    /**
     * Returns a java.io.File resource given a filename.
     * @param fileName The filename of the file.
     * @return the file.
     */
    public static File getFile(String fileName){
        return new File(fileName);
    }

    /**
     * Opens a GUI file selector dialog in the source directory where the user can select a Maze or FloorMap file.
     * Restricts extensions to text files (*.txt).
     *
     * @return java.io.file the selected file.
     *
     * @throws NullPointerException (in main) if user cancels the file open operation.
     */
    public static File selectMap(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("FloorMap Files", "txt", "map", "maz", "maze", "fm", "FloorMap");
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.showDialog(null,"Select");
        fileChooser.setVisible(true);
        return fileChooser.getSelectedFile();
    }

    /**
     * Reads a map File and returns the map as a String.
     *
     * @param map the map File to be read.
     * @return the map File as a String.
     *
     * @throws FileNotFoundException - if File can not be read.
     */
    public static String getMapString(File map) throws FileNotFoundException {
        StringBuilder mapString = new StringBuilder();
        try (Scanner input = new Scanner(map)) {
            while (input.hasNextLine()) {
                mapString.append(input.nextLine()).append("\n");
            }
        }
        return mapString.toString();
    }
}
