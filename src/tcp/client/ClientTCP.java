package tcp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class ClientTCP {
	public static void main(String args[]) {
		String message = "Hello, cruel distributed world !";

		// Open your connection to a server, at port 3333. We use localhost here
		System.out.println("Opening a connection to a server, at port 3333");

		try (Socket clientSocket = new Socket("localhost", 3333);) { // Try with resources - Java SE 8

			// Get an input stream from the socket
			InputStream inputStream = clientSocket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			OutputStream outputStream = clientSocket.getOutputStream();
			PrintStream printStream = new PrintStream(outputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			System.out.println("Connection established with success");

			System.out.println("Sending a message to the server...: " + message);

			printStream.println(message);
			printStream.flush();

			// The line below blocks until a reply from the server arrives
			String echoMessage = bufferedReader.readLine();
			System.out.println("Message echoed by the server...: " + echoMessage);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}