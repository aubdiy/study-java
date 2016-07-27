package self.aub.study.java.third.h2;

import java.sql.SQLException;

import org.h2.tools.Server;

public class H2EmbeddingServer {
	private Server server;
	private String port = "9999";

	public void startServer() {
		try {
			System.out.println("正在启动h2...");
			server = Server.createTcpServer(new String[] { "-tcpPort", port }).start();
		} catch (SQLException e) {
			System.out.println("启动h2出错：" + e.toString());
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void stopServer() {
		if (server != null) {
			System.out.println("正在关闭h2...");
			server.stop();
			System.out.println("关闭成功.");
		}
	}

	public static void main(String[] args) {
		H2EmbeddingServer h2 = new H2EmbeddingServer();
		h2.startServer();
		try {
			Thread.sleep(1000000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
