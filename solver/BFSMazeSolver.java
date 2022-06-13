package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BFSMazeSolver {
    private static final int[][] DIRECTIONS = {
            { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; //in format [row][col] or (y,x)
    private boolean isPodFound = false;
    private Direction facingDirection = Direction.UP; //todo base search for shortest path to include facing movements as a movement

    public List<Point> solve(Maze maze) throws UnsupportedOperationException {
        if (maze.getPodLocation() != null) { //if this is not a simple start to finish maze
            List<Point> returnList;
            returnList = (solver(maze,maze.getInitialKivaLocation()));
//            System.out.println(returnList);
//            returnList = (solver(maze,maze.getPodLocation()));
            try {
                returnList.addAll(solver(maze, maze.getPodLocation()));
            } catch (UnsupportedOperationException e) {
                System.out.println("Kiva mission aborted, Kiva can not get to pod.");
            }
//            System.out.println(solver(maze,maze.getPodLocation()));
            System.out.println(returnList); //todo fix the map so if the Kiva backtracks after getting the pod, entire path is  displayed.
            return returnList;
        } else {
            isPodFound = true;
            Point startLocation = maze.getInitialKivaLocation();
            return solver(maze,startLocation);
        }
    }

    public List<Point> solver(Maze maze, Point startLocation) {
        LinkedList<Point> nextToVisit = new LinkedList<>();
        nextToVisit.add(startLocation);

        while (!nextToVisit.isEmpty()) {
            Point cur = nextToVisit.remove();

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
                Point coordinate = new Point(cur.getX() + direction[0], cur.getY() + direction[1], cur);
                nextToVisit.add(coordinate);
                maze.setVisited(cur.getX(), cur.getY(), true);
            }
        }
        return Collections.emptyList();
    }

    private List<Point> backtrackPath(Point cur) {
        List<Point> path = new ArrayList<>();
        Point iter = cur;

        while (iter != null) {
            path.add(iter);
            iter = iter.parent;
        }

        return path;
    }
}
