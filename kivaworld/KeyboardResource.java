package kivaworld;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * A simple text scanner that takes keyboard input from a user.
 */
public class KeyboardResource {
    private final Scanner scanner;

    /**
     * Scans the "standard" input stream.
     * This stream is already open and ready to supply input data.
     * Typically this stream corresponds to a keyboard.
     */
    public KeyboardResource() {
        this.scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
    }

    /**
     * This method returns the current input line, excluding any line separator at the end.
     *
     * @return The line of input scanned.
     */
    public String getLine() {
        return this.scanner.nextLine();
    }

    /**
     * Closes the Scanner object
     */
    public void close(){
        this.scanner.close();
    }
}
