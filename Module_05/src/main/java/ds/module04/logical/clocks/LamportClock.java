package ds.module04.logical.clocks;

import ds.util.Util;

public class LamportClock {
    private int clock;
    
    public LamportClock() {
        clock = 1;
    }
    
    public int getValue() {
        return clock;
    }
    
    public void tick() { // on internal events
        clock = clock + 1;
    }
    
    public void sendAction() {
       // include clock in message
        clock = clock + 1;      
    }
    
    public void receiveAction(int src, int sentValue) {
        clock = Util.max(clock, sentValue) + 1;
    }
}











