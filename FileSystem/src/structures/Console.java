package structures;

public class Console {

	Fat fat;
	
	public Console(Fat fat) {
		this.fat = fat;
	}
	
	public void printMetadata() {
		
	}
	
	public void createDirectory(String name) {
		int i = 0;
		if(fat.metadata.size() < fat.clusterNumber) {
			while(fat.metadata.get(i).available==false) {
				i++;
			}
			Directory directory = new Directory(name);
			//fat.metadata.get(i).insertDirectory(directory);
		}
		else{
			System.out.println("Error");
		}
	}
	
	public void createFile() {
		
	}
	
	public void deleteDirectory() {
		
	}
	
	void deleteFile() {
		
	}
	
	public void listProcesses() {
		
	}
	
	public void launchProcess(int process) {
		switch(process) {
		case 1:
			//Empezar temporizador
		}
	}
	
	public void killProcess(int process) {
		
	}
}
