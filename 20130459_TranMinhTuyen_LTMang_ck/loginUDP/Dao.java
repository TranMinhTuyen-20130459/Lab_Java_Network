package ltm.socket.tuan2.loginUDP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {
	
	List<Student> listSt;
	List<User> listUser;
	
	public Dao() {
		listSt = new ArrayList<>();
		listUser = new ArrayList<>();
		
	}

	public List<User> checkUserName(String param) {
		ArrayList<User> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			con = connectAccess();
			String sql = "select * from user "
					+ "where username = ? ";
			statement = con.prepareStatement(sql);
			statement.setString(1, param);
			rs = statement.executeQuery();
			while(rs.next()){
				list.add(new User(rs.getString("username"), rs.getString("password")));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public List<User> login(String userName, String param) {
		ArrayList<User> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			con = connectAccess();
			String sql = "select * from user "
					+ "where username = ? and password = ? ";
			statement = con.prepareStatement(sql);
			statement.setString(1, userName);
			statement.setString(2, param);
			rs = statement.executeQuery();
			while(rs.next()){
				list.add(new User(rs.getString("username"), rs.getString("password")));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public List<Student> findById(String param) {
		ArrayList<Student> list = new ArrayList<>();
		int idParam = Integer.parseInt(param);
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			con = connectAccess();
			String sql = "select * from student "
					+ "where id = ?";
			statement = con.prepareStatement(sql);
			statement.setInt(1, idParam);
			rs = statement.executeQuery();
			while(rs.next()){
				list.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getDouble("score")));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public Connection connectAccess() {
		Connection connect = null;
		try {
			String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
			String url = "jdbc:ucanaccess://C:/Test/TestAccess.accdb";
			Class.forName(driver);
			connect = DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connect;
	}
	
	public static void main(String[] args) {
		
		System.out.println(new Dao().login("nguyenvanlenh", "1234").isEmpty());
	}

}
