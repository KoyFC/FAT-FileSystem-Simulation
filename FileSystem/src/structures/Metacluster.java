package structures;

public class Metacluster {
	public static int clusterCounter = 0;
	
	int index;
	boolean available;
	boolean damaged;
	boolean reserved;
	int next;
	boolean end;
	Cluster associatedCluster;
	
	public Metacluster() {
		this.index = ++clusterCounter;
		this.available = true;
		this.damaged = false;
		this.reserved = false;
		this.next = -1;
		this.end = true;
		associatedCluster = new File("N/A", "N/A");
	}
	
	public Metacluster(boolean available, boolean damaged, boolean reserved, int next, boolean end, Cluster associatedCluster) {
		this.index = ++clusterCounter;
		this.available = available;
		this.damaged = damaged;
		this.reserved = reserved;
		this.next = next;
		this.end = end;
		this.associatedCluster = associatedCluster;
	}

	@Override
	public String toString() {
		return "Metacluster [index=" + index + ", available=" + available + ", damaged=" + damaged + ", reserved="
				+ reserved + ", next=" + next + ", end=" + end + ", associatedCluster=" + associatedCluster + "]";
	}

	
}
