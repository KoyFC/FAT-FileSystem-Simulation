package main;
import structures.Fat;
import structures.Console;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Fat fat = new Fat(6);
		Console console = new Console(fat);

		System.out.println("Welcome to the program. Please choose an option below:");
		System.out.println("\t1. Option 1");
		System.out.println("\t2. Option 2");
		
		int choice = 1;
		do {
			if (choice < 1 || choice > 6) System.err.println("\nThat option is outside the allowed range.");
			System.out.print("Please choose your option: ");
			choice = sc.nextInt();
		} while(choice < 1 || choice > 6);
		
		switch (choice) {
			case 1:
				
				break;
		}
		
	}
}
