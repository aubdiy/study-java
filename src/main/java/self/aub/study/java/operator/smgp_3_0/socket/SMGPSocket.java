package self.aub.study.java.operator.smgp_3_0.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SMGPSocket {
	private static Logger logger = LoggerFactory.getLogger(SMGPSocket.class);

	/**
	 * 网关主机地址
	 */
	private String host;

	/**
	 * 监听端口
	 */
	private int port;

	/**
	 * 建立起的socket端
	 */
	private Socket socket;

	/**
	 * 输出流
	 */
	private OutputStream ops;

	/**
	 * 输入流
	 */
	private DataInputStream ips;
	
	/**
	 * 超时时间,单位：秒
	 */
	public static final int SOCKET_TIMEOUT = 60000;

	public SMGPSocket(String host, int port) {
		this.host = host;
		this.port = port;
	}

	/**
	 * @return boolean
	 * @author LJX
	 * @date 2011-8-25 下午5:49:21
	 * @comment 建立本地到SMG的Socket连接
	 */
	public boolean initSMGPSocket() {
		logger.debug("SMGP Socket Connect---->Host：{}, Port: {}", host, port);
		try {
			socket = new Socket(host, port);
			socket.setSoTimeout(SOCKET_TIMEOUT);
			ops = socket.getOutputStream();
			ips = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			logger.error("SMGP Socket Connect---->{}", e.toString());
			e.printStackTrace();
			return false;
		}
		logger.debug("SMGP Socket Connect---->Success");
		return true;
	}
	
	public void bbb() throws IOException{
		ips = new DataInputStream(socket.getInputStream());
	}

	/**
	 * @return boolean
	 * @author LJX
	 * @date 2011-8-25 下午5:50:55
	 * @comment 判断连接是否关闭
	 */
	public boolean isConnected() {
		if (null == socket || !socket.isConnected() || socket.isClosed()) {
			return false;
		}
		return true;
	}

	/**
	 * void
	 * 
	 * @author LJX
	 * @date 2011-8-25 下午5:51:09
	 * @comment 关闭本地到SMG的连接
	 */
	public void closeSMGPSocket() {
		try {
			if (ops != null) {
				ops.close();
			}
			if (ips != null) {
				ips.close();
			}
			if (socket != null) {
				socket.close();
			}
			socket = null;
			ops = null;
			ips = null;
			logger.debug("SMGP Socket Connect---->Close Success");
		} catch (IOException e) {
			logger.error("SMGP Socket Connect---->{}", e.toString());
			e.printStackTrace();
		}
	}

	public void write(byte[] data) throws IOException {
		ops.write(data);
	}

	public int read(byte[] data) throws IOException {
		return ips.read(data);
	}
}
