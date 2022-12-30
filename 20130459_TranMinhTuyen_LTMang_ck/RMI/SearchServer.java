package Cau_25;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SearchServer {
	public static void main(String[] args) throws RemoteException {
		Registry reg = LocateRegistry.createRegistry(2000);
		reg.rebind("IS", new Dao());
	}
}
