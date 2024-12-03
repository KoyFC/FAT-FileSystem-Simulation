package structures;

public abstract class Cluster {
	String name;
	
	public Cluster(String name, int spaceNeeded) {
		
	}

	@Override
	public String toString() {
		return "Cluster [name=" + name + ", spaceNeeded=" + spaceNeeded + "]";
	}
}
