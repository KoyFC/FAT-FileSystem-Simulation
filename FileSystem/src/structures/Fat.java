package structures;
import java.util.ArrayList;

public class Fat {
	public int clusterNumber;
	public Directory rootDir;
	
	public ArrayList<Metacluster> metadata = new ArrayList<Metacluster>();
	// ArrayList<Cluster> data = new ArrayList<Cluster>();
	
	public Fat(int clusterNumber) {
		this.clusterNumber = clusterNumber;
		
		for (int i = 0; i < clusterNumber; i++) {
            metadata.add(new Metacluster());
        }
		
		rootDir = new Directory("ROOT");
	}
	
	public void addFile(File file, int fileSize) {
		if (availableClusters() > 0) {	
			Metacluster currentMetaCluster;
			Metacluster nextMetaCluster = firstAvailableCluster();
			
			for (int i = 0; i < fileSize - 1; i++) { // -1 to change the "next" and "end" for the last cluster variables separately
				// We will change data from the current cluster and immediately find the next one
				currentMetaCluster = nextMetaCluster;
				nextMetaCluster = firstAvailableCluster();
				
				currentMetaCluster.associatedCluster = file;
				currentMetaCluster.available = false;
				currentMetaCluster.next = nextMetaCluster.index;
				currentMetaCluster.end = false;
			}
			currentMetaCluster = nextMetaCluster;
			
			currentMetaCluster.associatedCluster = file;
			currentMetaCluster.available = false;
			currentMetaCluster.end = true;
		} else {
			System.err.println("ERROR101: Could not create new FILE. No clusters are available.");
		}
	}
	
	public void addDirectory(Directory directory) {
		if (availableClusters() > 0) {
			Metacluster currentMetaCluster = firstAvailableCluster();
			currentMetaCluster.available = false;
			currentMetaCluster.end = true;
			currentMetaCluster.associatedCluster = directory;
		} else {
			System.err.println("ERROR102: Could not create new DIRECTORY. No clusters are available.");
		}
	}
	
	/**
	 * @return The first cluster in the ArrayList with the available flag set to true
	 */
	public Metacluster firstAvailableCluster() {
		for (Metacluster current : metadata) {
			if (current.available) return current;
		}
		
		System.err.println("ERROR100: There are no more clusters left!");
		return null;
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
