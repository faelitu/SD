package ds.module02.socket.tcp.linker;

import java.util.*;

public class Message {
	
    private int sourceId;
    private int destinationId;
    private String parameterMessageType;
    private String messageBuffer;
    
    public Message(int sourceId, int destinationId, String parameterMessageType, String messageBuffer) {
    	this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.parameterMessageType = parameterMessageType;
        this.messageBuffer = messageBuffer;
    }
    
    public int getSourceId() {
        return sourceId;
    }
    
    public int getDestinationID() {
        return destinationId;
    }
    
    public String getParameterMessageType() {
        return parameterMessageType;
    }
    
    public String getMessage() {
        return messageBuffer;
    }
    
    public int getMessageInt() {
        StringTokenizer stringTokenizer = new StringTokenizer(messageBuffer);
        return Integer.parseInt(stringTokenizer.nextToken());
    }
    
    public static Message parseMessage(StringTokenizer stringTokenizer) {
        int sourceId = Integer.parseInt(stringTokenizer.nextToken());
        int destinationId = Integer.parseInt(stringTokenizer.nextToken());
        String parameterMessageType = stringTokenizer.nextToken();
        String buffer = stringTokenizer.nextToken("#");
        return new Message(sourceId, destinationId, parameterMessageType, buffer);
    }
    
    public String toString(){
        String result = String.valueOf(sourceId)+" " + String.valueOf(destinationId)+ " " + parameterMessageType + " " + messageBuffer + "#";
        return result;
    }
}
