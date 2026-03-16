package critterproject;

import java.util.Random;

public class BreedingCenter {
    private static final Random rand = new Random();

    public static PlayerOwnedCritter breed(PlayerOwnedCritter parentA, PlayerOwnedCritter parentB, String babyName) {
        if (parentA == null || parentB == null || parentA.isSameCritter(parentB)) {
            return null;
        }

        int[] genesA = parentA.getGenes();
        int[] genesB = parentB.getGenes();
        int[] childGenes = new int[10];

        for (int i = 0; i < 10; i++) {
            int base = rand.nextBoolean() ? genesA[i] : genesB[i];
            int variation = rand.nextInt(5) - 2;
            childGenes[i] = Math.max(0, Math.min(10, base + variation));
        }

        PlayerOwnedCritter baby = new PlayerOwnedCritter(babyName, childGenes);
        return baby;
    }
}
