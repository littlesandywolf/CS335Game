package critterproject;

import java.util.HashSet;
import java.util.Set;

enum LifeStage {
    BABY, JUVENILE, ADULT, ELDER
}

public class PlayerOwnedCritter extends Critters {
    private int age = 0;
    private int bond = 0;
    private LifeStage lifeStage;
    
    // Prevents duplicate names for player-owned critters
    private static final Set<String> usedNames = new HashSet<>();

    public PlayerOwnedCritter(String name, int[] genes) {
        String finalName = name;
        int suffix = 1;
        while (usedNames.contains(finalName)) {
            finalName = name + " #" + suffix++;
        }
        super(finalName, true, genes);
        usedNames.add(finalName);
        this.lifeStage = LifeStage.BABY;
    }

    // Age & Bond
    public void incrementAge() {
        age++;
        updateLifeStage();
    }

    public void addBond(int amount) {
        bond = Math.min(100, bond + amount); // optional cap
    }

    public void updateLifeStage() {
        if (age < 5) {
            lifeStage = LifeStage.BABY;
        } else if (age < 15) {
            lifeStage = LifeStage.JUVENILE;
        } else if (age < 30) {
            lifeStage = LifeStage.ADULT;
        } else {
            lifeStage = LifeStage.ELDER;
        }
    }
    // Rename with duplicate protection
    public boolean rename(String newName) {
        if (newName.equals(this.name)) return true;
        if (usedNames.contains(newName)) {
            return false;
        }
        usedNames.remove(this.name);
        this.name = newName;
        usedNames.add(newName);
        return true;
    }

    public int getAge() { return age; }
    public int getBond() { return bond; }
    public LifeStage getLifeStage() { return lifeStage; }
    public void displayStats() {
        System.out.println(name + " (Age: " + age + " Bond: " + bond + ")");
        System.out.println(run);
        System.out.println(swim);
        System.out.println(climb);
        System.out.println(fly);
        System.out.println(stamina);
    }
}
