package critterproject;

import java.util.Random;

public class Competition {
    private static final Random rand = new Random();

    public static String compete(PlayerOwnedCritter c1, PlayerOwnedCritter c2) {
        if (c1 == null || c2 == null || c1.isSameCritter(c2)) {
            return "Invalid competitors!";
        }

        float score1 = c1.getTotalPower();
        float score2 = c2.getTotalPower();

        if (score1 > score2 + 0.1f) {
            return c1.getName() + " wins the competition against " + c2.getName() + "!";
        } else if (score2 > score1 + 0.1f) {
            return c2.getName() + " wins the competition against " + c1.getName() + "!";
        } else {
            // tiebreaker
            return rand.nextBoolean()
                    ? c1.getName() + " wins the competition against " + c2.getName() + " in a tiebreaker!"
                    : c2.getName() + " wins the competition against " + c1.getName() + " in a tiebreaker!";
        }
    }
}
