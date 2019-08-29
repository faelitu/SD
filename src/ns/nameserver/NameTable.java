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
    private int index = 0;
    
    public int search(String processName) {
        for (int i = 0; i < index; i++)
            if (processNamesArray[i].equals(processName)) return i;
        return -1;
    }
    
    public int insert(String processName, String hostName, int portNumber) {
        int oldIndex = search(processName); // is it already there?
        if ((oldIndex == -1) && (index < MAX_SIZE)) { 
            processNamesArray[index] = processName; 
            hostsArray[index] = hostName;
            portsArray[index] = portNumber;
            index++;
            return 1;
        } else // already there, or table full
            return 0;
    }
    
    public int delete(String processName) {
        int position = 0;
        boolean flag = false;
        for (int i = 0; i < index; i++) {
            if (processNamesArray[i].equals(processName)){
                position = i;
                flag = true;
            }
        }
        
        // if process exists, refactor
        if (flag) {
            for (int j = position; j < index; j++) {
                processNamesArray[j - 1] = processNamesArray[j];
                hostsArray[j - 1] = hostsArray[j];
                portsArray[j - 1] = portsArray[j];
            }
            index--;
            return 1;
        } else // process not found
            return 0;
    }
    
    public int getPort(int index) {
        return portsArray[index];
    }
    
    public String getHostName(int index) {
        return hostsArray[index];
    }
    
    public String toString( ) {
    	String result = "";
    	for (int i=0; i < MAX_SIZE; i++) {
    		if ((processNamesArray[i] == null) || (hostsArray[i] == null) || (portsArray[i] == 0))
    			return result + "Empty name table";
    		result+= "Process name..: " + processNamesArray[i] + 
    				        "\tHostname/IP address..: " + hostsArray[i] + 
    				        "\t\tPort..:" + portsArray[i]+"\n";
    	}
    	return result;
    	}
    }
  

