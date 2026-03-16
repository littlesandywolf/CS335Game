package critterproject;

public class Item {
    private String name;
    private String description;
    private String type;      // "stat_exp" or "bond"
    private int amount;
    private String target;    // stat name when type is "stat_exp"

    public Item(String name, String description, String type, int amount, String target) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.amount = amount;
        this.target = target;
    }

    public void use(PlayerOwnedCritter critter) {
        if (critter == null) {
            System.out.println("No critter selected!");
            return;
        }
        if ("stat_exp".equals(type) && target != null) {
            critter.addExp(target, amount);
        } else if ("bond".equals(type)) {
            critter.addBond(amount);
        } else {
            System.out.println("Item has no valid effect.");
            return;
        }
        System.out.println("Used " + name + " on " + critter.getName() + "!");
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}
