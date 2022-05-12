public class RunKivaTest {
    public static void main(String[] args) {
        KivaTest kivaTest = new KivaTest();
//        kivaTest.testForwardInteractive();
        kivaTest.testTake();
        kivaTest.testDrop();
        kivaTest.setTestMode(false);
        kivaTest.testForwardFromUp();
        kivaTest.testTurnLeftFromUP();
        kivaTest.testTurnLeftFromLeft();
        kivaTest.testTurnLeftFromDown();
        kivaTest.testTurnLeftFromRight();
        kivaTest.testTurnRightFromUP();
        kivaTest.testTurnRightFromLeft();
        kivaTest.testTurnRightFromDown();
        kivaTest.testTurnRightFromRight();
        kivaTest.testTurnLeft(3);
        kivaTest.testForward(3);
        kivaTest.setTestMode(true);
        kivaTest.testTurnRight(3);

    }
}
