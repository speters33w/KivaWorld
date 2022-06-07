package kivaworld;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

/**
 * Thrown when an invalid command key (that doesn't correspond to a valid KivaCommand) is received.
 */
public class InvalidKivaCommandException extends RuntimeException {

    /**
     * Thrown when receiving an unrecognized command.
     *
     * @param message String message to be displayed on console.
     */
    public InvalidKivaCommandException(String message) {
        super(message);
    }
}
