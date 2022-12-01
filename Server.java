import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
	
	 private ArrayList<ClientHandler> connections;
	 private ServerSocket server;
	 private boolean done;
	 //thread pool for handling multithreadding
	 private ExecutorService threads;
	 
	 //to initialize the array list
	 public Server() {
		 connections = new ArrayList<>();
		 done = false;
	 }
	 
	 @Override
	 public void run() {
		 try {
			server = new ServerSocket(9999);
			threads = Executors.newCachedThreadPool();
			//listen to connections at all times
			while (!done) {
				Socket client = server.accept();
				ClientHandler handler = new ClientHandler(client);
				connections.add(handler);
				//executes new thread on the handler
				threads.execute(handler);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 //display message to all the clients
	 public void broadcast(String message) {
		 // for each client in connections
		 for (ClientHandler ch: connections) {
			 if (ch != null) {
				 ch.sendMessage(message);
			 }
		 }
	 }
	 
	 //function to shut down server
	 public void shutdown() {
		 done = true;
		 try {
			 if (!server.isClosed()) {
				 server.close();
			} 
			 
			 //close each individual connection
			 for (ClientHandler ch: connections) {
				 ch.shutdown();
			 }
		 } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	 //handles the  client connection
	 class ClientHandler implements Runnable {
		 private Socket client;
		 private BufferedReader in;
		 private PrintWriter out;
		 private String nickname;
		 
		 public ClientHandler(Socket client) {
			 this.client = client;
		 }
		 @Override
		 public void run() {
			 try {
				 out = new PrintWriter(client.getOutputStream(), true);
				 in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				 
				 //send message to client here
				 out.println("Please enter a name for your client: ");
				 nickname = in.readLine();
				 
				 //show who connected to server
				 System.out.print(nickname + " connected!");
				 //send message to all the clients that someone joined the chat
				 broadcast(nickname + " joined the chat!");
				 
				 //process message and complete commands based on message
				 String message;
				 while ((message = in.readLine()) != null) {
					 if (message.startsWith("/nick")) {
						 
					 }
					 else if (message.startsWith("/quit")) {
						 
					 }
					 else {
						 broadcast(nickname + ": " + message);
					 }
				 }
				 
			 } catch (IOException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
			 }
		 }
		 
		 //send function to send message to client via the handler
		 public void sendMessage(String message) {
			 out.println(message);
		 }
		 
		 public void shutdown() {
			 try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 out.close();
			 if (!client.isClosed()) {
				 try {
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		 }
 	 }
	 
	 //main function for testing server
	 public static void main(String[] args) {
		 Server server = new Server();
		 server.run();
		 
	 }
	 
}