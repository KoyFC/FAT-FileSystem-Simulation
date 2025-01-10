package structures;

import java.util.Timer;
import java.util.TimerTask;

public class Process {
    static int pidCounter = 0; // Static counter for unique PIDs
    final int pid;
    final String name;
    final int action;
    final long interval;
    final String dataName;
    final String dataType;
    final int dataClusterCount;
    boolean isAlive;
    final Timer timer;
    Console console;
    Fat fat;
    
    /**
     * Process constructor that doesn't require any data to be associated with it.
     * @param name The name of the process
     * @param action The action that will be executed by the process (e.g. deleting a file)
     * @param interval The interval, in seconds, in which the process will execute the task (0 = never)
     * @param console A reference to the console of the program
     * @param fat A reference to the FAT of the program
     */
    public Process(String name, int action, float interval, Console console, Fat fat) {
        this.pid = pidCounter++;
        this.name = name;
        this.action = action;
        this.interval = (long) interval;
        this.dataName = null;
        this.dataType = null;
        this.dataClusterCount = 0;
        this.isAlive = true;
        this.timer = new Timer();
        this.console = console;
        this.fat = fat;
    }
    
    /**
     * Process constructor that requires the name and type of the data associated, but not its cluster count.
     * @param name The name of the process
     * @param action The action that will be executed by the process (e.g. deleting a file)
     * @param interval The interval, in seconds, in which the process will execute the task (0 = never)
     * @param dataName The name of the file or directory upon which to act
     * @param dataType The type of the file or directory upon which to act
     * @param console A reference to the console of the program
     * @param fat A reference to the FAT of the program
     */
    public Process(String name, int action, float interval, String dataName, String dataType, Console console, Fat fat) {
        this.pid = pidCounter++;
        this.name = name;
        this.action = action;
        this.interval = (long) interval;
        this.dataName = dataName;
        this.dataType = dataType;
        this.dataClusterCount = 1;
        this.isAlive = true;
        this.timer = new Timer();
        this.console = console;
        this.fat = fat;
    }
    
    /**
     * Complete process constructor
     * @param name The name of the process
     * @param action The action that will be executed by the process (e.g. deleting a file)
     * @param interval The interval, in seconds, in which the process will execute the task (0 = never)
     * @param dataName The name of the file or directory upon which to act
     * @param dataType The type of the file or directory upon which to act
     * @param dataClusterCount The amount of metaclusters the new cluster needs
     * @param console A reference to the console of the program
     * @param fat A reference to the FAT of the program
     */
    public Process(String name, int action, float interval, String dataName, String dataType, int dataClusterCount, Console console, Fat fat) {
        this.pid = pidCounter++;
        this.name = name;
        this.action = action;
        this.interval = (long) interval;
        this.dataName = dataName;
        this.dataType = dataType;
        this.dataClusterCount = dataClusterCount;
        this.isAlive = true;
        this.timer = new Timer();
        this.console = console;
        this.fat = fat;
    }

    /**
     * Method that calls for the performTask() method in the specified interval.
     */
    public void startPeriodicExecution() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (isAlive && interval > 0) {
					performTask();
				} else {
					timer.cancel();
				}
			}
		}, 0, interval + 1); // Interval + 1 is used to avoid issues when interval == 0
    }

    /**
     * Method that performs a specific task based on the action and data variables.
     */
    private void performTask() {
        System.out.println("\nPerforming task for process: " + name + " (PID: " + pid + ")");
        
        switch (action) {
        case 1: // Create DIR
        	Directory newProcessDirectory = new Directory(dataName, fat.firstAvailableClusterIndex(), console.currentDir);
        	fat.addDirectory(newProcessDirectory);
        	break;
        	
        case 2: // Create FILE
        	File newProcessFile = new File(dataName, dataType, fat.firstAvailableClusterIndex(), console.currentDir);
        	fat.addFile(newProcessFile, dataClusterCount);
        	break;
        	
        case 3: // Delete DIR
        	for (Cluster cluster : console.currentDir.content) {
    			if(cluster.name.equals(dataName) && cluster.type.equals("DIR")) {
    				fat.deleteDirectory(cluster.firstClusterIndex);
    				return;
    			}
    		}
        	System.err.println("ERROR203: The DIRECTORY could not be found.");
        	break;
        	
        case 4: // Delete FILE
        	for (Cluster cluster : console.currentDir.content) {
    			if(cluster.name.equals(dataName) && cluster.type.equals(dataType)) {
    				fat.deleteFile(cluster.firstClusterIndex);
    				return;
    			}
    		}
        	System.err.println("ERROR202: The FILE could not be found.");
        	break;
        	
        case 5: // Clear Current DIR
        	console.clearDirectory();
        	break;
        	
        }
    }

    /**
     * Method that kills this process.
     */
    public void terminate() {
        isAlive = false;
        timer.cancel();
        System.out.println("Process " + name + " (PID: " + pid + ") terminated.");
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }
}