package critterproject;

//import java.util.Scanner;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit when user hits X
		window.setResizable(false); //no resizing window
		window.setTitle("Critter Game");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null); //window will be displayed at the center of the screen bc not specified
		window.setVisible(true);
		
		gamePanel.startGameThread();
		
		
		
		
		
		/*Scanner s = new Scanner(System.in);
		
		System.out.println("Welcome to The Critter Game");
        System.out.print("Name your new critter: ");
        String name = s.nextLine().trim();

		if (name.isEmpty()) name = "Baby Critter"; //if empty, will just be called default "Baby Critter"
        if (name.length() > 64) name = name.substring(0, 64);

		//creating random genes (= 50 points total) 
        int[] genes = CritterFactory.generateGenes();
        PlayerOwnedCritter critter = new PlayerOwnedCritter(name, genes);

        System.out.println("\n--- Created: " + critter.getName() + " ---"); 
        //initial stat check
        System.out.println("Starting Stamina: " + critter.getStat("stamina").getLevel());

        boolean running = true;
        while (running) {
            //status bar so the player knows the critter info every turn!
            System.out.println("\n[" + critter.getName() + " | Hunger: " + critter.getHunger() + "/100 | Age: " + critter.getAge() + "]");
            System.out.println("1. View Full Stats");
            System.out.println("2. Feed (Reduce Hunger)");
            System.out.println("3. Rest (Gain Stamina)");
            System.out.println("4. Play (Train a Stat)");
            System.out.println("5. Rename Critter");
            System.out.println("6. Age Up (Manual)");
            System.out.println("7. Quit");
            System.out.print("Choice: ");
            
            String choiceline = s.nextLine().trim();
            int choice;
            
            try {
                choice = Integer.parseInt(choiceline);    
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
                continue;
            }
        
            switch (choice) {
                case 1:
                    critter.displayStats();
                    break;

                case 2:
                    System.out.print("How much food? ");
                    if(s.hasNextInt()) {
                        int food = s.nextInt();
                        s.nextLine(); 
                        critter.feed(food);
                    } else {
                        System.out.println("Invalid amount!");
                        s.nextLine();
                    }
                    break;

                case 3:
                    critter.rest();
                    break;

                case 4:
                    System.out.print("What do you want to play? (run/swim/climb/fly/stamina): ");
                    String playStat = s.nextLine().toLowerCase().trim();
                    critter.play(playStat);
                    break;

                case 5:
                    System.out.print("New name: ");
                    String newName = s.nextLine().trim();
                    
                    if (newName.isEmpty()) {
                        System.out.println("Name can't be empty!");
                    } else if (newName.length() > 64) {
                        System.out.println("Name needs to be 64 characters or less!");
                    } else if (critter.rename(newName)) {
                        System.out.println("Renamed to " + critter.getName() + "!");
                    } else {
                        System.out.println("That name is already taken!");
                    }
                    break;

                case 6:
                    critter.incrementAge();
                    System.out.println("Critter is now " + critter.getAge() + " turns old.");
                    break;

                case 7:
                    System.out.println("Goodbye! " + critter.getName() + " will miss you...!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

            //at the end of every loop, update critter vitals
            if (running) {
                critter.updateTurn();
            }
        }

        s.close(); */
    } 
}

