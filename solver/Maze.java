package solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Maze {
    private static final int EMPTY = 0;
    private static final int OBSTACLE = 1;
    private static final int START = 2;
    private static final int POD = 3;
    private static final int DROP_ZONE = 4;
    private static final int PATH = 5;

    private int[][] maze;
    private boolean[][] visited;
    private Coordinate initialKivaLocation;
    private Coordinate podLocation = null;
    private Coordinate dropZoneLocation;

    public Maze(File maze) throws FileNotFoundException {
        StringBuilder fileText = new StringBuilder();
        try (Scanner input = new Scanner(maze)) {
            while (input.hasNextLine()) {
                fileText.append(input.nextLine()).append("\n");
            }
        }
        initializeMaze(fileText.toString());
    }

    private void initializeMaze(String text) {
        if (text == null || (text = text.trim()).length() == 0) {
            throw new IllegalArgumentException("empty lines data");
        }

        String[] lines = text.split("[\r]?\n");
        maze = new int[lines.length][lines[0].length()];
        visited = new boolean[lines.length][lines[0].length()];

        for (int row = 0; row < getHeight(); row++) {
            if (lines[row].length() != getWidth()) {
                throw new IllegalArgumentException("line " + (row + 1) + " wrong length (was " + lines[row].length() 
                        + " but should be " + getWidth() + ")");
            }

            for (int col = 0; col < getWidth(); col++) {
                switch (lines[row].charAt(col)){
                    case '#' :
                    case '*' :
                    case '-' :
                    case '|' :
                        maze[row][col] = OBSTACLE;
                        break;
                    case 'S' :
                    case 'K' :
                        maze[row][col] = START;
                        initialKivaLocation = new Coordinate(row, col);
                        break;
                    case 'P' :
                        maze[row][col] = POD;
                        podLocation = new Coordinate(row, col);
                        break;
                    case 'E' :
                    case 'D' :
                        maze[row][col] = DROP_ZONE;
                        dropZoneLocation = new Coordinate(row, col);
                        break;
                    default:
                        maze[row][col] = EMPTY;
                }
            }
        }
    }

    public int getHeight() {
        return maze.length;
    }

    public int getWidth() {
        return maze[0].length;
    }

    public Coordinate getInitialKivaLocation() {
        return initialKivaLocation;
    }

    public Coordinate getPodLocation() {
        return podLocation;
    }

    public Coordinate getDropZoneLocation() {
        return dropZoneLocation;
    }

    public boolean isInitialKivaLocation(int x, int y) {
        return x == initialKivaLocation.getX() && y == initialKivaLocation.getY();
    }

    public boolean isPodLocation(int x, int y) {
        if (podLocation != null) {
            return x == podLocation.getX() && y == podLocation.getY();
        } else {
            return false;
        }
    }

    public boolean isDropZone(int x, int y) {
        return x == dropZoneLocation.getX() && y == dropZoneLocation.getY();
    }

    public boolean isExplored(int row, int col) {
        return visited[row][col];
    }

    public boolean isObstacle(int row, int col) {
        return maze[row][col] == OBSTACLE;
    }

    public void setVisited(int row, int col, boolean value) {
        visited[row][col] = value;
    }

    public boolean isValidLocation(int row, int col) {
        return row >= 0 && row < getHeight() && col >= 0 && col < getWidth();
    }

    public void printPath(List<Coordinate> path) {
        int[][] tempMaze = Arrays.stream(maze)
            .map(int[]::clone)
            .toArray(int[][]::new);
        for (Coordinate coordinate : path) {
            if (isInitialKivaLocation(coordinate.getX(), coordinate.getY()) || isDropZone(coordinate.getX(), coordinate.getY())) {
                continue;
            }
            tempMaze[coordinate.getX()][coordinate.getY()] = PATH;
        }
        System.out.println(toString(tempMaze));
    }

    public String toString(int[][] maze) {
        StringBuilder result = new StringBuilder(getWidth() * (getHeight() + 1));
        if (podLocation != null) {
            maze[podLocation.getX()][podLocation.getY()] = POD;
        }
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if (maze[row][col] == EMPTY) {
                    result.append(' ');
                } else if (maze[row][col] == OBSTACLE) {
                    if (row == 0 || row == getHeight()-1) {
                        result.append('-');
                    } else if (col == 0 || col == getWidth()-1) {
                        result.append('|');
                    } else {
                        result.append('*');
                    }
                } else if (maze[row][col] == START) {
                    result.append('K');
                } else if (maze[row][col] == POD) {
                    result.append('P');
                }else if (maze[row][col] == DROP_ZONE) {
                    result.append('D');
                } else {
                    result.append('.');
                }
            }
            result.append('\n');
        }
        return result.toString();
    }

    public void reset() {
        for (boolean[] booleans : visited) Arrays.fill(booleans, false);
    }
}
