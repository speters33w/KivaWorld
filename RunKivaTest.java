public class RunKivaTest {
    public static void main(String[] args) {
        KivaTest kivaTest = new KivaTest();
//        kivaTest.testForwardInteractive();
//        kivaTest.testForward(9);
//        kivaTest.testTake();
//        kivaTest.testDrop();
        kivaTest.testForwardFromUp();
        kivaTest.testTurnLeftFromUP();
        kivaTest.testTurnLeftFromLeft();
        kivaTest.testTurnLeftFromDown();
        kivaTest.testTurnLeftFromRight();
    }
}
