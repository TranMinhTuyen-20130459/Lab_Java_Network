package Cau_25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SearchClient2 {
	public static void main(String[] args) throws IOException, NotBoundException {
	Registry reg = LocateRegistry.getRegistry(2000);
	ISearch dao = (ISearch) reg.lookup("IS");
	
	BufferedReader userIn;
	userIn = new BufferedReader(new InputStreamReader(System.in));
	String line, command, param;
	boolean islogin = true;
	String userName = null;		
	System.out.println("Hacker");

			List<student> res = new ArrayList<>();
			while (islogin) {
				res.clear();
				line = userIn.readLine();
				if(line == null || line.equalsIgnoreCase("QUIT")) break;
				StringTokenizer st = new StringTokenizer(line);
				command = st.nextToken().toUpperCase();
				param = st.nextToken();
				try {
				switch (command) {
				case "FIND_ID":
					res = dao.find_id(userName,param);
					break;
				case "FIND_AGE":
				//	res = dao.findAge(param);
					break;
				default:
					System.out.println("Unknown Command");
					break;
				}
				response(res);
				}catch(RemoteException e) {
					System.out.println(e.getMessage());
				}
			}
}
	private static void response(List<student> res) {
		if(res.isEmpty()) {
			System.out.println("No data");
			return;
		}
		for (student st : res) {
			System.out.println(st.toString());
		}
	}
}
