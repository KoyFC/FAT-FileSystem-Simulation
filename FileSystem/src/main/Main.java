package main;
import structures.Fat;
import structures.Console;
import structures.Directory;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		Fat fat = new Fat(6);
		Console console = new Console(fat);

		int choice = 1; // Initialize the choice to 1 so that we can enter the choice loop later on
		
		do {
			System.out.println("\n[------------- Main menu -------------]\n");
			
			System.out.println("Current directory: " + console.currentDir.globalPath + console.currentDir);
			
			System.out.println("\n1. Print metadata");
			System.out.println("2. Create directory");
			System.out.println("3. Create file");
			System.out.println("4. Change current directory");
			System.out.println("5. Clear current directory");
			System.out.println("6. Delete directory");
			System.out.println("7. Delete file");
			System.out.println("8. Process list");
			System.out.println("9. Start new process");
			System.out.println("10. Kill process");
			System.out.println("11. Exit");
			
			System.out.println("\n------ EXTRA OPTIONS ------");
			System.out.println("12. Print current Directory");
			
			System.out.print("\nPlease input the number of the choice you want to make: ");
			choice = sc.nextInt();
			
			while (choice < 1 || choice > 12) {
				System.out.print("\nYou entered a choice outside the allowed range. Please input one of the presented choices (1 - 11): ");
				choice = sc.nextInt();
			}
			
			switch (choice) {
			case 1:
				System.out.println("\n[------------- Metadata -------------]\n");
				console.printMetadata();
				break;
			case 2:
				System.out.println("\n\n[------------- Create new Directory -------------]");
				console.createDirectory();
				break;
			case 3:
				System.out.println("\n\n[------------- Create new File -------------]");
				console.createFile();
				break;
			case 4:
				System.out.println("\n[------------- Change current Directory -------------]\n");
				console.changeDirectory();
				break;
			case 5:
				System.out.println("\n[------------- Clear current directory -------------]");
				console.clearDirectory();
				break;
			case 6:
				System.out.println("\n[------------- Delete a Directory -------------]");
				console.deleteDirectory();
				break;
			case 7:
				System.out.println("\n[------------- Delete a File -------------]");
				console.deleteFile();
				break;
			case 8:
				System.out.println("\n[------------- List of all running processes -------------]\n");
				console.listProcesses();
				break;
			case 9:
				System.out.println("\n[------------- Start new process -------------]");

				break;
			case 10:
				System.out.println("\n[------------- Kill process -------------]");

				break;
			case 12:
				System.out.println("\n[------------- Print current Directory -------------]");
				console.printData();
				break;
			}
		} while (choice != 11); 
		
		System.out.println("\nThank you for using this simulator.");
	}
}
