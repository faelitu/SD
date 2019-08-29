/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ns.util;

/**
 *
 * @author Fellipe
 */
public class Address {
    private String hostName;
    private int portNumber;
    
    public Address(String s, int i) {
        hostName = new String(s);
        portNumber = i;
    }
    public String getHostName() {
        return hostName;
    }
    public int getPort() {
        return portNumber;
    }
}
