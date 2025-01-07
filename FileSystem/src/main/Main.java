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
		
		do {
			System.out.println("\n[------------- Main menu -------------]\n");
			
			System.out.println("Current directory: " +console.currentDir.globalPath + console.currentDir);
			
			System.out.println("\n1. Show metadata");
			System.out.println("2. Create directory");
			System.out.println("3. Create file");
			System.out.println("4. Change current directory");
			System.out.println("5. Clear current directory");
			System.out.println("6. Delete directory");
			System.out.println("7. Delete file");
			System.out.println("8. Process list");
			System.out.println("9. Start new process");
			System.out.println("10. Kill process");
			System.out.println("11. Salir");
			
			System.out.print("\nPor favor, seleccione la accion que desea realizar: ");
			choice = sc.nextInt();
			
			while (choice < 1 || choice > 11) {
				System.out.print("\nHa introducido una opcion fuera del rango permitido. Introduzca una de las opciones presentadas: ");
				choice = sc.nextInt();
			}
			
			switch (choice) {
			case 1:
				console.printMetadata();
				break;
				
			case 2:
				console.createDirectory();
				break;
			case 3:
				console.createFile();
				break;
			case 4:
				console.changeDirectory();
				break;
			case 5:
				console.clearDirectory();;
				break;
			case 6:
				console.deleteDirectory();;
				break;
			case 7:
				console.deleteFile();;
				break;
			case 8:
				console.listProcesses();
				break;
			case 9:
				console.createDirectory();
				break;
			case 10:
				console.createDirectory();
				break;
			}
		} while (choice != 11); 
		
		System.out.println("\n");

		// DEBUG ZONE
		
	}
}
