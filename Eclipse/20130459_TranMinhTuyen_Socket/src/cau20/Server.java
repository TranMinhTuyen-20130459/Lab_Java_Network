package cau20;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	static final String SERVER_IP="127.0.0.1";
	static final int SERVER_PORT = 98;
	
	ServerSocket serverSocket;
	
	public Server() {
		
		try {
			serverSocket = new ServerSocket(SERVER_PORT); // start server
			System.out.println("Waitting for client ....");
			
			while (true) {
				
				Socket socketClient = serverSocket.accept(); 
				System.out.println(socketClient +"	connect to server");
				Server_Process process = new Server_Process(socketClient);
				process.start(); // run thread
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public static void main(String[] args) {
		
		Server server = new Server();
	}
	
	

}
