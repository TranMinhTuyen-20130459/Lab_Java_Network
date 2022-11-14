package Echo.server.multi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoChatMultiServer {

    static final int NUM_OF_THREAD = 2;
    static final int SERVER_PORT = 7;

    public static void EchoServerMulti() {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREAD);
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(SERVER_PORT); //start Server
            System.out.println("Server started: " + serverSocket);
            System.out.println("Waiting for client ...");
            while(true){

                Socket socket = serverSocket.accept(); // Client is accepted;
                System.out.println("Client accepted: "+socket);

                WorkerThread handler = new WorkerThread(socket);
                executorService.execute(handler);

            }


        } catch (IOException e) {
            System.out.println("server is error");
        }

    }

    public static void main(String[] args) {

        EchoServerMulti();

    }
}
