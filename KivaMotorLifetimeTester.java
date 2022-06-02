
/**
 * Very simple motor lifetime counter tester (starts at 0L).
 * Kiva max motor lifetime is 20,000 hours, counted in milliseconds.
 *
 * The Kiva max lifetime in milliseconds is 4320000000000L.
 *
 *  @author Stephan Peters (peterstz)
 *  @version 20220601.2200
 */
public class KivaMotorLifetimeTester
{
    /**
     * Main test method (public static void main())
     * Tests with motorLifetime starting at 0L
     */
    public static void main(String[] args){
        String motorLifetimeMapString = ""
                + "-----\n"
                + "|K D|\n"
                + "| P |\n"
                + "|* *|\n"
                + "-----\n";

        FloorMap motorLifetimeTestMap = new FloorMap(motorLifetimeMapString);
        Kiva kiva = new Kiva(motorLifetimeTestMap);
        System.out.println("Starting Kiva motor lifetime: " + kiva.getMotorLifetime());
        kiva.move(KivaCommand.TURN_RIGHT);
        System.out.println("Current Kiva motor lifetime: " + kiva.getMotorLifetime());
        kiva.move(KivaCommand.FORWARD);
        System.out.println("Current Kiva motor lifetime: " + kiva.getMotorLifetime());
        kiva.move(KivaCommand.TURN_RIGHT);
        System.out.println("Current Kiva motor lifetime: " + kiva.getMotorLifetime());
        kiva.move(KivaCommand.FORWARD);
        System.out.println("Current Kiva motor lifetime: " + kiva.getMotorLifetime());
        kiva.move(KivaCommand.TAKE);
        System.out.println("Current Kiva motor lifetime: " + kiva.getMotorLifetime());
        if (kiva.getMotorLifetime() == 4000){
            System.out.println("KivaMotorLifetimeTester PASS! Expect: 4000 Actual: " + kiva.getMotorLifetime());
        } else {
            System.out.println("KivaMotorLifetimeTester FAIL! Expect: 4000 Actual: " + kiva.getMotorLifetime());
        }
    }


}
