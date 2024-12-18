package main;
import structures.Fat;
import structures.Console;
import structures.Directory;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Fat fat = new Fat(6);
		Console console = new Console(fat);
		
		// TODO: ESTA PARTE HAY QUE MOVERLA A LA CLASE CONSOLA
		
		int choice = 1; // Initialize the choice to 1 so that we can enter the choice loop later on
		/*
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
		*/

		
		// DEBUG ZONE
		
		console.listProcesses();
		
		
		console.printMetadata();
		System.out.println("\n");
		
		console.createDirectory();
		
		System.out.println("\nAvailable clusters: " + fat.availableClusters());
		
		console.printMetadata();
		
		console.createFile();
		System.out.println("\nAvailable clusters: " + fat.availableClusters());

		console.printMetadata();
		
		console.deleteFile();
		console.printMetadata();
		
		console.createDirectory();
		System.out.println("\nAvailable clusters: " + fat.availableClusters());
		console.createDirectory();
		System.out.println("\nAvailable clusters: " + fat.availableClusters());
		
		console.printMetadata();
		
		console.changeDirectory("Pelis");
		System.out.println("Current directory: " + console.currentDir);
		console.changeDirectory("..");
		console.changeDirectory("..");
		System.out.println("Current directory: " + console.currentDir);
		console.changeDirectory("PelisBuenas");
		System.out.println("Current directory: " + console.currentDir);
		console.changeDirectory("Pelis");
		System.out.println("Current directory: " + console.currentDir);
		
	}
}
