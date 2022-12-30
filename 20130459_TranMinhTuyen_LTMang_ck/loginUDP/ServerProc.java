package ltm.socket.tuan2.loginUDP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ServerProc {

	DatagramSocket socket;
	DatagramPacket packet;
	byte[] buff = new byte[1024];

	boolean isLogin = false;
	Dao dao;

	public ServerProc() throws IOException {

		socket = new DatagramSocket(1234);
		packet = new DatagramPacket(buff, buff.length); // setdata, setLength
		dao = new Dao();

	}

	public void run() {

		String line;
		String com, param;
		String userName = null;
		try {
			// login
			while (!isLogin) {
				line = recRequest();
				if ((line == null) || (line.equalsIgnoreCase("QUIT")))
					break;
				StringTokenizer st = new StringTokenizer(line);
				com = st.nextToken().toUpperCase();
				param = st.nextToken();
				switch (com) {
				case "USER": {
					if (!dao.checkUserName(param).isEmpty()) {
						response("OK username");
						userName = param;
					} else {
						response("Error username");
					}
					break;
				}
				case "PASS": {
					if (userName == null) {
						response("Error username");
					} else {
						if (!dao.login(userName, param).isEmpty()) {
							response("Ok login!");
							isLogin = true;
						} else {
							response("Error Login!");
						}
					}
					break;
				}
				default:
					response("Unknown Command");
				}
			}
			List<Student> res = new ArrayList<>();
			while (isLogin) {
				res.clear();
				line = recRequest();
				if ((line == null) || (line.equalsIgnoreCase("QUIT")))
					break;
				StringTokenizer st = new StringTokenizer(line);
				com = st.nextToken().toUpperCase();
				param = st.nextToken();
				switch (com) {
				case "FIND_ID":
					res = dao.findById(param);
					break;
				default:
					response("Unknown Command");
				}
				response(res);
			}
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String recRequest() throws IOException {
		packet.setData(buff);
		packet.setLength(buff.length);
		socket.receive(packet);

		ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
		DataInputStream dis = new DataInputStream(bais);
		return dis.readUTF();
	}

	private void response(List<Student> res) throws IOException {
		if (res.isEmpty())
			response("No data");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		for (Student s : res) {
			dos.writeUTF(s.toString());
			dos.writeUTF(".");
			dos.flush();
		}

		byte[] data = baos.toByteArray();
		packet.setData(data);
		packet.setLength(data.length);
		socket.send(packet);

	}

	private void response(String line) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeUTF(line);
		dos.writeUTF(".");
		dos.flush();

		byte[] data = baos.toByteArray();

		packet.setData(data);
		packet.setLength(data.length);

		socket.send(packet);
	}

	private String nhan() throws IOException {
		packet.setData(buff);
		packet.setLength(buff.length);
		socket.receive(packet);

		ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
		DataInputStream dis = new DataInputStream(bais);

		return dis.readUTF();
	}

	private void gui(String text) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeUTF(text);
		dos.flush();

		byte[] data = baos.toByteArray();
		packet.setData(data);
		packet.setLength(data.length);
		socket.send(packet);
	}
}