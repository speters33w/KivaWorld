package solver;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * A Breadth First maze solving class, adapted for use with KivaWorld.
 *
 * Given a solver.Maze() object, solves the map from starting location to pod location to drop zone location,
 * or from start to end if there is no pod in the map.
 * <p>
 * This is a heavily modified version of com.baeldung.algorithms.maze.solver.BFSMazeSolver.
 * </p>
 *
 * @see Maze
 */
public class BFSMazeSolver {
    /**
     * 2D Directions array (ordered coordinate integer pairs)
     * {@code [0] = UP, [1] = RIGHT [2] = DOWN [3] = LEFT}
     * Points are in reflected format [row][col] or (y,x).
     */
    static final int[][] DIRECTIONS = {
            { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; //in reflected format [row][col] or (y,x)
    
    // todo using this DIRECTIONS HashMap instead of the 2D Integer array seems to work as a Map, but breaks program
    boolean hashMapTrial = false;
    static final Map<Direction,Point> DIRECTIONSMAP = Stream.of(new Object[][]{ //in reflected format [row][col] or (y,x)
            {Direction.UP, new Point(-1, 0)},
            {Direction.RIGHT, new Point(0, 1)},
            {Direction.DOWN, new Point(1, 0)},
            {Direction.LEFT, new Point(0, -1)},
    }).collect(Collectors.toMap(data -> (Direction) data[0], data -> (Point) data[1]));
    
    private boolean isPodFound = false;
    private List<Point> path = new LinkedList<>();

    /**
     * This is the main entry point for the maze solver.
     *
     * @param maze - a floor map in solver.Maze format
     * @return a List of Points containing the solution.
     */
    public List<Point> solve(Maze maze)  {
        List<Point> returnPath= new LinkedList<>();
        //if the map has a pod,
        if (maze.getPodLocation() != null) {

            // get the path of points from the Kiva to the pod
            List<Point> pathToPod = solver(maze,maze.getInitialKivaLocation()); // Points are reflected (y,x) or [row],[col].
            Collections.reverse(pathToPod);
            // and add the Points to the return list.
            returnPath.addAll(pathToPod);
            //System.out.println(pathToPod);
            // In case there is no path to the pod;
            if (pathToPod.isEmpty()){
                System.out.println("Kiva mission aborted, Kiva can not go to pod location.");
            }

            // Then get the path of points from the pod to the drop zone
            List<Point> pathToDropZone = solver(maze,maze.getDropZoneLocation()); // Points are reflected (y,x) or [row],[col].
            // and add the Points to the return list.
            returnPath.addAll(pathToDropZone);
            //System.out.println(pathToDropZone);
            // In case there is no path to the drop zone;
            if (pathToDropZone.isEmpty()){
                System.out.println("Kiva mission aborted, Kiva can not go to drop zone location.");
            }
        }
        // If the map is a simple start-to-finish maze,
        else {
            // Solve for simple start-to-finish maze,
            maze.setPodLocation(maze.getDropZoneLocation());
            List<Point> pathToPod = solver(maze,maze.getInitialKivaLocation()); // Points are reflected (y,x) or [row],[col].
            Collections.reverse(pathToPod);
            // and send the competed map with the solution to the caller.
            System.out.println(pathToPod);
            returnPath = pathToPod;
        }
        // Send the competed map with the solution to the caller.
        return returnPath;
    }

    /**
     * The main solver method.
     *
     * @param maze - a floor map in solver.Maze format.
     * @param startLocation - the starting location point.
     * @return a List of Points containing the solution.
     */
    public List<Point> solver(Maze maze, Point startLocation) {
        LinkedList<Point> nextToVisit = new LinkedList<>();
        path.clear();
        nextToVisit.add(startLocation);

        while (!nextToVisit.isEmpty()) {
            Point currentPoint = nextToVisit.remove();

            if (!maze.isValidLocation(currentPoint.getX(), currentPoint.getY()) || maze.isExplored(currentPoint.getX(), currentPoint.getY())) {
                continue;
            }

            if (maze.isObstacle(currentPoint.getX(), currentPoint.getY())) {
                maze.setVisited(currentPoint.getX(), currentPoint.getY(), true);
                continue;
            }

            if (maze.isPodLocation(currentPoint.getX(), currentPoint.getY())) {
                maze.reset();
                return backtrackPath(currentPoint);
            }
            

            if(hashMapTrial){ // todo using this DIRECTIONS HashMap instead of the 2D Integer array seems to work, but breaks program
                Direction[] directions = Direction.values();
                for(Direction direction : directions) {
                    Point delta = DIRECTIONSMAP.get(direction);
                    System.out.print(DIRECTIONSMAP.get(direction) + ", "); //for testing, seems to print properly
                    Point coordinate = new Point(currentPoint.getX() + delta.getX(), currentPoint.getY() + delta.getY());
                    System.out.println(coordinate + ", "); //for testing, seems to print properly
                    nextToVisit.add(coordinate);
                    maze.setVisited(currentPoint.getX(), currentPoint.getY(), true);
                }
            } else {
                for (int[] direction : DIRECTIONS) {
                    Point coordinate = new Point(currentPoint.getX() + direction[0], currentPoint.getY() + direction[1], currentPoint);
                    nextToVisit.add(coordinate);
                    maze.setVisited(currentPoint.getX(), currentPoint.getY(), true);
                }
            }
        }
        return Collections.emptyList();
    }

    /**
     * The Point path creator method.
     *
     * @param currentPoint the current location of the search (y,x).
     * @return - a List of Points containing the path.
     */
    private List<Point> backtrackPath(Point currentPoint) {
        Point iter = currentPoint;

        while (iter != null) {
            path.add(iter);
            iter = iter.parent;
        }
        return path;
    }
    
    public String constructKivaCommands(List<Point> path){
        StringBuilder commands = new StringBuilder();
        Direction direction = Direction.UP;
        Direction directionDesired = Direction.UP;
        Point delta = new Point();
        for(int i = 0; i < path.size()-1; i++){
            Point p = path.get(i);
            Point q = path.get(i+1);
            delta = delta.getDelta(p, q);
            if(p.equals(q)){
                commands.append("T");
            }
            if(DIRECTIONSMAP.get(direction).equals(delta)){
                commands.append("F");
            }
            switch(delta.getX()){
                case -1:
                    directionDesired = Direction.UP;
                    if (direction == Direction.RIGHT){
                        commands.append("LF");
                    }
                    if (direction == Direction.DOWN){
                        commands.append("LLF");
                    }
                    if (direction == Direction.LEFT){
                        commands.append("RF");
                    }
                    break;
                case 1:
                    directionDesired = Direction.DOWN;
                    if (direction == Direction.RIGHT){
                        commands.append("RF");
                    }
                    if (direction == Direction.UP){
                        commands.append("RRF");
                    }
                    if (direction == Direction.LEFT){
                        commands.append("LF");
                    }
                    break;
            }
            switch(delta.getY()){
                case -1:
                    directionDesired = Direction.LEFT;
                    if (direction == Direction.UP){
                        commands.append("LF");
                    }
                    if (direction == Direction.RIGHT){
                        commands.append("LLF");
                    }
                    if (direction == Direction.DOWN){
                        commands.append("RF");
                    }
                    break;
                case 1:
                    directionDesired = Direction.RIGHT;
                    if (direction == Direction.UP){
                        commands.append("RF");
                    }
                    if (direction == Direction.LEFT){
                        commands.append("RRF");
                    }
                    if (direction == Direction.DOWN){
                        commands.append("LF");
                    }
                    break;
            }
            direction = directionDesired;
            if(i == path.size()-2){
                commands.append("D");
            }
        }
        return commands.toString();
    }
}
