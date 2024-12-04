package structures;

public class File extends Cluster {

	/**
	 * File constructor
	 * @param name name of the new file
	 * @param type file extension (exe, avi, jpg...) - it will be converted into uppercase, so don't worry about it
	 */
	public File(String name, String type, int firstClusterIndex) {		
		super(name, type, firstClusterIndex);
	}

	@Override
	public String toString() {
		return name + "." + type;
	}
}
