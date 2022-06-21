/**
 * Defines valid Kiva commands and links them to a one character directionKey
 *
 * @author Stephan Peters (peterstz)
 * @version 20220613.0900
 */
public enum KivaCommand
{
    FORWARD('F'),
    TURN_LEFT('L'),
    TURN_RIGHT('R'),
    TAKE('T'),
    DROP('D');

    private final char directionKey;

    KivaCommand(char directionKey){
        this.directionKey = directionKey;
//        String directionKeyString = String.valueOf(directionKey).toUpperCase();
    }

    /**
     * Returns the valid direction key for the current Kiva command.
     *
     * @return Direction key as a command.
     */
    public char getDirectionKey(){
            return directionKey;
    }
}



