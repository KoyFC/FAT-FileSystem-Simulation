# Simulated FAT System

This repository contains a simplified simulation of a file system based on the FAT (File Allocation Table) system. It provides classes that model the structure and functionality of a file system, including directories, files, and the management of metaclusters. Additionally, it includes a `Console` class to interact with the system.

---

## Main Classes

### 1. `Directory`

- Represents a directory.
- Attributes:
  - `content`: List of files and subdirectories.
- Key Methods:
  - `addContent(Cluster cluster)`: Adds a cluster to the directory.
  - `removeContent(Cluster cluster)`: Removes a cluster from the directory.

### 2. `File`

- Represents a file.
- Attributes:
  - `name`, `type`, `firstClusterIndex`, `parentDir` (inherited).

### 3. `Metacluster`

- Models the metadata of a cluster.
- Attributes:
  - `index`: Index of the metacluster.
  - `available`, `damaged`, `reserved`, `next`, `end`: States of the metacluster.
  - `associatedData`: Associated cluster.

### 4. `FAT`

- Handles core file system operations.
- Key Methods:
  - `addDirectory(Directory directory)`: Adds a directory to the system.
  - `addFile(File file, int size)`: Adds a file to the system with a specified size.
  - `deleteFile(int clusterIndex)`: Deletes a file starting from a given cluster index.
  - `clearDirectory()`: Clears all contents of the current directory.
  - `firstAvailableClusterIndex()`: Finds the first available cluster index for allocation.

### 5. `Console`

- Main class for interacting with the system.
- Key Features:
  - **Directory Management:**
    - `changeDirectory(String path)`: Change the current directory.
    - `createDirectory()`: Create a new directory.
    - `clearDirectory()`: Clear the contents of a directory.
  - **File Management:**
    - `createFile()`: Create a file.
    - `deleteFile()`: Delete a file.
  - **Processes:**
    - `listProcesses()`: List running processes.
    - `create5secondsProcess(Process process)`: Create a process that clears temporary directories every 5 seconds.

---

## Installation

1. Clone this repository:

   ```bash
   git clone https://github.com/KoyFC/SSOO-Trabajo-Grupal
   ```

2. Import the project into Eclipse:

   - Open Eclipse.
   - Select **File > Import > Existing Projects into Workspace**.
   - Browse to the cloned repository folder and click **Finish**.

3. Build and run the project.