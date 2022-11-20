package cau20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class Server_Process extends Thread {

	Socket socket;
	InputStream is;
	OutputStream os;
	BufferedReader br;
	PrintWriter pw;

	public Server_Process(Socket socket) {

		this.socket = socket;
		try {
			is = socket.getInputStream();
			os = socket.getOutputStream();
			br = new BufferedReader(new InputStreamReader(is));
			pw = new PrintWriter(new OutputStreamWriter(os), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String findOperator(String command) {

		if (new StringTokenizer(command, "+").countTokens() == 2)
			return "+";
		if (new StringTokenizer(command, "-").countTokens() == 2)
			return "-";
		if (new StringTokenizer(command, "*").countTokens() == 2)
			return "*";
		if (new StringTokenizer(command, "/").countTokens() == 2)
			return "/";

		return null;

	}

	public void run() {

		pw.println("Hello client ^.^");

		while (true) {

			try {
				String input_from_client = br.readLine();
				if (input_from_client.equalsIgnoreCase("quit")) {
					
					pw.println("Bye bye client ^.^");

					br.close();
					pw.close();
					socket.close();
					break;

				} else {

					String operator = Server_Process.findOperator(input_from_client);
					if (operator == null) {

						pw.println("Input wrong , please input type");
						
					} else {

						switch (operator) {
						case "+": {

							StringTokenizer strTokenizer = new StringTokenizer(input_from_client, operator);
							double number_A = Double.parseDouble(strTokenizer.nextToken());
							double number_B = Double.parseDouble(strTokenizer.nextToken());
							pw.println(number_A + number_B);
							break;

						}
						case "-": {
							StringTokenizer strTokenizer = new StringTokenizer(input_from_client, operator);
							double number_A = Double.parseDouble(strTokenizer.nextToken());
							double number_B = Double.parseDouble(strTokenizer.nextToken());
							pw.println(number_A - number_B);
							break;

						}
						case "*": {

							StringTokenizer strTokenizer = new StringTokenizer(input_from_client, operator);
							double number_A = Double.parseDouble(strTokenizer.nextToken());
							double number_B = Double.parseDouble(strTokenizer.nextToken());
							pw.println(number_A * number_B);
							break;

						}
						case "/": {

							StringTokenizer strTokenizer = new StringTokenizer(input_from_client, operator);
							double number_A = Double.parseDouble(strTokenizer.nextToken());
							double number_B = Double.parseDouble(strTokenizer.nextToken());
							pw.println(number_A / number_B);
							break;

						}

						default:
							throw new IllegalArgumentException("Unexpected value: " + operator);
						}

					}

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}