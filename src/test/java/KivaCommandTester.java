/**
 * Tests kiva commands and direction keys in KivaCommand
 *
 * @author Stephan Peters (peterstz)
 * @version 20220607.1300
 */
public class KivaCommandTester
{
    private void testForward() {
        KivaCommand command = KivaCommand.FORWARD;
        System.out.print(command);
        System.out.println(":    " + command.getDirectionKey());
    }

    private void testTurnLeft() {
        KivaCommand command = KivaCommand.TURN_LEFT;
        System.out.print(command);
        System.out.println(":  " + command.getDirectionKey());
    }

    private void testTurnRight() {
        KivaCommand command = KivaCommand.TURN_RIGHT;
        System.out.print(command);
        System.out.println(": " + command.getDirectionKey());
    }

    private void testTake() {
        KivaCommand command = KivaCommand.TAKE;
        System.out.print(command);
        System.out.println(":       " + command.getDirectionKey());
    }

    private void testDrop() {
        KivaCommand command = KivaCommand.DROP;
        System.out.print(command);
        System.out.println(":       " + command.getDirectionKey());
    }

    /**
     * Runs the KivaCommand tests.
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
