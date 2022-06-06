package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.addAll;

public class BFSMazeSolver {
    private static final int[][] DIRECTIONS = {
            { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; //in format [row][col] or (y,x)
    private boolean isPodFound = false;
    private FacingDirection facingDirection = FacingDirection.UP; //todo base search for shortest path to include facing movements as a movement

    public List<Coordinate> solve(Maze maze){
        if (maze.getPodLocation() != null) { //if this is not a simple start to finish maze
            List<Coordinate> returnList;
            returnList = (solver(maze,maze.getInitialKivaLocation()));
//            System.out.println(returnList);
//            returnList = (solver(maze,maze.getPodLocation()));
            returnList.addAll(solver(maze,maze.getPodLocation()));
//            System.out.println(solver(maze,maze.getPodLocation()));
//            System.out.println(returnList); //todo fix the map so if the Kiva backtracks after getting the pod, entire path is  displayed.
            return returnList;
        } else {
            isPodFound = true;
            Coordinate startLocation = maze.getInitialKivaLocation();
            return solver(maze,startLocation);
        }
    }

    public List<Coordinate> solver(Maze maze, Coordinate startLocation) {
        LinkedList<Coordinate> nextToVisit = new LinkedList<>();
        nextToVisit.add(startLocation);

        while (!nextToVisit.isEmpty()) {
            Coordinate cur = nextToVisit.remove();

            if (!maze.isValidLocation(cur.getX(), cur.getY()) || maze.isExplored(cur.getX(), cur.getY())) {
                continue;
            }

            if (maze.isObstacle(cur.getX(), cur.getY())) {
                maze.setVisited(cur.getX(), cur.getY(), true);
                continue;
            }

            if (maze.isPodLocation(cur.getX(), cur.getY())) {
                if(!isPodFound) {
                    isPodFound = true;
                    return backtrackPath(cur);
                }
            }

            if (maze.isDropZone(cur.getX(), cur.getY())) {
                if(isPodFound) {
                   return backtrackPath(cur);
                }
            }

            for (int[] direction : DIRECTIONS) {
                Coordinate coordinate = new Coordinate(cur.getX() + direction[0], cur.getY() + direction[1], cur);
                nextToVisit.add(coordinate);
                maze.setVisited(cur.getX(), cur.getY(), true);
            }
        }
        return Collections.emptyList();
    }

    private List<Coordinate> backtrackPath(Coordinate cur) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate iter = cur;

        while (iter != null) {
            path.add(iter);
            iter = iter.parent;
        }

        return path;
    }
}
