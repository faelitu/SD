package ds.module02.socket.tcp.linker;

import java.io.*;
import java.util.*;
import ds.util.Util;

public class Topology {
	
    public static void readNeighbors(int processId, int numberOfProcesses, IntLinkedListOfNeighbors neighbors) {
    	
        Util.println("Reading topology for process #" + processId);
        
        /* file topology+processId on root of project folder
            For each process there should be a file that contains all the neighbors of that process. The
            file name has to follow this format, "topology#", where the # is replaced by the process ID to which this file belongs.
     	    Example:
     	    File name: topology2  (file with neighbors of process of id #2)
     	    File contains: 0 1 5 6
           This shows that process two's neighbors are 0, 1, 5, and 6.
         */
        
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("topology" + processId));) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            
            while (stringTokenizer.hasMoreTokens()) {
                int neighbor = Integer.parseInt(stringTokenizer.nextToken());
                neighbors.add(neighbor); // Add the neighbor presented in the file topology in the neighbors list
            }
            
        } catch (FileNotFoundException e) {
            for (int j = 0; j < numberOfProcesses; j++) {
                if (j != processId) {
                	neighbors.add(j);  //  Adds all neighbors but the processId
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        Util.println(neighbors.toString());
    }
    
    public static void main(String [] args) {
    	
    	// Testcase: File do exists
     	IntLinkedListOfNeighbors list = new IntLinkedListOfNeighbors();
    	 // We do not need to include the neighbors in the list. They are read from a file inside the readNeighbors method.
     	// if the file does not exists, the second argument below is used instead of the file, also inside the  readNeighbors method.
     	Topology.readNeighbors(1, 10, list);  // processId, number of processes, neighbors. Lists all neighbors of process witH id 2.
     	
     	// Testcase: File does not exist
     	IntLinkedListOfNeighbors list2 = new IntLinkedListOfNeighbors();
     	Topology.readNeighbors(2, 5, list2);  // lists all neighbors of process 2
     	
     	
     // Testcase: Read all processes of the topology used throughout the course
     	System.out.println("Reading the topology used throughout the course with 5 processes");
     	IntLinkedListOfNeighbors list3 = new IntLinkedListOfNeighbors();
    	 // We do not need to include the neighbors in the list. They are read from a file inside the readNeighbors method.
     	// if the file does not exists, the second argument below is used instead of the file, also inside the  readNeighbors method.
     	Topology.readNeighbors(1, 5, list3);  // processId, number of processes, neighbors. Lists all neighbors of process witH id 2.
     	list3.clear();
     	Topology.readNeighbors(2, 5, list3);  // processId, number of processes, neighbors. Lists all neighbors of process witH id 2.
    	list3.clear();
     	Topology.readNeighbors(3, 5, list3);  // processId, number of processes, neighbors. Lists all neighbors of process witH id 2.
    	list3.clear();
     	Topology.readNeighbors(4, 5, list3);  // processId, number of processes, neighbors. Lists all neighbors of process witH id 2.
    	list3.clear();



    }
}
