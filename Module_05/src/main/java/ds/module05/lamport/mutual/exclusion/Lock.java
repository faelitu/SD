package ds.module05.lamport.mutual.exclusion;

public interface Lock extends MessageHandler {
	
    void requestCriticalSection(); //may block
    void releaseCriticalSection();
    
}
