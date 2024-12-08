package structures;
import java.util.ArrayList;

public class Fat {
	public int clusterNumber;
	public Directory rootDir;
	public Console console;
	
	public ArrayList<Metacluster> metadata;
	// ArrayList<Cluster> data = new ArrayList<Cluster>();
	
	/**
	 * Constructor that initializes the file system with a maximum number of clusters, creating empty Metaclusters and the Root directory.
	 * @param clusterNumber
	 */
	public Fat(int clusterNumber) {
		this.clusterNumber = clusterNumber;
		metadata = new ArrayList<Metacluster>();
		
		rootDir = new Directory("ROOT", 0, null);
		Metacluster rootDirMetaCluster = new Metacluster(0, false, false, true, -1, true, rootDir);
		
		metadata.add(rootDirMetaCluster);
		
		for (int i = 1; i <= clusterNumber; i++) {
            metadata.add(new Metacluster(i));
        }
	}
	
	/**
	 * Adds a new directory to the ArrayList if there are any clusters available.
	 * @param directory the directory that will be inserted, if possible.
	 */
	public void addDirectory(Directory directory) {
		// Checking if an identical directory already exists
		for (Metacluster comparison : metadata) {
			if (comparison.associatedData.name.equals(directory.name) && 
					comparison.associatedData.type.equals(directory.type)){
				System.err.println("ERROR103-2: An identical DIRECTORY already exists in the \"" + console.currentDir + "\" directory!");
				return;
			}
		}
		
		if (availableClusters() > 0) {
			Metacluster currentMetaCluster = firstAvailableCluster();
			currentMetaCluster.available = false;
			currentMetaCluster.end = true;
			currentMetaCluster.associatedData = directory;
			
			directory.parentDir = console.currentDir;
			console.currentDir.addContent(directory);
			
			System.out.println("\n\tDIRECTORY \"" + directory + "\" was created successfully.");
		} 
		else {
			System.err.println("ERROR101-1: Could not create new DIRECTORY. No clusters are available.");
		}
	}
	
	/**
	 * Adds a new file to the ArrayList if there are enough clusters available and the file doesn't already exist.
	 * @param file the file that will be inserted, if possible.
	 * @param fileSize the size of the file, AKA the number of clusters it needs.
	 */
	public void addFile(File file, int fileSize) {
		// Checking if an identical file already exists
		for (Metacluster comparison : metadata) {
			if (comparison.associatedData.name.equals(file.name) && 
					comparison.associatedData.type.equals(file.type)){
				System.err.println("ERROR103-2: An identical FILE already exists in the \"" + console.currentDir + "\" directory!");
				return;
			}
		}
		
		if (availableClusters() >= fileSize) {	
			Metacluster currentMetaCluster;
			Metacluster nextMetaCluster;
			
			file.firstClusterIndex = firstAvailableCluster().index;
			
			for (int i = 0; i < fileSize - 1; i++) { // -1 to change the "next" and "end" for the last cluster variables separately
				// We will change data from the current cluster and immediately find the next one
				nextMetaCluster = firstAvailableCluster();
				currentMetaCluster = nextMetaCluster;
				
				currentMetaCluster.associatedData = file;
				currentMetaCluster.available = false;
				currentMetaCluster.next = nextMetaCluster.index;
				currentMetaCluster.end = false;
				
				// System.out.println(currentMetaCluster); // DEBUG
			}
			currentMetaCluster = firstAvailableCluster();
			
			currentMetaCluster.associatedData = file;
			currentMetaCluster.available = false;
			currentMetaCluster.end = true;
			console.currentDir.addContent(file);
			
			// System.out.println(currentMetaCluster); // DEBUG
			System.out.println("\n\tFILE \"" + file + "\" was created successfully.");
		} 
		else if (availableClusters() <= 0){
			System.err.println("ERROR101-2: Could not create new FILE. No clusters are available.");
		} 
		else {
			System.err.print("ERROR102: Could not insert new FILE \"" + file + "\". ");
			System.err.println("This FILE needs " + fileSize + " clusters, but there are only " + availableClusters() + " clusters currently available!");			
		}
	}
	
	/**
	 * @return The first cluster in the ArrayList with the available flag set to true
	 */
	public Metacluster firstAvailableCluster() {
		for (Metacluster current : metadata) {
			if (current.available) return current;
		}
		
		//System.err.println("ERROR100: There are no clusters available!");
		return null;
	}
	
	public int firstAvailableClusterIndex() {
		for (Metacluster current : metadata) {
			if (current.available) return current.index;
		}
		
		//System.err.println("ERROR100: There are no clusters available!");
		return -1;
	}
	
	/**
	 * @return The number of clusters currently available.
	 */
	public int availableClusters() {
		int available = 0;
		
		for (Metacluster current : metadata) 
			if (current.available) available++;
		
		return available;
	}
}
