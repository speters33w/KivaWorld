import java.util.Random;

/**
 * Very simple motor lifetime counter tester (starts at 0L).
 * Kiva max motor lifetime is 20,000 hours, counted in milliseconds.
 *
 * The Kiva max lifetime in milliseconds is 4320000000000L.
 *
 * @author Stephan Peters (peterstz)
 * @version 20220607.1300
 */
public class KivaMotorLifetimeTester
{
    /**
     * Main test method (public static void main())
     * Tests with a random starting motorLifetime.
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
        Random random = new Random();
        int MotorLifetimeHours = random.nextInt(20000);
        long motorLifetime = (long) MotorLifetimeHours * 60 * 60 * 60 * 1000;
        kiva.setMotorLifetime(motorLifetime);
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
        if (kiva.getMotorLifetime() == motorLifetime + 4000){
            System.out.println("KivaMotorLifetimeTester PASS! Expect: " + (motorLifetime + 4000) + " Actual: " + kiva.getMotorLifetime());
        } else {
            System.out.println("KivaMotorLifetimeTester FAIL! Expect: " + (motorLifetime + 4000) + " Actual: " + kiva.getMotorLifetime());
        }
    }
}
