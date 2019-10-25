package ds.module02.socket.tcp.linker;

import java.util.*;
import ds.util.Util;
import java.io.*;

public class Linker {
	
    private PrintWriter[] printWriterArray;
    private BufferedReader[] bufferedReaderArray;
    private int processId; 
    private int numberOfProcesses;
    private Connector connector;
    
    public IntLinkedListOfNeighbors neighbors = new IntLinkedListOfNeighbors();
    
    public Linker(String baseName, int processId, int numberOfProcesses) throws Exception {  
        this.processId = processId;
        this.numberOfProcesses = numberOfProcesses;
        this.bufferedReaderArray = new BufferedReader[numberOfProcesses];
        
        this.printWriterArray = new PrintWriter[numberOfProcesses];
        
    	 // We do not need to include the neighbors in the list here. They are read from a file inside the readNeighbors method
     	// if the file does not exists, the second argument below is used instead of the file, also inside the  readNeighbors method
        Topology.readNeighbors(processId, numberOfProcesses, neighbors);
        connector = new Connector();
        connector.connect(baseName, processId, numberOfProcesses, bufferedReaderArray, printWriterArray);
    }
    
    public void sendMessage(int destinationId, String parameter, String message) {     
        printWriterArray[destinationId].println(processId + " " + destinationId + " " + parameter + " " + message + "#");
        printWriterArray[destinationId].flush();
    }
    
    public void sendMessage(int destinationId, String parameter) {
        sendMessage(destinationId, parameter, " 0 ");
    }
    
    public void sendMulticastMessage(IntLinkedListOfNeighbors destinationIds, String parameter, String message){
        for (int i=0; i<destinationIds.size(); i++) {
            sendMessage(destinationIds.getEntry(i), parameter, message);
        }
    }
    
    public Message receiveMessage(int fromId) throws IOException  {        
        String line = bufferedReaderArray[fromId].readLine();
        Util.println(" received message " + line);
        
        StringTokenizer stringTokenizer = new StringTokenizer(line);
        int sourceId = Integer.parseInt(stringTokenizer.nextToken());
        int destinationId = Integer.parseInt(stringTokenizer.nextToken());
        
        String parameter = stringTokenizer.nextToken();
        String message = stringTokenizer.nextToken("#");
        
        return new Message(sourceId, destinationId, parameter, message);        
    }
    
    public int getProcessId() { 
    	return processId; 
    }
    
    public int getNumberOfProcesses() { 
    	return numberOfProcesses; 
    }
    
    public void close() {
    	connector.closeSockets();
    }
    
    public static void main(String[] args) {
    	try {
			Linker linker = new Linker("network", 2, 3); // second argument is the processId. Third, the number of processes
			System.out.println(linker.getProcessId());
			System.out.println(linker.getNumberOfProcesses());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
}
