package ds.module04.logical.clocks;

import java.io.IOException;

import ds.module02.socket.tcp.linker.Linker;
import ds.module02.socket.tcp.linker.Message;
import ds.util.Util;

public class VectorClockLinker extends Linker {
    private VectorClock vectorClock;
    private int receivedParameters[];
    
    public VectorClockLinker(String basename, int processId, int numberOfProcesses) throws Exception {
        super(basename, processId, numberOfProcesses);
        vectorClock = new VectorClock(numberOfProcesses, processId);
        receivedParameters = new int[numberOfProcesses];
    }
    
    @Override
    public void sendMessage(int destinationId, String parameter, String message) {
        super.sendMessage(destinationId, "vector", vectorClock.toString());
        super.sendMessage(destinationId, parameter, message);
        vectorClock.sendAction();
    }
    
    public void simpleSendMessage(int destinationId, String parameter, String message) {
        super.sendMessage(destinationId, parameter, message);
     }
    
    @Override
    public Message receiveMessage(int fromId) throws IOException {
        Message message = super.receiveMessage(fromId);
        if (message.getParameterMessageType().equals("vector")) {
            Util.readArray(message.getMessage(), receivedParameters);
            vectorClock.receiveAction(receivedParameters);
            Message m = super.receiveMessage(fromId); //app message
            return m;
        } else 
        	return message;
    }
}


