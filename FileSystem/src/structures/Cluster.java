package structures;

public abstract class Cluster {
	String name;
	String type;
	
	public Cluster(String name, String type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Cluster [name=" + name + ", type=" + type + "]";
	}
}
