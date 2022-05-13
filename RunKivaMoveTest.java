public class RunKivaMoveTest {
    public static void main(String[] args) {
        KivaMoveTest kivaMoveTest = new KivaMoveTest();

        kivaMoveTest.verifyKivaState();
        kivaMoveTest.setTestMode(false);
        kivaMoveTest.testForward(3);
        kivaMoveTest.testTurnRight();
        kivaMoveTest.testForward(6);
        kivaMoveTest.setTestMode(true);
        kivaMoveTest.testTake();
        //
        kivaMoveTest.verifyKivaState();
        kivaMoveTest.setTestMode(false);
        kivaMoveTest.testTurnRight();
        kivaMoveTest.testForward();
        kivaMoveTest.testTurnLeft();
        kivaMoveTest.testForward(2);
        kivaMoveTest.testTurnRight();
        kivaMoveTest.testForward(2);
        kivaMoveTest.setTestMode(true);
        kivaMoveTest.testDrop();
    }
}
