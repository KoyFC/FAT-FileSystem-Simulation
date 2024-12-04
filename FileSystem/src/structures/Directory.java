package structures;

import java.util.ArrayList;

public class Directory extends Cluster {

	public ArrayList<Cluster> content; // An arraylist that stores the data of its contents (NOT its metadata)
	public Directory parentDir;
	
	/**
	 * Constructor for a new directory.
	 * @param name The name of the new directory.
	 */
	public Directory(String name, int firstClusterIndex, Directory parentDir) {
		super(name, "DIR", firstClusterIndex);
		this.content = new ArrayList<Cluster>();
		this.parentDir = parentDir;
	}

	public void addContent(Cluster cluster) {
        content.add(cluster);
    }
	
	@Override
	public String toString() {
		return name;
	}
}
