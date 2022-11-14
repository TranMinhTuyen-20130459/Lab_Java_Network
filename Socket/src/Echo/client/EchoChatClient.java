package Echo.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class EchoChatClient {

    static final String SERVER_IP = "127.0.0.1";
    static final int SERVER_PORT = 7;

    public static void EchoClient() {

        Socket socket = null;
        Scanner sc = new Scanner(System.in);
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT); //connect to server

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(os);

            String welcome = dis.readUTF();
            if (welcome.equals("Welcome")) {

                System.out.println(welcome + " " + socket.toString());
                while (true) {

                    System.out.println("Input:");
                    String input = sc.nextLine();

                    dos.writeUTF(input);


                    String dataFromServer = dis.readUTF();
                    System.out.println(dataFromServer);
                    if (dataFromServer.equalsIgnoreCase("Disconnect")) break;


                }
                socket.close();
            }

        } catch (IOException e) {
            System.out.println("can't connect to server");
        }


    }

    public static void main(String[] args) {

        EchoClient();

    }


}
