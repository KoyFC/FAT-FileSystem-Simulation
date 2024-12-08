package structures;

public abstract class Cluster {
	String name;
	String type;
	int firstClusterIndex;
	
	// Will be called by the children of this class.
	public Cluster(String name, String type, int firstClusterIndex) {
		this.name = name;
		this.type = type;
		this.firstClusterIndex = firstClusterIndex;
	}
}
