package thread.server.multi;

import java.io.*;
import java.net.Socket;

public class WorkedThread implements Runnable {

    private Socket socket;

    public WorkedThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        System.out.println("Processing: " + socket);

        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(os);

            dos.writeUTF("Welcome");
            while (true) {

                String dataFromClient = dis.readUTF();
                if (dataFromClient.equalsIgnoreCase("Exit")) {

                    dos.writeUTF("Disconnect");
                    socket.close();

                } else {

                    dos.writeUTF("Echo " + dataFromClient);

                }

            }
        } catch (IOException e) {

            System.out.println("server is error");
        }


    }
}
