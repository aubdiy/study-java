package self.aub.study.java.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServer extends Remote {
	public String sayHello() throws RemoteException;
}
