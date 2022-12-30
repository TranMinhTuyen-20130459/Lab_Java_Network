package Cau_25;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ISearch extends Remote{
	public boolean checkUserName(String param) throws RemoteException;
	public boolean isLogin(String userName, String param) throws RemoteException;
	public List<student> find_id(String userName,String param) throws RemoteException;

}
