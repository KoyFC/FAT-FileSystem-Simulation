package structures;
import java.util.ArrayList;

public class Fat {
	int clusterNumber;
	
	ArrayList<Metacluster> metadata = new ArrayList<Metacluster>();
	// ArrayList<Cluster> data = new ArrayList<Cluster>();
	
	public Fat(int clusterNumber) {
		this.clusterNumber = clusterNumber;
		
		for (int i = 0; i < clusterNumber; i++) {
            metadata.add(new Metacluster());
        }
	}
}
