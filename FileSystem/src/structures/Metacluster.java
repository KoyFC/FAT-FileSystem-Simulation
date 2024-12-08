package structures;

public class Metacluster {	
	public int index;
	boolean available;
	boolean damaged;
	boolean reserved;
	int next;
	boolean end;
	Cluster associatedData;
	
	/**
	 * Empty Metacluster constructor. 
	 * Initializes the metacluster with an index of -1 (which should ensure no accidental calls to any other metacluster).
	 */
	public Metacluster() {
		this.index = -1;
		this.available = true;
		this.damaged = false;
		this.reserved = false;
		this.next = -1;
		this.end = true;
		associatedData = new File("N/A", "N/A", -1);
	}
	
	/**
	 * Empty Metacluster constructor with a specific index.
	 * @param index The index of the new metacluster.
	 */
	public Metacluster(int index) {
		this.index = index;
		this.available = true;
		this.damaged = false;
		this.reserved = false;
		this.next = -1;
		this.end = true;
		associatedData = new File("N/A", "N/A", -1);
	}
	
	/**
	 * Full Metacluster constructor.
	 * @param index The index of the new metacluster.
	 * @param available Whether the metacluster is available.
	 * @param damaged Whether the metacluster is damaged (unless explicitly specified otherwise, set to false).
	 * @param reserved Whether the metacluster is reserved (should only be true for the root directory in this simulation).
	 * @param next The index of the next cluster, in case we need a file requiring multiple.
	 * @param end Only true in the last metacluster of a file or directory.
	 * @param associatedCluster A file or directory that will be linked to this metacluster.
	 */
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
