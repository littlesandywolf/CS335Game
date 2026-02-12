package critterproject;

public class Stats {
	private String type;
	private int level = 0;
	private int exp = 0;
	private float value = 0;
	private float multiplier;
	
	
	//constructor
	public Stats(String type, int geneA, int geneB) {
		this.type = type;
		this.multiplier = (10.0f + geneA + geneB)/10.0f;
	}
	
	
	//math for exp counts
	public void addExp(int amount) {
		if (level >= 99) {
			exp = 0;
			return;
		} 
		exp += amount;
		while (exp >= 100 && level < 99) {
            exp -= 100;
            levelUp();
        }
        if (level == 99) exp = 0;
	}
	
	//leveling up method
	private void levelUp() {
		level ++;
        float gain = (float) (Math.random() * 6 + 5) * multiplier; //formula is random(5-10) * multiplier
        value += gain;
	}
	
	//nice printing
	public String toString() {
	    return type + ": Lvl " + level + " (" + value + ") [Exp: " + exp + "/100, Mult: " + multiplier + "]";
	}
	
}
