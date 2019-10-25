package ds.util;

public class Address {
    private String hostName;
    private int portNumber;
    
    public Address(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
    }
    public String getHostName() {
        return hostName;
    }
    public int getPortNumber() {
        return portNumber;
    }
}

