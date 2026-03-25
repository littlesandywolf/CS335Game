package critterproject;

public class Critters {
	private static int nextID = 1; //global counter
	protected int id;
	protected String name; //critter name
	protected boolean playerOwned; //boolean whether or not owned by player
	
	protected int hunger = 0; //hunger variable: 0=full, 100=starving
	
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
	
	//feed method: reduces hunger
    public void feed(int foodAmount) {
        this.hunger -= foodAmount;
        if (this.hunger < 0) this.hunger = 0; // Can't be "negative" hungry
        System.out.println(name + " ate some food. Hunger is now " + hunger + "/100.");
    }
    
    // rest method recovers stamina
    public void rest() {
        System.out.println(name + " is taking a nap...");
        //recovering 20 points of "experience" or "level" for stamina
        this.addExp("stamina", 20); 
        //resting will slightly increase hunger
        this.hunger += 5; 
    }
    
    
    
    //play method increases exp but costs stamina and increases hunger
    public void play(String statToTrain) {
        if (this.hunger > 80) {
            System.out.println(name + " is too hungry to play!");
            return;
        }
        
        System.out.println(name + " is playing and learning " + statToTrain + "!");
        this.addExp(statToTrain, 15);
        
        //playing makes critters hungrier and tired
        this.hunger += 10;
        
        //future way to "drain" stamina called here eventually? 
    }
	
    
    //updateTurn is called at the end of every turn
    public void updateTurn() {
        this.hunger += 2; //critter will get hungrier as time goes on
        if (this.hunger > 100) this.hunger = 100;
        
        if (this.hunger > 90) {
            System.out.println("Warning: " + name + " is starving!");
        }
    }
	
    
	//method to check if the id from two different critters are equal
	public boolean isSameCritter(Critters other) {
        return this.id == other.id;
	}
	
	public int getTotalPower() {
	    return run.getLevel() +
	           swim.getLevel() +
	           climb.getLevel() +
	           fly.getLevel() +
	           stamina.getLevel();
	}
	
	public int[] getGenes() {
	    return new int[] {
	        run.getGeneA(),    // genes[0]
	        run.getGeneB(),    // genes[1]
	        swim.getGeneA(),   // genes[2]
	        swim.getGeneB(),   // genes[3]
	        climb.getGeneA(),  // genes[4]
	        climb.getGeneB(),  // genes[5]
	        fly.getGeneA(),    // genes[6]
	        fly.getGeneB(),    // genes[7]
	        stamina.getGeneA(),// genes[8]
	        stamina.getGeneB() // genes[9]
	    };
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
    public int getHunger() { return hunger; }
}
