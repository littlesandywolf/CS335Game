package critterproject;

import java.util.HashSet;
import java.util.Set;

public class PlayerOwnedCritter extends Critters {
    private int age = 0;
    private int bond = 0;

    // Prevents duplicate names for player-owned critters
    private static final Set<String> usedNames = new HashSet<>();

    public PlayerOwnedCritter(String name, int[] genes) {
        super(name, true, genes);
        usedNames.add(name); // assume unique on creation (main only creates one for now)
    }

    // Age & Bond
    public void incrementAge() {
        age++;
    }

    public void addBond(int amount) {
        bond = Math.min(100, bond + amount); // optional cap
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

    public void displayStats() {
        System.out.println(name + " (Age: " + age + " Bond: " + bond + ")");
        System.out.println(run);
        System.out.println(swim);
        System.out.println(climb);
        System.out.println(fly);
        System.out.println(stamina);
    }
}
