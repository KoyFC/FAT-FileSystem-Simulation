package structures;

public abstract class Cluster {
	String name;
	
	public Cluster(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Cluster [name=" + name + "]";
	}
	
	
}
