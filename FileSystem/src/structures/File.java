package structures;

public class File extends Cluster {
	
	int spaceNeeded;

	/**
	 * File constructor
	 * @param name name of the new file
	 * @param spaceNeeded number of clusters needed for the file
	 */
	public File(String name, int spaceNeeded) {
		super(name);
		this.spaceNeeded = 0;
	}

}
