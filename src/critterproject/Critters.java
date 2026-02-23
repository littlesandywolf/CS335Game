package critterproject;

public class Critters {
	private static int nextID = 1; //global counter
	protected int id;
	protected String name; //critter name
	protected boolean playerOwned; //boolean whether or not owned by player
	
	protected Stats run, swim, climb, fly, stamina;
	
	public Critters(String name, boolean playerOwned, int[] genes) {
		this.id = nextID++;
		this.name = name;
		this.playerOwned = playerOwned;
		
		//genes paired up: runA, runB, swimA, swimB, etc.
		this.run = new Stats("Run", genes[0], genes[1], 99);
        this.swim = new Stats("Swim", genes[2], genes[3], 99);
        this.climb = new Stats("Climb", genes[4], genes[5], 99);
        this.fly = new Stats("Fly", genes[6], genes[7], 99);
        this.stamina = new Stats("Stamina", genes[8], genes[9], 99);
	}
	
	//method to check if the id from two different critters are equal
	public boolean isSameCritter(Critters other) {
        return this.id == other.id;
	}
	
	//getter for stats -> instead of if/else used switch statement so we can add later (https://www.w3schools.com/java/java_switch.asp) 
	public Stats getStat(String statName) {
        return switch (statName.toLowerCase()) {
            case "run" -> run;
            case "swim" -> swim;
            case "climb" -> climb;
            case "fly" -> fly;
            case "stamina" -> stamina;
            default -> null;
        };
    }
	
	public void addExp(String statName, int amount) {
        Stats stat = getStat(statName);
        if (stat != null) {
            stat.addExp(amount); //levels up the stat if necessary
        }
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public boolean isPlayerOwned() { return playerOwned; }
}
