public class RunKivaTest {
    public static void main(String[] args) {
        KivaTest kivaTest = new KivaTest();
        kivaTest.testForward();
        kivaTest.testForward(9);
        kivaTest.testTake();
        kivaTest.testDrop();
    }
}
