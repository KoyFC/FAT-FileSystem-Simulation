package structures;

public class File extends Cluster {

	/**
	 * Constructor for a new file.
	 * @param name Name of the new file
	 * @param type File extension (exe, avi, jpg...) - 
	 * will be converted to lowercase for consistency and checking for duplicates.
	 */
	public File(String name, String type, int firstClusterIndex) {		
		super(name, type.toLowerCase(), firstClusterIndex);
	}

	@Override
	public String toString() {
		return name + "." + type;
	}
}
