package structures;

public abstract class Cluster {
	String name;
	int spaceNeeded;
	int firstCluster;
	
	public Cluster(String name, int spaceNeeded) {
		this.name = name;
		this.spaceNeeded = spaceNeeded;
	}
}
