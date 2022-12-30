package ltm.socket.tuan2.loginUDP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {	
			new ServerProc().run();
	}

}
