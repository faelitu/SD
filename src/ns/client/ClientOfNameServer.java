/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ns.client;

/**
 *
 * @author Fellipe
 */
import java.net.*; 
import java.io.*;
import java.util.StringTokenizer;
import ns.util.Address;
import ns.util.Symbols;

public class ClientOfNameServer {
    private Socket clientSocket;
    private BufferedReader bufferedReader;
    private PrintStream printStream;
    
    public void configureSocket() throws IOException {
        clientSocket = new Socket(Symbols.NAME_SERVER, Symbols.SERVER_PORT);
        bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        printStream = new PrintStream(clientSocket.getOutputStream());
    }
    
    public int insertNameInTheNameServer(String name, String hostName, int portNumber) throws IOException {
        configureSocket();
        // printStream prints to the socket output stream. The token "insert" is read in the server in order to decide what to do
        printStream.println("insert " + name + " " + hostName + " " + portNumber);
        printStream.flush();
        return Integer.parseInt(bufferedReader.readLine());
    }
    
    public Address searchNameinTheNameServer(String name) throws IOException {
        configureSocket();
        // pout prints to the socket output stream
        // the token "search" is read in the server
        printStream.println("search " + name);
        printStream.flush();
        // The line below blocks until a reply from the server arrives
        String result = bufferedReader.readLine(); 
        StringTokenizer st = new StringTokenizer(result);
        int portnum = Integer.parseInt(st.nextToken());
        String hname = st.nextToken();
        return new Address(hname, portnum);
    }
    
    public String listAllProcessesInTheNameServer() throws IOException {
        configureSocket();
        // printStream prints to the socket output stream. The token "listAllProcesses" is read in the server in order to decide what to do
        printStream.println("listAllProcesses");
        printStream.flush();
        return bufferedReader.readLine();
    }
    
    public String deleteNameFromNameServer(String name) throws IOException  {
        configureSocket();
        // printStream prints to the socket output stream. The token "delete" is read in the server in order to decide what to do
        printStream.println("delete " + name);
        printStream.flush();
        return bufferedReader.readLine();
    }
    
    public static void main(String[] args) {
        ClientOfNameServer clientOfNameServer = new ClientOfNameServer();
        System.out.println("Client of name server started");
        
        try {
            System.out.println("\nInserting process p1, localhost, port 9999");
            clientOfNameServer.insertNameInTheNameServer("p1", "localhost", 9999);  
            
            System.out.println("\nTrying to locate p1");
            Address pa = clientOfNameServer.searchNameinTheNameServer("p1");
            System.out.println(pa.getHostName() + ":" + pa.getPort());
            
            System.out.println("\nInserting process p2, localhost, port 2222");
            clientOfNameServer.insertNameInTheNameServer("p2", "192.168.1.20", 2222);
            
            System.out.println("\nTrying to locate p2");
            pa = clientOfNameServer.searchNameinTheNameServer("p2");
            System.out.println(pa.getHostName() + ":" + pa.getPort());
            
            
            System.out.println("\nListing all processes in the nameServer");
            String allProcesses = clientOfNameServer.listAllProcessesInTheNameServer();
            System.out.println(allProcesses.replace("[LINE_BREAK]", "\n"));
            
            System.out.println("\nDeleting process p1");
            String deleteResult = clientOfNameServer.deleteNameFromNameServer("p1");
            System.out.println(deleteResult);
            
            System.out.println("\nDeleting process p3");
            deleteResult = clientOfNameServer.deleteNameFromNameServer("p3");
            System.out.println(deleteResult);
            
            System.out.println("\nListing all processes in the nameServer");
            allProcesses = clientOfNameServer.listAllProcessesInTheNameServer();
            System.out.println(allProcesses.replace("[LINE_BREAK]", "\n"));
        } catch (Exception e) {
            System.err.println("Server aborted:" + e);
        }
    }
}
