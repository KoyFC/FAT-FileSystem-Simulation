package structures;

public class File extends Cluster {

	/**
	 * File constructor
	 * @param name name of the new file
	 * @param spaceNeeded number of clusters needed for the file
	 * @param firstCluster the first cluster
	 */
	public File(String name, int spaceNeeded) {
		super(name, spaceNeeded);
	}

}
