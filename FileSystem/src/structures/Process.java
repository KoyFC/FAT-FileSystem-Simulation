package structures;

import java.util.Timer;
import java.util.TimerTask;

public class Process {
    static int pidCounter = 0; // Static counter for unique PIDs
    final int pid;
    final String name;
    boolean isAlive;
    final Timer timer;
    Console console;
    Fat fat;
    Directory tmp;

    public Process(String name, Console console, Fat fat) {
        this.pid = pidCounter++;
        this.name = name;
        this.isAlive = true;
        this.timer = new Timer();
        this.console = console;
        this.fat = fat;
    }

    public void startPeriodicExecution() {
    	if (name.toLowerCase().equals("borratmpcada5segundos")) {
    		timer.scheduleAtFixedRate(new TimerTask() {
    			@Override
    			public void run() {
    				if (isAlive) {
    					performTask();
    				} else {
    					timer.cancel();
    				}
    			}
    		}, 0, 5000);
    	}
    }

    private void performTask() {
        System.out.println("\nPerforming task for process: " + name + " (PID: " + pid + ")");
        
        for (Cluster cluster : console.currentDir.content) {
        	if (cluster.type.equals("DIR") && cluster.name.toLowerCase().equals("tmp")) {
        		int index = cluster.firstClusterIndex;
        		fat.deleteDirectory(index);
        		return;
        	}
        }
        System.err.println("ERROR: Couldn't find DIRECTORY \"TMP\" inside the current directory.");
    }

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