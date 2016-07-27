package self.aub.study.java.rmi;

import self.aub.study.java.ThreadPool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;


public class RMITest implements Runnable {

	private static Context namingCtx = initContext();
	private static RMIServer rmiServer = initRMIServer();

	private static Context initContext() {
		try {
			return new InitialContext();
		} catch (NamingException e) {
			return null;
		}
	}

	private static RMIServer initRMIServer() {
		try {
			return (RMIServer) namingCtx.lookup("rmi://127.0.0.1:9999/RMIServer");
		} catch (NamingException e) {
			return null;
		}
	}

	@Override
	public void run() {
		long s = System.currentTimeMillis();
		try {
			rmiServer.sayHello();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		long e = System.currentTimeMillis();
		System.out.println(e - s);

	}

	public static void main(String[] args) throws Exception {
		System.out.println(System.currentTimeMillis());
		for (int i = 0; i < 100; i++) {
			ThreadPool.execute(new RMITest());
		}
	}
}
