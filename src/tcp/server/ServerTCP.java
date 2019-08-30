package tcp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {
    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(3333); // Register your service on port 3333
            System.out.println("TCP Server socket started on port 3333");

            while (true) { // Run the listen/accept loop forever
                Socket clientSocket = serverSocket.accept(); // Wait here and listen for a connection

                boolean flag = true;
                while (flag) {
                    InputStream inputStream = clientSocket.getInputStream();
                    OutputStream outputStream = clientSocket.getOutputStream();
                    PrintStream printStream = new PrintStream(outputStream);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                    String message = bufferedReader.readLine();
                    System.out.println("Message data received from client..: " + message);
                    System.out.println("Message length..: " + message.length() + " character(es)");

                    printStream.println(message); // Send your message back!
                    if (message.equals("done")) {
                        printStream.close();
                        flag = false;
                    } else {
                        printStream.flush();
                    }
                }
                
                clientSocket.close(); // Close the connection
                System.out.println("Client connection closed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                    serverSocket.close(); // Close the serverSocket
            } catch (IOException e) {
                    e.printStackTrace();
            }
        }
    }
}