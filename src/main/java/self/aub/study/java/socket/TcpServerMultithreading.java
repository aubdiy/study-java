package self.aub.study.java.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerMultithreading implements Runnable {

	private static ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private String threadName;

	static {
		System.out.println("====Tcp Server Start====");
		try {
			serverSocket = new ServerSocket(8888);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public TcpServerMultithreading(String threadName) {
		this.threadName = threadName;
	}

	@Override
	public void run() {
		try {
			while (true) {
				socket = serverSocket.accept();
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				String line = in.readLine();
				System.out.println("thread name:" + threadName + ",==server:" + line);
				out.println("thread name:" + threadName + ",you input is :" + line);
				out.close();
				in.close();
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Thread(new TcpServerMultithreading("1")).start();
		new Thread(new TcpServerMultithreading("2")).start();
	}

}
