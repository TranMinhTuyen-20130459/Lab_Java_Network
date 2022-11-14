package thread.single;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoChatSingleServer {

    static final int SERVER_PORT = 7;

    public static void EchoServer() {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(SERVER_PORT); // open Port Server -> Server is start
            Socket socket = serverSocket.accept(); // Client is accepted
            System.out.println(socket);

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(os);

            String welcome = "Welcome";
            dos.writeUTF(welcome);

            while (true) {

                String dataFromClient = dis.readUTF();


                if (dataFromClient.equalsIgnoreCase("Exit")) {

                    dos.writeUTF("Disconnect");
                    socket.close();
                    break;

                } else {
                    dos.writeUTF("Echo " + dataFromClient);
                }

            }

        } catch (IOException e) {

            System.out.println("server is error");
        }


    }

    public static void main(String[] args) {

        EchoServer();
    }


}
