/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ns.nameserver;

/**
 *
 * @author Fellipe
 */
import java.net.*;
import java.io.*;
import java.util.*;
import ns.util.Symbols;

public class NameServer {
	
    private NameTable nameTable  = new NameTable();
    
    public static void main(String[] args) {
    	
        NameServer nameServer = new NameServer();
        System.out.println("NameServer started on port " + Symbols.SERVER_PORT);
        
        try (ServerSocket serverSocket = new ServerSocket(Symbols.SERVER_PORT);){
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Blocks until a connection is made
                System.out.println("Data received on the name server");
                nameServer.handleclient(clientSocket);
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Server aborted:" + e);
        }
    }
    
    
    public void handleclient(Socket clientSocket) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream());
            
            String message = bufferedReader.readLine();          
            StringTokenizer stringTokenizer = new StringTokenizer(message);
            String parameter = stringTokenizer.nextToken();
            
            if (parameter.equals("search")) {
                int index = nameTable.search(stringTokenizer.nextToken());
                if (index == -1) // not found
                    printWriter.println(-1 + " " + "host not found !");
                else
                    printWriter.println(nameTable.getPort(index) + " "+ nameTable.getHostName(index));
                
            } else if (parameter.equals("insert")) {
                String name = stringTokenizer.nextToken();
                String hostName = stringTokenizer.nextToken();
                int portNumber = Integer.parseInt(stringTokenizer.nextToken());
                int result = nameTable.insert(name, hostName, portNumber);
                printWriter.println(result);
            } else if (parameter.equals("listAllProcesses")) {
                // Quick-fix, the table was not recognizing \n
                String nameTableString = nameTable.toString().replace("\n", "[LINE_BREAK]");
                System.out.println(nameTableString);
            	printWriter.println(nameTableString);
            } else if (parameter.equals("delete")) {
                String processName = stringTokenizer.nextToken();
                int delete = nameTable.delete(processName);
                if (delete == 0) // not found
                    printWriter.println("Delete function returned " + delete + ": Process " + processName + " not found!");
                else
                    printWriter.println("Process " + processName + " deleted successfully!");
            }
            
            printWriter.flush();
            
        } catch (IOException e) {
            System.err.println(e);
        }
    }   
}
