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
			Metacluster nextMetaCluster = firstAvailableCluster();
			Metacluster currentMetaCluster;
			
			file.firstClusterIndex = firstAvailableClusterIndex();
			
			for (int i = 0; i < fileSize - 1; i++) { // -1 to change the "next" and "end" for the last cluster's variables separately
				// We will change data from the current cluster and immediately find the next one
				currentMetaCluster = nextMetaCluster;
				
				currentMetaCluster.associatedData = file;
				currentMetaCluster.available = false;
				currentMetaCluster.next = nextMetaCluster.index;
				currentMetaCluster.end = false;
				
				nextMetaCluster = firstAvailableCluster();
				// System.out.println(currentMetaCluster); // DEBUG
			}
			currentMetaCluster = nextMetaCluster;
			
			currentMetaCluster.available = false;
			currentMetaCluster.end = true;
			currentMetaCluster.associatedData = file;
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
	 * Method to set the "available" variable of the metacluster a directory is in to true.
	 * @param clusterIndex The directory's index.
	 */
	public void deleteDirectory(int clusterIndex) {
		int index = clusterIndex;
		Metacluster deletedMetacluster = metadata.get(index);
		
		// Return in case the cluster is reserved. This is only the case with the root directory, so as to not delete it.
		// This shouldn't happen in the first place, but just in case.
		if (deletedMetacluster.reserved) {
			System.err.println("...Why did you even try deleting the root directory...?");
			return; 
		}
		
		deletedMetacluster.available = true;
	}
	
	/**
	 * Method to set all the "available" variables in each file's metaclusters to true.
	 * @param firstClusterIndex The index of the file's first cluster.
	 */
	public void deleteFile(int firstClusterIndex) {
		int index = firstClusterIndex;
		Metacluster deletedMetacluster = metadata.get(index);
		Metacluster aux = deletedMetacluster;
		
		deletedMetacluster.available = true;
		
		if (deletedMetacluster.end) {
			System.out.println("\n\tFile deleted successfully.\n");
			return;
		}
		
		// In case the file is in many clusters, we go through each one and set available to true
		while (!aux.end) {
			index = aux.next;
			
			if (index == -1) return; // This is to prevent an infinite loop (just in case)
			
			aux = metadata.get(index + 1); // Our clusters start from 0 but indexes start from 1, so we need to add 1 to not get stuck in a loop
			aux.available = true;
			
			if (aux == deletedMetacluster) {
	            System.err.println("FATAL ERROR: Detected a cycle in the cluster chain.");
	            return; // Preventing another infinite loop due to cycling (this happened a lot during debug)
	        }
		}
		System.out.println("\n\tFile deleted successfully.\n");
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
