public class RunKivaTest {
    public static void main(String[] args) {
        KivaTest kivaTest = new KivaTest();

        kivaTest.verifyKivaState();
        kivaTest.setTestMode(false);
        kivaTest.testForward(3);
        kivaTest.testTurnRight();
        kivaTest.testForward(6);
        kivaTest.setTestMode(true);
        kivaTest.testTake();
        //
        kivaTest.verifyKivaState();
        kivaTest.setTestMode(false);
        kivaTest.testTurnRight();
        kivaTest.testForward();
        kivaTest.testTurnLeft();
        kivaTest.testForward(2);
        kivaTest.testTurnRight();
        kivaTest.testForward(2);
        kivaTest.setTestMode(true);
        kivaTest.testDrop();
    }
}
