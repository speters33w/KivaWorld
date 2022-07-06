package kivaworld;

/**
 * Thrown when an attempt is made to drop a pod on a location that isn't a drop zone.
 */
public class IllegalDropZoneException extends RuntimeException {

    /**
     * Thrown when attempting to drop a pod occurs on an invalid location.
     *
     * @param message String What went wrong and in which location.
     */
    public IllegalDropZoneException(String message) {
        super(message);
    }
}
