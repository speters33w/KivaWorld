package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.addAll;

public class BFSMazeSolver {
    private static final int[][] DIRECTIONS = { { 0, -1 }, { 1, 0 }, { -1, 0 }, { 0, 1 } };
    private boolean isPodFound = false;

    public List<Coordinate> solve(Maze maze){
        if (!(maze.getPodLocation().getX() == -1)) {
            List<Coordinate> returnList;
            returnList = (solver(maze,maze.getInitialKivaLocation()));
            returnList.addAll(solver(maze,maze.getPodLocation()));
            returnList.add(maze.getPodLocation()); //todo fix the map so if the Kiva backtracks after getting the pod, entire path is still displayed.
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
