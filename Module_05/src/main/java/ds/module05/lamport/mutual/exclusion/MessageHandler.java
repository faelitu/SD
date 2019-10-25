package ds.module05.lamport.mutual.exclusion;

import java.io.IOException;

import ds.module02.socket.tcp.linker.Message;

public interface MessageHandler {
	
    void handleMessage(Message message, int sourceId, String parameter);
    Message receiveMessage(int fromId) throws IOException;
    
}

