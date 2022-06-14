package solver;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.List;

public class MazeDriver {
    public static void main(String[] args) throws Exception {
        execute(selectFile());
    }

    /**
     * Opens a GUI file selector dialog in the source directory where the user can select a Maze or FloorMap file.
     * Restricts extensions to text files (*.txt).
     *
     * @return java.io.file the selected file.
     *
     * @throws NullPointerException (in main) if user cancels the file open operation.
     */
    private static File selectFile(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("FloorMap Files", "txt", "map", "maz", "maze", "fm", "FloorMap");
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.showDialog(null,"Select");
        fileChooser.setVisible(true);
        return fileChooser.getSelectedFile();
    }

    private static void execute(File file) throws Exception {
        Maze maze = new Maze(file);
        bfs(maze); // To find the shortest path, a graph traversal approach known as Breadth-first search.
    }

    private static void bfs(Maze maze) {
        BFSMazeSolver bfs = new BFSMazeSolver();
        List<Point> path = bfs.solve(maze);
        maze.printPath(path);
        String commands = bfs.constructKivaCommands(path);
        System.out.println("Kiva Commands:\n" + commands);
        maze.reset();
    }
}
