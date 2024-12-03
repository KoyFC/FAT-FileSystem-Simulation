package main;
import structures.Fat;
import structures.Console;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Fat fat = new Fat(6);
		Console console = new Console(fat);
		int choice = 1;

		System.out.print("Welcome to the program. ");
		do {
			System.out.println("Please choose an option below:");
			System.out.println("\t1. Option 1");
			System.out.println("\t2. Option 2");
			
			do {
				if (choice < 1 || choice > 6) System.err.println("\nThat option is outside the allowed range.");
				System.out.print("Please choose your option: ");
				choice = sc.nextInt();
			} while(choice < 1 || choice > 6);
			
			switch (choice) {
			case 1:
				
				break;
			}
		} while (choice != 6);
		
	}
}
