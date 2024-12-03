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
		
		rootDir = new Directory("Root");
	}
	
	public void addFile(File file) {
		
	}
	
	public void addDirectory(Directory directory) {
		
	}
}
