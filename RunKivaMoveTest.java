public class RunKivaMoveTest {
    public static void main(String[] args) {
        KivaMoveTest KivaMoveTest = new KivaMoveTest();

        KivaMoveTest.verifyKivaState();
        KivaMoveTest.setTestMode(false);
        KivaMoveTest.testForward(3);
        KivaMoveTest.testTurnRight();
        KivaMoveTest.testForward(6);
        KivaMoveTest.setTestMode(true);
        KivaMoveTest.testTake();
        //
        KivaMoveTest.verifyKivaState();
        KivaMoveTest.setTestMode(false);
        KivaMoveTest.testTurnRight();
        KivaMoveTest.testForward();
        KivaMoveTest.testTurnLeft();
        KivaMoveTest.testForward(2);
        KivaMoveTest.testTurnRight();
        KivaMoveTest.testForward(2);
        KivaMoveTest.setTestMode(true);
        KivaMoveTest.testDrop();
    }
}
