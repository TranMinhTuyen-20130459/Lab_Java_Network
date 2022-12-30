package ltm.socket.tuan2.loginTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ServerProc extends Thread {

	Socket socket;
	PrintWriter netOut;
	BufferedReader netIn;
	boolean isLogin = false;
	Dao dao;

	public ServerProc(Socket socket) throws IOException {
		this.socket = socket;
		dao = new Dao();
		netOut = new PrintWriter(socket.getOutputStream(), true);
		netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	@Override
	public void run() {

		String line;
		String com , param;
		String userName = null;
		netOut.println("System is ready...");

		try {
			// login
			while (!isLogin) {
				line = netIn.readLine();
				if ((line == null) || (line.equalsIgnoreCase("QUIT")))
					break;

				StringTokenizer st = new StringTokenizer(line);
				com = st.nextToken().toUpperCase();
				param = st.nextToken();
				switch (com) {
				case "USER": {
					if (dao.checkUserName(param)) {
						netOut.println("OK username");
						userName = param;
						
					} else {
						netOut.println("Error username");
					}
					break;
				}
				case "PASS": {
					if (userName == null) {
						netOut.println("Error username");
					} else {
						if (dao.login(userName, param)) {
							netOut.println("Ok login!");
							isLogin = true;
						} else {
							netOut.println("Error Login!");
						}
					}
					break;
				}
				default:
					netOut.println("Unknown Command");
				}

				List<Student> res = new ArrayList<>();
					while (isLogin) {
					res.clear();
					line = netIn.readLine();
					if ((line == null) || (line.equalsIgnoreCase("QUIT")))
						break;
					StringTokenizer st2 = new StringTokenizer(line);
					com = st2.nextToken().toUpperCase();
					param = st2.nextToken();
					switch (com) {
					case "FIND_ID": {
						res = dao.findById(param);
						break;
					}
					default:
						netOut.println("Unknown Command");
					}
					respone(res);
				}
			}
			socket.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	private void respone(List<Student> res) {
		for (Student s : res)
			netOut.println(s.toString());
		if (res.isEmpty())
			netOut.println("No data");
	}
}