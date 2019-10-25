package ds.module05.lamport.mutual.exclusion;

import java.io.*;
import ds.module02.socket.tcp.linker.Message;

public class ListenerThread extends Thread {
    private int processIdChannel;
    private MessageHandler messageHandler;
    
    public ListenerThread(int processIdChannel, MessageHandler messageHandler) {
        this.processIdChannel= processIdChannel;
        this.messageHandler = messageHandler;
    }
    public void run() {
        while (true) {
            try {
                Message message = messageHandler.receiveMessage(processIdChannel);             
                messageHandler.handleMessage(message, message.getSourceId(), message.getParameterMessageType());
            } catch (IOException e) {
                System.err.println(e);            
            }
        }
    }
}
