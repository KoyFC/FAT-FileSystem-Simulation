package structures;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Console {

	Fat fat;
	public Directory currentDir;
	ArrayList<Process> processList = new ArrayList<Process>();
	int processCounter;
	
	public Console(Fat fat) {
		this.fat = fat;
		this.currentDir = fat.rootDir;
		fat.console = this;
		processCounter=1;

		Process consoleProcess = new Process(0, "Console");
		processList.add(consoleProcess);
	}
	
	public void mainMessage() {
		
	}
	
	/**
	 * Method that changes the directory under which all operations will be performed.
	 * @param path The new directory to go to. To go back to a parent directory, use ".."
	 */
	public void changeDirectory() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please input the name of the directory you want to chage to:");
		String path = sc.nextLine();
		
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
	
	/**
	 * Method that prints each metacluster in the FAT associated with the console, taking advantage of the Metacluster class' toString() override.
	 */
	public void printMetadata() {
		for (Metacluster metacluster : fat.metadata ) {
			if (metacluster.index != 0) System.out.println( metacluster );
		}
	}
	
	/**
	 * Method that asks the user the name of the directory they want to create and adds it to the system.
	 */
	public void createDirectory() { // Hace una llamada al método addDirectory() de la clase Fat
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\nPlease input the NAME of the new DIRECTORY: ");
		String name = sc.nextLine();
		while(name == "") {
			System.err.println("ERROR200: The NAME of the DIRECTORY can not be empty. Please input another name: ");
			name = sc.nextLine();
		}
		
		Directory newDirectory = new Directory(name, fat.firstAvailableClusterIndex(), currentDir);
		fat.addDirectory(newDirectory);
	}
	
	/**
	 * Method that asks the user relevant details about the file they want to create and adds it to the system.
	 */
	public void createFile() { // Hace una llamada al método addFile() de la clase Fat
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\nPlease input the NAME of the new FILE: ");
		String name = sc.nextLine();
		while(name == "") {
			System.err.print("ERROR200: The NAME of the FILE can not be empty. Please input another name: ");
			name = sc.nextLine();
		}
		
		System.out.print("\nPlease input the TYPE or EXTENSION of the new FILE: ");
		String type = sc.nextLine();
		while(type == "") {
			System.err.print("ERROR200: The TYPE of the FILE can not be empty. Please input another name: ");
			type = sc.nextLine();
		}
		
		System.out.print("\nPlease input the SIZE of the new FILE: ");
		int size = sc.nextInt();
		while(size <= 0) {
			System.err.print("ERROR201: The SIZE can not be lower than 1. Please input another number: ");
			size = sc.nextInt();
		}
		
		File newFile = new File(name, type, fat.firstAvailableClusterIndex(), currentDir);
		
		fat.addFile(newFile, size);
	}
	
	/**
	 * Method that clears all the contents of the current directory.
	 */
	public void clearDirectory() {
		System.out.println("All contents inside the directory \"" + currentDir + "\" will be deleted.\n");
		
		int deleteCounter = fat.clearDirectory();
		if (deleteCounter == 0) System.out.println("\tERROR400: Tried to clear the current directory \"" + currentDir + "\", but it was already empty!");
		else System.out.println("\tThe current directory \"" + currentDir + "\" was cleared successfully.");
	}
	
	/**
	 * Method that searches for the directory that the user inputs. If found, it calls the Fat class' method to delete it.
	 */
	public void deleteDirectory() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\nPlease input the NAME of the DIRECTORY you want to DELETE: ");
		String name = sc.nextLine();
		while(name == "") {
			System.err.print("ERROR200: The NAME of the DIRECTORY can not be empty. Please input another name: ");
			name = sc.nextLine();
		}
		
		for (Cluster cluster : currentDir.content) {
			if(cluster.name.equals(name) && cluster.type.equals("DIR")) {
				fat.deleteDirectory(cluster.firstClusterIndex);
				return;
			}
		}
		
		System.err.println("ERROR203: The DIRECTORY could not be found.");
	}
	
	/**
	 * Method that searches for the file that the user inputs. If found, it calls the Fat class' method to delete it.
	 */
	public void deleteFile() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\nPlease input the NAME of the FILE you want to DELETE: ");
		String name = sc.nextLine();
		while(name == "") {
			System.err.print("ERROR200: The NAME of the FILE can not be empty. Please input another name: ");
			name = sc.nextLine();
		}
		
		System.out.print("\nPlease input the TYPE or EXTENSION of the FILE you want to DELETE: ");
		String type = sc.nextLine().toLowerCase();
		while(type == "") {
			System.err.print("ERROR200: The TYPE of the FILE can not be empty. Please input another name: ");
			type = sc.nextLine();
		}
		
		for (Cluster cluster : currentDir.content) {
			if(cluster.name.equals(name) && cluster.type.equals(type)) {
				fat.deleteFile(cluster.firstClusterIndex);
				return;
			}
		}
		
		System.err.println("ERROR202: The FILE could not be found.");
	}
	
	/**
	 * Method that lists all the processes that are currently running. Process 0 is reserved for the console.
	 */
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
	
	public void killProcess(java.lang.Process process) throws IOException {
		

		process.destroy();
		
	}
	
	public void create5secondsProcess(java.lang.Process proceso)
	{
		Scanner sc = new Scanner(System.in);
		int option;
		do
		{
			System.out.println("Select the options below");
			System.out.println("\n\t0-Cancel");
			System.out.println("\n\t1-Create a process that clears TMP directory every 5 seconds");
			option = sc.nextInt();
		}while(option!=0||option!=1);
		
		if(option==0)
		{
			return;
		}
		else if(option==1)
		{
			System.out.println("(this process will kill tmp every five seconds)");
			String name = "BorraTMPcada5segundos";
			
			processCounter++;
			
			while(proceso.isAlive())
			{
				alarmFiveSeconds();
			}
		}
	}
	
	public void alarmFiveSeconds()
	{
		
		// 1 - Crear un Timer:
		Timer nombreTemporizador = new Timer();

		// 2 - Definir la tarea que se tiene que ejecutar:
		TimerTask tareaEjecutar = new TimerTask() {
		    public void run() {
		        fat.clearDirectory();
		        nombreTemporizador.cancel(); // Puesto que ya no es necesario tener el temporizador, se borra al ejecutar la acción evitando que siga consumiendo recursos innecesariamente
		    }
		};

		// 3 - Programar la tarea:
		nombreTemporizador.schedule(tareaEjecutar, 5000); // Esto funciona con milisegundos
	}
}
