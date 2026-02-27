package critterproject;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Welcome to The Critter Game");
        System.out.print("Name your new critter: ");
        String name = s.nextLine();

		if (name.isEmpty()) name = "Baby Critter";
        if (name.length() > 64) name = name.substring(0, 64);

		//creating random genes (= 50 points total) here?
        int[] genes = CritterFactory.generateGenes();
        PlayerOwnedCritter critter = new PlayerOwnedCritter(name, genes);

        System.out.println("Created: " + name); 
        //"get stat" print statement here

        boolean running = true;
        while (running) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. View stats");
            System.out.println("2. Add EXP to a stat");
            System.out.println("3. Rename critter");
            System.out.println("4. Age up ");
            System.out.println("5. Quit");
            System.out.print("Choice: ");
            
            String choiceline = s.nextLine().trim();
            int choice;
            
            try {
            	choice = Integer.parseInt(choiceline);	
            }
            
            catch (NumberFormatException e) {
            	System.out.println("Invalid Choice!");
                
                continue;
            }
        
        	if (choice < 1 || choice > 5) {
        		System.out.println("Invalid Choice!");
        		continue;
        	}

            switch (choice) {
                case 1:
                    critter.displayStats();
                    break;

                case 2:
                    System.out.print("Stat (run/swim/climb/fly/stamina): ");
                    String stat = s.nextLine().toLowerCase();
                    System.out.print("EXP amount: ");
                    int exp = s.nextInt();
                    s.nextLine();
                    critter.addExp(stat, exp);
                    System.out.println("EXP added!");
                    break;

                case 3:
                    System.out.print("New name: ");
                    String newName = s.nextLine().trim();
                    if (critter.rename(newName)) {
                        System.out.println("Renamed to " + critter.getName() + "!");
                    } else {
                        System.out.println("That name is already taken!");
                    }
                    break;

                case 4:
                    critter.incrementAge();
                    System.out.println("Critter is now " + critter.getAge() + " turns old.");
                    break;

                case 5:
                    System.out.println("Goodbye! Your critter will miss you~");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        s.close();
    }
}
