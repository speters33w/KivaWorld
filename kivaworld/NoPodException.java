package kivaworld;

/**
 * Thrown if an attempt is made to pick up a pod that doesn't exist.
 */
public class NoPodException extends RuntimeException {

    /**
     * Thrown when attempting to pick up a pod that isn't there.
     *
     * @param message - What went wrong and in which location.
     */
    public NoPodException(String message) {
        super(message);
    }
}

