package structures;
import java.util.Scanner;

public class Console {

	Fat fat;
	
	public Console(Fat fat) {
		this.fat = fat;
	}
	
	public void mainMessage() {
		
	}
	
	public void printMetadata() {
		
	}
	
	public void createDirectory() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nPlease input the NAME of the new DIRECTORY: ");
		String name = sc.nextLine();
		while(name == "") {
			System.err.println("ERROR 200: The NAME of the DIRECTORY can not be empty. Please input another name: ");
			name = sc.nextLine();
		}
		
		Directory newDirectory = new Directory(name);
		fat.addDirectory(newDirectory);
	}
	
	public void createFile() { // Hacer una llamada al método addFile de la clase Fat
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nPlease input the NAME of the new FILE: ");
		String name = sc.nextLine();
		while(name == "") {
			System.err.println("ERROR 200: The NAME of the FILE can not be empty. Please input another name: ");
			name = sc.nextLine();
		}
		
		System.out.println("\nPlease input the TYPE or EXTENSION of the new FILE: ");
		String type = sc.nextLine();
		while(type == "") {
			System.err.println("ERROR 200: The TYPE of the FILE can not be empty. Please input another name: ");
			type = sc.nextLine();
		}
		
		System.out.println("\nPlease input the SIZE of the new FILE: ");
		int size = sc.nextInt();
		while(size <= 0) {
			System.err.println("ERROR 201: The SIZE can not be lower than 1. Please input another number: ");
			size = sc.nextInt();
		}
		
		File newFile = new File(name, type);
		
		fat.addFile(newFile, size);
	}
	
	public void deleteDirectory() {
		
	}
	
	void deleteFile() {
		
	}
	
	public void listProcesses() {
		
	}
	
	public void launchProcess(int process) {
		switch(process) {
		case 1:
			//Empezar temporizador
		}
	}
	
	public void killProcess(int process) {
		
	}
}
