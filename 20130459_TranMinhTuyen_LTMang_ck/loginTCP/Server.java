package ltm.socket.tuan2.loginTCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(2000);
		while(true) {
			Socket socket = server.accept();
			new ServerProc(socket).start();			
		}
	}

}
