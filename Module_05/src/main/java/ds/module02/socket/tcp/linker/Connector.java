package ds.module02.socket.tcp.linker;

import java.util.*;

import ds.util.Address;
import ds.util.Symbols;

import java.net.*;
import java.io.*;

public class Connector {
    private ServerSocket serverSocket;  
    private Socket [] clientSocketsArray;
    
    public void connect(String basename, int processId, int numberOfProcesses, BufferedReader[] bufferedReaderArray, PrintWriter[] printWriterArray) throws Exception {
        ClientOfNameServer clientOfNameServer = new ClientOfNameServer();
        clientSocketsArray = new Socket[numberOfProcesses];
        int localport = getLocalPortOfProcess(processId);
        serverSocket = new ServerSocket(localport);
        
        /* register in the name server */
        String processName = basename + processId;
        String hostName  = InetAddress.getLocalHost().getHostName(); // TODO try with IPAddress and Network Name
        clientOfNameServer.insertNameInTheNameServer(processName, hostName, localport);
        
        /* accept connections from all the smaller processes */
        for (int i = 0; i < processId; i++) {
            Socket clientSocket = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String message = bufferedReader.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(message);
            int hisId = Integer.parseInt(stringTokenizer.nextToken());
            int destinationId = Integer.parseInt(stringTokenizer.nextToken());
            String parameter = stringTokenizer.nextToken();
            if (parameter.equals("hello")) {   // TODO eliminate hard code?
                clientSocketsArray[hisId] = clientSocket;
                bufferedReaderArray[hisId] = bufferedReader;
                printWriterArray[hisId] = new PrintWriter(clientSocket.getOutputStream());
            }
        }
        /* contact all the bigger processes */
        for (int i = processId + 1; i < numberOfProcesses; i++) {
            Address address = null;         
            do {
                address = clientOfNameServer.searchNameInTheNameServer(basename + i);
                Thread.sleep(100);
            } while (address.getPortNumber() == -1);
            
            clientSocketsArray[i] = new Socket(address.getHostName(), address.getPortNumber());
            printWriterArray[i] = new PrintWriter(clientSocketsArray[i].getOutputStream());
            bufferedReaderArray[i] = new BufferedReader(new InputStreamReader(clientSocketsArray[i].getInputStream()));
            /* send a hello message to P_i */
            printWriterArray[i].println(processId +" "+ i +" "+ "hello" + " " + "null");  // TODO eliminate hard-code
            printWriterArray[i].flush();
        }
    }
    
    public int getLocalPortOfProcess(int processId) { 
    	return Symbols.SERVER_PORT + 10 + processId; 
    }
    
    public void closeSockets(){
        try {
            serverSocket.close();
            for (int i=0; i< clientSocketsArray.length; i++) {
            	clientSocketsArray[i].close();
            }
        } catch (Exception e) {
        	System.err.println(e);
        }
    }
}


