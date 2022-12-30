package ltm.socket.tuan2.loginTCP;

import java.util.ArrayList;
import java.util.List;

public class Dao {
	
	List<Student> listSt;
	List<User> listUser;
	
	public Dao() {
		listSt = new ArrayList<>();
		listUser = new ArrayList<>();
		
		listUser.add(new User("nguyenvanlenh", "1234"));
		listUser.add(new User("nguyentuan", "1111"));
		
		listSt.add(new Student(1, "Hung", 2000, 9.8));
		listSt.add(new Student(2, "Le", 2001, 7.8));
		listSt.add(new Student(3, "My", 2002, 8.5));
	}

	public boolean checkUserName(String param) {
		for(User u : listUser) {
			if(u.username.equals(param)) return true;
		}
		return false;
	}

	public boolean login(String userName, String param) {
		for(User u : listUser) {
			if(u.username.equals(userName) && u.password.equals(param)) return true;
		}
		return false;
	}

	public List<Student> findById(String param) {
		ArrayList<Student> list = new ArrayList<>();
		int idParam = Integer.parseInt(param);
		for(Student s : listSt) {
			if(s.id == idParam) list.add(s);
		}
		return list;
	}

}
