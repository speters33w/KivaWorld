import edu.duke.Point;

public class RunKivaMoveTest {

    public static void main(String[] args) {
        KivaMoveTest kivaMoveTest = new KivaMoveTest();
        kivaMoveTest.testKivaTakeDrop(kivaMoveTest);

        // EXCEPTION tests
//        kivaMoveTest = new KivaMoveTest(); // Re-initialize Kiva to default location
//        kivaMoveTest.testMoveOutOfBounds(); // should terminate in IllegalMoveException
//        kivaMoveTest.testMoveIntoObstacle(); // should terminate in IllegalMoveException
        kivaMoveTest.testPodCollision(); // should terminate in IllegalMoveException
//        kivaMoveTest.testNoPodAtLocation();
//        kivaMoveTest.testIllegalDropZone();

//        Kiva kiva = new Kiva();
//        kiva.tempExceptionMessageTester();
    }


}