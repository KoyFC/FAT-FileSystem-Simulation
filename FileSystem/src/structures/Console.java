package structures;
import java.util.ArrayList;
import java.util.Scanner;

public class Console {

	Fat fat;
	public Directory currentDir;
	int processCounter;
	ArrayList<Process> processList;

	public Console(Fat fat) {
	    this.fat = fat;
	    this.currentDir = fat.rootDir;
	    fat.console = this;
	    processCounter = 1;
	    processList = new ArrayList<Process>();
	    
	    Process consoleProcess = new Process("Console", 0, 0, null, null, this, fat);
        processList.add(consoleProcess);
        System.out.println("Initialized console process with PID: " + consoleProcess.getPid());
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
			if (metacluster.index != 0) System.out.println(metacluster);
		}
	}
	
	public void printData() {
		System.out.println(String.format("\n%-15s %-10s %-10s", "Name", "Type", "Index"));
        System.out.println("-------------------------------------------");
        
		for (Cluster cluster : currentDir.content) {
		    cluster.printCluster();
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
            System.out.println("\tPID: " + process.getPid() + ", Name: " + process.getName() + ", Status: " + (process.isAlive() ? "Alive" : "Terminated"));
        }
    }
	
	/**
	 * Method that asks the user for the name of the process they want to create,
	 * then asks them for the action they want it to perform,
	 * then asks them the interval in which the process should be executed (0 = never).
	 * Based on the action selected, they may be prompted to input the name of the file, type, or cluster count.
	 * 
	 * WARNING: A big switch may found be here. Proceed with caution.
	 */
	public void createProcess() {
		System.out.print("Please input the name of the process you want to create: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		
		System.out.println("\nThese are what the actions you can make your process do: ");
		System.out.println("\t1. Create Directory");
		System.out.println("\t2. Create File");
		System.out.println("\t3. Delete Directory");
		System.out.println("\t4. Delete File");
		System.out.println("\t5. Clear directory");
		System.out.print("\nPlease input the number of what you want the process to do: ");
		int action = sc.nextInt();
		
		while (action < 1 || action > 5) {
			System.err.print("The value you entered is outside the range. Please input the number again: ");
			action = sc.nextInt();
		}
		
		System.out.print("\nPlease input the interval in SECONDS in which you want the process to execute (0 never starts the task): ");
		float interval = sc.nextFloat() * 1000;
		
		while (interval < 0) {
			System.err.print("The value you entered can not be negative. Please input the number again: ");
			interval = sc.nextFloat();
		}
		sc.nextLine(); // Clearing the input buffer
		
		Process newProcess = null;
		String clusterName = "", clusterType = "";
		switch (action) {
		case 1:
			System.out.println("\n\tThis will try to create the specified Directory in the current directory each time it is executed.");
			System.out.print("\nPlease input the name of the Directory the process should create: ");
			clusterName = sc.nextLine();
			
			newProcess = new Process(name, action, interval, clusterName, "DIR", 1, this, fat);
			break;
			
		case 2:
			System.out.println("\n\tThis will try to create the specified File in the current directory each time it is executed.");
			System.out.print("\nPlease input the name of the File the process should create: ");
			clusterName = sc.nextLine();
			System.out.print("\nPlease input the name of the File the process should create: ");
			clusterType = sc.nextLine();
			System.out.print("\nPlease input the size of the File the process should create: ");
			int clusterCount = sc.nextInt();
			
			newProcess = new Process(name, action, interval, clusterName, clusterType, clusterCount, this, fat);
			break;
			
		case 3:
			System.out.println("\n\tThis will try to delete the specified Directory in the current directory each time it is executed.");
			System.out.print("\nPlease input the name of the Directory the process should delete: ");
			clusterName = sc.nextLine();
			
			newProcess = new Process(name, action, interval, clusterName, "DIR", 1, this, fat);
			break;
			
		case 4:
			System.out.println("\n\tThis will try to delete the specified File in the current directory each time it is executed.");
			System.out.print("\nPlease input the name of the File the process should delete: ");
			clusterName = sc.nextLine();
			System.out.print("\nPlease input the name of the File the process should delete: ");
			clusterType = sc.nextLine();
			
			newProcess = new Process(name, action, interval, clusterName, clusterType, this, fat);
			break;
			
		case 5:
			System.out.println("\n\tThis will clear the current Directory each time it is executed.");
			newProcess = new Process(name, action, interval, this, fat);
			break;
		}
		
        processList.add(newProcess);
        newProcess.startPeriodicExecution();
        System.out.println("Process " + name + " (PID: " + newProcess.getPid() + ") started.");
    }
	
	/**
	 * Terminates a specific process. The user must input its PID.
	 */
	public void terminateProcess() {
		System.out.print("Please input the PID of the process you want to kill: ");
		Scanner sc = new Scanner(System.in);
		int pid = sc.nextInt();
		
        for (Process process : processList) {
            if (process.getPid() == pid) {
            	if (pid == 0) {
                    System.out.println("Console process (PID 0) is terminating. Exiting the program...");
                    System.exit(0); // Terminates the program
                }
                process.terminate();
                return;
            }
        }
        System.err.println("ERROR500: Process with PID " + pid + " was not found.");
    }
}
