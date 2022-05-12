public class RunKivaTest {
    public static void main(String[] args) {
        KivaTest kivaTest = new KivaTest();
        kivaTest.verifyKivaState();
        kivaTest.setTestMode(false);
        kivaTest.testForward(3);
        kivaTest.testTurnRight();
        kivaTest.testForward(6);
        kivaTest.testTake();
        kivaTest.verifyKivaState();
        //
        kivaTest.testTurnRight();
        kivaTest.testForward();
        kivaTest.testTurnLeft();
        kivaTest.testForward(2);
        kivaTest.testTurnRight();
        kivaTest.testForward(2);
        kivaTest.testDrop();
        kivaTest.verifyKivaState();
    }
}
