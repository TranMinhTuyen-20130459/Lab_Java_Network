package File.upload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WorkerThread implements Runnable {

    Socket socket;
    InputStream isServer;
    OutputStream osServer;

    public WorkerThread(Socket socket) {
        this.socket = socket;
        try {
            this.isServer = socket.getInputStream();
            this.osServer = socket.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void run() {

        try {
            receiveFileFromClient(Utils.dest_file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void receiveFileFromClient(String dest_file) throws IOException {

        FileOutputStream fos = new FileOutputStream(dest_file);

        int byteRead;
        byte[] arrByte = new byte[1024];
        while ((byteRead = isServer.read(arrByte)) != -1) {

            fos.write(arrByte, 0, byteRead);


        }
        fos.close();
    }
}
