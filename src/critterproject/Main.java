package critterproject;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Welcome to The Critter Game");
        System.out.print("Name your new critter: ");
        String name = s.nextLine();

        //creating random genes (= 50 points total) here?

        System.out.println("Created: " + name);
        //"get stat" print statement here
	}
}
