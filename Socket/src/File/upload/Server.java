package File.upload;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    static final String SERVER_IP = "127.0.0.1";
    static final int SERVER_PORT = 98;

    static final int NUM_OF_THREAD = 10;
    static Server server;
    ServerSocket serverSocket;
    ExecutorService executorService;

    public Server() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT); // start SERVER
            System.out.println("Server is started");
            System.out.println("Waiting for client ....");
            executorService = Executors.newFixedThreadPool(NUM_OF_THREAD);
            while (true) {

                Socket socket = serverSocket.accept(); // Client is accepted
                System.out.println("Client is accepted: " + socket);
                WorkerThread handler = new WorkerThread(socket);
                executorService.execute(handler);

            }
        } catch (IOException e) {

            System.out.println("server is error");
        }

    }

    public static Server getServer() {
        if (server == null) server = new Server();
        return server;
    }

    public static void main(String[] args) {

        Server server = Server.getServer();
    }


}
