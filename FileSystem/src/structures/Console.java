package structures;
import java.util.ArrayList;
import java.util.Scanner;

public class Console {

	Fat fat;
	public Directory currentDir;
	ArrayList<Process> processList = new ArrayList<Process>();
	
	public Console(Fat fat) {
		this.fat = fat;
		this.currentDir = fat.rootDir;
		fat.console = this;

		Process consoleProcess = new Process(0, "Console");
		processList.add(consoleProcess);
	}
	
	public void mainMessage() {
		
	}
	
	public void changeDirectory(String path) {
        if (path.equals("..")) { // Parent dir
        	if (currentDir.parentDir != null) {
        		currentDir = currentDir.parentDir;
        		System.out.println("Changed to parent directory: " + currentDir);
        	}
        	else System.err.println("ERROR301: Tried to change directory, but there is no parent directory for \"" + currentDir + "\"!");
        } else {
            // Searching through the current directory's content for the specified directory
            for (Cluster cluster : currentDir.content) {
                if (cluster.type.equals("DIR") && cluster.name.equals(path)) {
                    currentDir = (Directory) cluster;
                    System.out.println("Successfully changed to directory: " + currentDir.name);
                    return;
                }
            }
            System.err.println("ERROR300: Directory " + path + " was not found inside of \"" + currentDir + "\"!");
        }
    }
	
	public void printMetadata() {
		
	}
	
	public void createDirectory() { // Hace una llamada al método addDirectory() de la clase Fat
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nPlease input the NAME of the new DIRECTORY: ");
		String name = sc.nextLine();
		while(name == "") {
			System.err.println("ERROR 200: The NAME of the DIRECTORY can not be empty. Please input another name: ");
			name = sc.nextLine();
		}
		
		Directory newDirectory = new Directory(name, fat.firstAvailableClusterIndex(), currentDir);
		fat.addDirectory(newDirectory);
	}
	
	public void createFile() { // Hace una llamada al método addFile() de la clase Fat
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
		
		File newFile = new File(name, type, fat.firstAvailableClusterIndex());
		
		fat.addFile(newFile, size);
	}
	
	public void deleteDirectory() {
		
	}
	
	public void deleteFile() { //
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nPlease input the NAME of the FILE you want to DELETE: ");
		String name = sc.nextLine();
		while(name == "") {
			System.err.println("ERROR 200: The NAME of the FILE can not be empty. Please input another name: ");
			name = sc.nextLine();
		}
		System.out.println("\nPlease input the TYPE or EXTENSION of the FILE you want to DELETE: ");
		String type = sc.nextLine();
		while(type == "") {
			System.err.println("ERROR 200: The TYPE of the FILE can not be empty. Please input another name: ");
			type = sc.nextLine();
		}
		
		for (Cluster cluster : currentDir.content ) {
			if( cluster.name.equals(name) && cluster.type.equals(type.toLowerCase()));{
				//fat.deleteFile
				return;
			}
		}
		
		System.err.println("ERROR 202: The FILE could not be found. ");
		
	}
	
	public void listProcesses() {
		System.out.println("List of running processes:");
		for (Process process : processList) {
			System.out.println("\n\tProcess ID: " + process.id);
			System.out.println("\tProcess Name: " + process.name + "\n");
		}
	}
	
	public void launchProcess(Process newProcess) {
		processList.add(newProcess);
	}
	
	public void killProcess(int process) {
		
	}
}
