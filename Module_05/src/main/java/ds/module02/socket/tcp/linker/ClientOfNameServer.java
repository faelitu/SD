package ds.module02.socket.tcp.linker;

 
import java.util.*;

import ds.util.Address;
import ds.util.Symbols;

import java.net.*; 
import java.io.*;

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
        // pout prints to the socket output stream
        // the token "insert" is read in the server in order to decide what to do
        printStream.println("insert " + name + " " + hostName + " " + portNumber);
        printStream.flush();
        return Integer.parseInt(bufferedReader.readLine());
    }
    
    public Address searchNameInTheNameServer(String name) throws IOException {
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
    
    public static void main(String[] args) {
        ClientOfNameServer clientOfNameServer = new ClientOfNameServer();
        System.out.println("Client of name server started");
        
        try {
            clientOfNameServer.insertNameInTheNameServer("localhost", "localhost", 7033); // can be different? i think so
            Address pa = clientOfNameServer.searchNameInTheNameServer("localhost");
            System.out.println(pa.getHostName() + ":" + pa.getPortNumber());
        } catch (Exception e) {
            System.err.println("Server aborted:" + e);
        }
    }
}
