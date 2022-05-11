/**
 * Enumeration class KivaCommand - write a description of the enum class here
 */
public enum KivaCommand
{
    FORWARD('F'),
    TURN_LEFT('L'),
    TURN_RIGHT('R'),
    TAKE('T'),
    DROP('D');

    private char directionKey;

    private KivaCommand(char directionKey){
        this.directionKey = directionKey;
//        String directionKeyString = String.valueOf(directionKey).toUpperCase();

    }
    public char getDirectionKey(){
            this.directionKey = directionKey;
            return directionKey;
    }
}



