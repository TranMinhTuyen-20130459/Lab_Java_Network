package cau20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	Socket socket;
	InputStream is;
	OutputStream os;
	BufferedReader br_from_server;
	BufferedReader br_from_keyboard;
	PrintWriter pw;

	public Client() {

		try {
			socket = new Socket(Server.SERVER_IP, Server.SERVER_PORT);
			is = socket.getInputStream();
			os = socket.getOutputStream();
			br_from_keyboard = new BufferedReader(new InputStreamReader(System.in));
			br_from_server = new BufferedReader(new InputStreamReader(is));
			pw = new PrintWriter(new OutputStreamWriter(os), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void action() {

		try {
			String hello = br_from_server.readLine();
			System.out.println(hello);

			while (true) {
				
				
				String input_command = br_from_keyboard.readLine();

				pw.println(input_command);

				String result_from_server = br_from_server.readLine();
				System.out.println(result_from_server);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		Client cl_1 = new Client();
	}

}
