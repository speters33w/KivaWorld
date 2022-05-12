/**
 * Tests kiva commands and direction keys in KivaCommand
 */
public class KivaCommandTester
{
    public void testForward() {
        KivaCommand command = KivaCommand.FORWARD;
        System.out.print(command);
        System.out.println(":    " + command.getDirectionKey());
    }

    // For you: create four more methods, each testing a different command.

    public void testTurnLeft() {
        KivaCommand command = KivaCommand.TURN_LEFT;
        System.out.print(command);
        System.out.println(":  " + command.getDirectionKey());
    }

    public void testTurnRight() {
        KivaCommand command = KivaCommand.TURN_RIGHT;
        System.out.print(command);
        System.out.println(": " + command.getDirectionKey());
    }

    public void testTake() {
        KivaCommand command = KivaCommand.TAKE;
        System.out.print(command);
        System.out.println(":       " + command.getDirectionKey());
    }

    public void testDrop() {
        KivaCommand command = KivaCommand.DROP;
        System.out.print(command);
        System.out.println(":       " + command.getDirectionKey());
    }

    /**
     * Runs the KivaCommand tests
     *
     * @param args String array command line arguments (not used)
     */
    public static void main(String[] args) {
        KivaCommandTester commandTester = new KivaCommandTester();
        commandTester.testForward();
        commandTester.testTurnLeft();
        commandTester.testTurnRight();
        commandTester.testTake();
        commandTester.testDrop();
    }
}
