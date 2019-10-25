package ds.module05.lamport.mutual.exclusion;

import java.io.*;  

import ds.module02.socket.tcp.linker.Linker;
import ds.module02.socket.tcp.linker.Message;
import ds.util.Util;

public class Process implements MessageHandler {
    protected int numberOfProcesses;
    protected int processId;
    private Linker linker;
    
    public Process(Linker linker) {
        this.linker = linker;
        this.processId = linker.getProcessId();  
        this.numberOfProcesses = linker.getNumberOfProcesses();
    }
    
    @Override
    public synchronized void handleMessage(Message message, int sender, String parameter) {
    	
    }
    
    public void sendMessage(int destinationId, String parameter, String message) {
        Util.println("Sending message to " + destinationId + ":" +parameter + " " + message);
        linker.sendMessage(destinationId, parameter, message);
    }
    
    public void sendMessage(int destinationId, String parameter, int message) {
        sendMessage(destinationId, parameter, message+" "); // simple way to convert int to String without use of String.valueOf(message)+ " "
    }
    
    public void sendMessage(int destinationId, String parameter, int message1, int message2) {
        sendMessage(destinationId,parameter, message1+" "+message2+" "); // the individual messages are combined before being sent
    }
    
    public void sendMessage(int destinationId, String parameter) {
        sendMessage(destinationId, parameter, " 0 ");
    }
       
    public void broadcastMessage(String parameter, int message) {
        for (int i = 0; i < numberOfProcesses; i++)
            if (i != processId) 
            	sendMessage(i, parameter, message);
    }
    
    public void sendToNeighbors(String parameter, int message) {
        for (int i = 0; i < numberOfProcesses; i++)
            if (isNeighbor(i)) 
            	sendMessage(i, parameter, message);     
    }
    
    public boolean isNeighbor(int neighbor) {
        if (linker.neighbors.contains(neighbor)) 
        	return true;
        else return false;
    }
    
    @Override
    public Message receiveMessage(int fromId) {
        try {
            return linker.receiveMessage(fromId);
        } catch (IOException e){
            System.out.println(e);
            linker.close();
            return null;
        }
    }
    
    public synchronized void myWait() {
        try {
            wait();
        } catch (InterruptedException e) {
        	System.err.println(e);
        }
    }
}
