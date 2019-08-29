/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ns.nameserver;

import java.util.LinkedList;

/**
 *
 * @author Fellipe
 */

//NameTable is a table with name, hostName, and portNumber 
// to give a mapping from a process name to the host and the port number

public class NameTable {   
    public static final int MAX_SIZE = 100;
    private LinkedList<String> processNames = new LinkedList();
    private LinkedList<String> hosts = new LinkedList();
    private LinkedList<Integer> ports = new LinkedList();
    
    public int search(String processName) {
        for (int i = 0; i < processNames.size(); i++)
            if (processNames.get(i).equals(processName)) return i;
        return -1;
    }
    
    public int insert(String processName, String hostName, int portNumber) {
        int oldIndex = search(processName); // is it already there?
        if ((oldIndex == -1) && (processNames.size() < MAX_SIZE)) { 
            processNames.add(processName); 
            hosts.add(hostName);
            ports.add(portNumber);
            return 1;
        } else // already there, or table full
            return 0;
    }
    
    public int delete(String processName) {
        for (int i = 0; i < processNames.size(); i++) {
            if (processNames.get(i).equals(processName)){
                processNames.remove(i);
                hosts.remove(i);
                ports.remove(i);
                return 1;
            }
        }
        return 0; // process not found
    }
    
    public int getPort(int index) {
        return ports.get(index);
    }
    
    public String getHostName(int index) {
        return hosts.get(index);
    }
    
    public String toString( ) {
    	String result = "";
    	for (int i=0; i < processNames.size(); i++) {
    		result += "Process name..: " + processNames.get(i) + 
    				        "\tHostname/IP address..: " + hosts.get(i) + 
    				        "\t\tPort..:" + ports.get(i)+"\n";
    	}
        
        if (processNames.size() == 0) 
            result += "Empty name table";
        
    	return result;
    	}
    }
  

