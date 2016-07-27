package self.aub.study.java.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RMIServerImpl extends UnicastRemoteObject implements RMIServer {

	private static final long serialVersionUID = 5539573754283067882L;

	protected RMIServerImpl() throws RemoteException {
		super();
	}

	@Override
	public String sayHello() throws RemoteException {
		return "hello";
	}

	public static void main(String[] args) throws NamingException, RemoteException {
		RMIServer rmiServer = new RMIServerImpl();
		// registry
		LocateRegistry.createRegistry(9999);
		// context
		Context namingCtx = new InitialContext();
		// bind
		namingCtx.bind("rmi://127.0.0.1:9999/RMIServer", rmiServer);
	}

}
