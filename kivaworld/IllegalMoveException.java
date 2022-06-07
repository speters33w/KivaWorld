package kivaworld;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

/**
 * Thrown when an attempt is made to have Kiva move in an illegal direction (e.g. into an obstacle).
 */
public class IllegalMoveException extends RuntimeException {

    /**
     * Thrown when attempting to move to an invalid location (e.g. off the floor).
     *
     * @param message String message to be displayed on console.
     */
    public IllegalMoveException(String message) {
        super(message);
    }
}

