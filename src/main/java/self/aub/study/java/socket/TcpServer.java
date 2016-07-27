package self.aub.study.java.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public TcpServer() {
		try {
			System.out.println("====Tcp Server Start====");
			serverSocket = new ServerSocket(8888);
			while (true) {
				System.out.println("====Tcp Server Is Ready====");
				socket = serverSocket.accept();
				in = new BufferedReader(new InputStreamReader(socket
						.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);

				String line = in.readLine();
				System.out.println("==server:"+line);
				out.println("you input is :" + line);
				out.close();
				in.close();
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new TcpServer();
	}
}
