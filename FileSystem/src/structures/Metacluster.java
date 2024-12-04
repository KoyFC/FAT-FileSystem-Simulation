package structures;

public class Metacluster {	
	public int index;
	boolean available;
	boolean damaged;
	boolean reserved;
	int next;
	boolean end;
	Cluster associatedData;
	
	public Metacluster() {
		this.index = 0;
		this.available = true;
		this.damaged = false;
		this.reserved = false;
		this.next = -1;
		this.end = true;
		associatedData = new File("N/A", "N/A", -1);
	}
	
	public Metacluster(int index) {
		this.index = index;
		this.available = true;
		this.damaged = false;
		this.reserved = false;
		this.next = -1;
		this.end = true;
		associatedData = new File("N/A", "N/A", -1);
	}
	
	// Probably not useful. Remove if not needed.
	public Metacluster(int index, boolean available, boolean damaged, boolean reserved, int next, boolean end, Cluster associatedCluster) {
		this.index = index;
		this.available = available;
		this.damaged = damaged;
		this.reserved = reserved;
		this.next = next;
		this.end = end;
		this.associatedData = associatedCluster;
	}

	@Override
	public String toString() {
		return "Metacluster [index=" + index + ", available=" + available + ", damaged=" + damaged + ", reserved="
				+ reserved + ", next=" + next + ", end=" + end + ", associatedCluster=" + associatedData + "]";
	}
}
