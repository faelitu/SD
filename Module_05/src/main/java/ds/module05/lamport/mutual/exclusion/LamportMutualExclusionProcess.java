package ds.module05.lamport.mutual.exclusion;

import ds.module02.socket.tcp.linker.Linker;
import ds.module02.socket.tcp.linker.Message;
import ds.module04.logical.clocks.DirectClock;
import ds.util.Symbols;

public class LamportMutualExclusionProcess extends Process implements Lock {
    private DirectClock directClock;
    private int[] requestQueue;  
    
    public LamportMutualExclusionProcess(Linker linker) {
        super(linker);
        this.directClock = new DirectClock(numberOfProcesses, processId);
        requestQueue = new int[numberOfProcesses];
        
        for (int j = 0; j < numberOfProcesses; j++) 
            requestQueue[j] = Symbols.INFINITY;
    }
    
    @Override
    public synchronized void requestCriticalSection() {
        directClock.tick();
        requestQueue[processId] = directClock.getValue(processId);
        broadcastMessage("request", requestQueue[processId]); // broadcast message requesting access to critical section
        while (!okayCriticalSection())
            myWait();
    }
    
    @Override
    public synchronized void releaseCriticalSection() {
        requestQueue[processId] = Symbols.INFINITY;
        broadcastMessage("release", directClock.getValue(processId));
    }
    
    boolean okayCriticalSection() {
        for (int j = 0; j < numberOfProcesses; j++){
            if (isGreater(requestQueue[processId], processId, requestQueue[j], j))
                return false;
            if (isGreater(requestQueue[processId], processId, directClock.getValue(j), j))
                return false;
        }
        return true;
    }
    
    boolean isGreater(int directClock1, int processId1, int directClock2, int processId2) {
        if (directClock2 == Symbols.INFINITY) 
        	return false;
        return ((directClock1 > directClock2) || ((directClock1 == directClock2) && (processId1 > processId2)));
    }
    
    @Override
    public synchronized void handleMessage(Message message, int sourceId, String parameter) {
        int timeStamp = message.getMessageInt();
        directClock.receiveAction(sourceId, timeStamp);
        
        if (parameter.equals("request")) {
            requestQueue[sourceId] = timeStamp;
            sendMessage(sourceId, "ack", directClock.getValue(processId));
        } else if (parameter.equals("release"))
            requestQueue[sourceId] = Symbols.INFINITY;
        notify(); // okayCriticalSection() may be true now
    }
}




