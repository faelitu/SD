package ds.module04.logical.clocks;

import ds.util.Util;

public class VectorClock {
    private int[] vectorClock;
    private int processId;
    private int numberOfProcesses;
    
    public VectorClock(int numberOfProcesses, int processId) {
        this.processId = processId;
        this.numberOfProcesses = numberOfProcesses;
        vectorClock = new int[numberOfProcesses];
        for (int i = 0; i < numberOfProcesses; i++) 
        	  vectorClock[i] = 0;
        vectorClock[processId] = 1;
    }
    
    public void tick() {
        vectorClock[processId]++;
    }
    
    public void sendAction() {
        vectorClock[processId]++; //include the vector in the message
    }
    
    public void receiveAction(int[] sentValue) {
        for (int i = 0; i < numberOfProcesses; i++)
              vectorClock[i] = Util.max(vectorClock[i], sentValue[i]);
        vectorClock[processId]++; 
    }
    
    public int getValue(int i) {
        return vectorClock[i];
    }
    
    public String toString(){
        return Util.writeArray(vectorClock);
    }
}
