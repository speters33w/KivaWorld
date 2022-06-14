/**
 * A maze solver designed to work with KivaWorld. Includes a robust point class and a random Kiva map generator.
 */
package solver;

/*
 * Point:
 * A Cartesian Coordinate point in integer resolution represented by a pair of numeric coordinates (x,y)).
 * Includes point translation, reflection, etc.
 * The Point object can contain a separate reference point, parent.
 *
 * KivaCreateMap:
 * Creates a Kiva FloorMap string.
 * This can be a random map, or a default map.
 * Contains a main() method that will generate a random map and allow the user to save the map.

 *
 * Direction:
 * Enumeration describing a facing direction, limiting the facing directions to up, down, left, and right.
 * The included Point, called delta, can be added to another Point to move one space in the indicated direction.
 *
 * MazeDriver:
 * Runner program for the maze solver.
 * Contains a main() method that prompts the user to open a map file from disk, solves the maze,
 * and gives the Kiva commands required to solve the map.
 *
 * Maze:
 * A maze class used to create a maze that can be solved by the solver.
 * modified version of one in package: com.baeldung.algorithms.maze.solver
 *
 * BFSMazeSolver:
 * A Breadth First Solver, a heavily modified version of one in package: com.baeldung.algorithms.maze.solver
 *
 * The original Baeldung article on maze solving may be found here:
 * https://www.baeldung.com/java-solve-maze
 *
 * The original Baeldung Java package may be found here:
 * https://github.com/eugenp/tutorials/tree/master/algorithms-modules/algorithms-miscellaneous-2/src/main/java/com/baeldung/algorithms/maze/solver
 */
