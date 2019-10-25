package ds.module04.logical.clocks;

import ds.util.Util;

public class DirectClock {
    private int[] clockArray;
    private int processId; 
    
    public DirectClock(int numberOfProcesses, int processId) {
        this.processId = processId;
        this.clockArray = new int[numberOfProcesses];
        for (int i = 0; i < numberOfProcesses; i++) {
        	clockArray[i] = 0;
        }
        clockArray[processId] = 1;
    }
    
    public int getValue(int i) {
        return clockArray[i];
    }
    
    public void tick() {
        clockArray[processId]++;
    }
    
    public void sendAction() {
        // sentValue = clock[processId];
        tick();
    }
    
    public void receiveAction(int sender, int sentValue) {
        clockArray[sender] = Util.max(clockArray[sender], sentValue);
        clockArray[processId] = Util.max(clockArray[processId], sentValue) + 1;
    }
    
    public int[] getClock() {
		return clockArray;
	}

	public void setClock(int[] clockArray) {
		this.clockArray = clockArray;
	}
	

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}
}





















	

