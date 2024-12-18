package structures;

public abstract class Cluster {
	String name;
	String type;
	int firstClusterIndex;
	public Directory parentDir;
	public String globalPath;
	
	// Will be called by the children of this class.
	public Cluster(String name, String type, int firstClusterIndex, Directory parentDir) {
		this.name = name;
		this.type = type;
		this.firstClusterIndex = firstClusterIndex;
		this.parentDir = parentDir;
		this.globalPath = calculateGlobalPath();
	}
	
	private String calculateGlobalPath() {
	    String path = "";
	    Directory currentDir = parentDir;

	    while (currentDir != null) {
	        path = currentDir.name + "/" + path; // Adding the name of the current directory at the BEGINNING of the String
	        currentDir = currentDir.parentDir;
	    }

	    return path;
	}
}
