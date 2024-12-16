package structures;

public abstract class Cluster {
	String name;
	String type;
	int firstClusterIndex;
	public Directory parentDir;
	String globalPath;
	
	// Will be called by the children of this class.
	public Cluster(String name, String type, int firstClusterIndex, Directory parentDir) {
		this.name = name;
		this.type = type;
		this.firstClusterIndex = firstClusterIndex;
		this.parentDir = parentDir;
		this.globalPath = calculateGlobalPath();
	}
	
	// Dynamically calculates the global path
    private String calculateGlobalPath() {
        StringBuilder path = new StringBuilder();
        Directory currentDir = parentDir;

        // Traverse up the hierarchy
        while (currentDir != null) {
            path.insert(0, currentDir.name + "/");
            currentDir = currentDir.parentDir;
        }

        // If this is a directory, append its own name to the path
        if (type.equals("DIR")) {
            path.append(name).append("/");
        }

        return path.toString();
    }
}
