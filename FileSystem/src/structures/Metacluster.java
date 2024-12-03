package structures;

public class Metacluster {
	boolean available;
	boolean damaged;
	boolean reserved;
	int next;
	boolean end;
	Cluster associatedCluster;
	
	public Metacluster() {
		this.available = true;
		this.damaged = false;
		this.reserved = false;
		this.next = 0;
		this.end = false;
		associatedCluster = new File("", 1);
	}
	
	public Metacluster(boolean available, boolean damaged, boolean reserved, int next, boolean end, Cluster associatedCluster) {
		this.available = available;
		this.damaged = damaged;
		this.reserved = reserved;
		this.next = next;
		this.end = end;
		this.associatedCluster = associatedCluster;
	}
	
	
}
