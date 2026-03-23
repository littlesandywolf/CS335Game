package critterproject;

public class Stats {
	private String type;
	private int level = 0;
	private int exp = 0;
	private float value = 0;
	private float multiplier;
	private int levelCap;
	
	
	private final int geneA;   
    private final int geneB;

   
    public Stats(String type, int geneA, int geneB, int levelCap) {
        this.type     = type;
        this.geneA    = geneA;          
        this.geneB    = geneB;
        this.multiplier = (10.0f + geneA + geneB) / 10.0f;
        this.levelCap = levelCap;
    }

    
    public int getGeneA() {
        return geneA;
    }

    public int getGeneB() {
        return geneB;
    }
	
	//math for exp counts
	public void addExp(int amount) { 
		if (level >= levelCap) {
			exp = 0;
			return;
		} 
		exp += amount;
		while (exp >= 100 && level < 99) {
            exp -= 100;
            levelUp();
        }
        if (level >= levelCap) exp = 0;
	}
	
	//leveling up method
	private void levelUp() {
		if (level >= levelCap) {
			exp = 0;
			return;
		}
		level ++;
        float gain = (float) (Math.random() * 6 + 5) * multiplier; //formula is random(5-10) * multiplier
        value += gain;
		
	}
	
	//nice printing
	public String toString() {
	    return type + ": Lvl " + level + " (" + value + ") [Exp: " + exp + "/100, Mult: " + multiplier + "]";
	}

	public float getValue() { return value; }
	public int getLevel() { return level; }
	public int getExp() { return exp; }
}
