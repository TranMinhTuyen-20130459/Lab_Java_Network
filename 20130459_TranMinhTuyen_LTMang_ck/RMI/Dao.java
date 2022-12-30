package Cau_25;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dao extends UnicastRemoteObject implements ISearch {
	Connection conn;
	long counter =0;
	private Map<String, Long> sessions;
	
	public Dao() throws RemoteException {
		sessions = new HashMap<>();
		String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
		String url = "jdbc:ucanaccess://C:/DB2.accdb";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
		} catch (Exception e) {
			throw new RemoteException(e.getMessage());
		}
	}

	public boolean checkUserName(String param) throws RemoteException {
		try {
			String sql = "select * from user where username =?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, param);
			ResultSet res = pre.executeQuery();
			return res.next();
		} catch (Exception e) {
			throw new RemoteException(e.getMessage());
		}
	}

	public boolean isLogin(String userName, String param) throws RemoteException {
		try {
			String sql = "select * from user where username = ? and pass =?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, userName);
			pre.setString(2, param);
			ResultSet res = pre.executeQuery();
			boolean suc = res.next();
			if(suc) {
				counter++;
				sessions.put(userName, counter);
			}
			return suc;
		} catch (Exception e) {
			throw new RemoteException(e.getMessage());
		}
	}

	public List<student> find_id(String userName,String param) throws RemoteException {
		if(sessions.get(userName)==null) throw new RemoteException("Invalid Session");
		try {
			List<student> list = new ArrayList<>();
			int id = Integer.parseInt(param);
			String sql = "select * from student where id_student =?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			ResultSet res = pre.executeQuery();
			while (res.next()) {
				list.add(new student(res.getInt(1), res.getString(2), res.getDouble(3), res.getInt(4)));
			}
			return list;
		} catch (Exception e) {
			throw new RemoteException(e.getMessage());
		}
	}

}
