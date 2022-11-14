package File.upload;

import java.io.*;
import java.net.Socket;

public class Client {

    Socket socket;
    InputStream isClient;
    OutputStream osClient;

    public Client() {
        try {
            socket = new Socket(Server.SERVER_IP, Server.SERVER_PORT);
            isClient = socket.getInputStream();
            osClient =  socket.getOutputStream();
        } catch (IOException e) {

            System.out.println("can't connect to server");
        }

    }

    public void upLoadFileToServer(String source_file) throws IOException {

        File file = new File(source_file);
        if (!file.exists()) System.out.println("File not exists");
        FileInputStream fis = new FileInputStream(file);
        int byteRead;
        byte[] arrByte = new byte[1024];
        while ((byteRead = fis.read(arrByte)) != -1) {

            osClient.write(arrByte, 0, byteRead);

        }
        fis.close();


    }

    public static void main(String[] args) throws IOException {

        Client cl_1 = new Client();
        cl_1.upLoadFileToServer(Utils.source_file);
    }


}
