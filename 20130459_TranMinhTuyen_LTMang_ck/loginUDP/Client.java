package ltm.socket.tuan2.loginUDP;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

	DatagramSocket socket;
	DatagramPacket packet;
	byte[] buff = new byte[1024];

	BufferedReader useIn;

	public Client() throws SocketException, UnknownHostException {
		socket = new DatagramSocket();
		packet = new DatagramPacket(buff, buff.length);
		packet.setAddress(InetAddress.getLocalHost());
		packet.setPort(1234);
		useIn = new BufferedReader(new InputStreamReader(System.in));
	}

	public void run() {
		String line;
		try {
			while (true) {
				line = useIn.readLine();
				if (line.equalsIgnoreCase("QUIT"))
					break;
				sendRequest(line);
				getResponse();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void getResponse() throws IOException {
		packet.setData(buff);
		packet.setLength(buff.length);
		socket.receive(packet);

		ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
		DataInputStream dis = new DataInputStream(bais);
		String line;
		while (!(line = dis.readUTF()).equalsIgnoreCase(".")) {
			System.out.println(line);
		}

	}

	private void sendRequest(String line) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeUTF(line);
		dos.flush();
		byte[] data = baos.toByteArray();

		packet.setData(data);
		packet.setLength(data.length);

		socket.send(packet);

	}

	public static void main(String[] args) throws SocketException, UnknownHostException {
		new Client().run();
	}
}
