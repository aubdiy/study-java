package self.aub.study.java.operator.smgp_3_0;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import self.aub.study.java.operator.smgp_3_0.msg.Constant;
import self.aub.study.java.operator.smgp_3_0.msg.structure.ActiveTest;
import self.aub.study.java.operator.smgp_3_0.msg.structure.ActiveTestResp;
import self.aub.study.java.operator.smgp_3_0.msg.structure.Header;
import self.aub.study.java.operator.smgp_3_0.msg.structure.Login;
import self.aub.study.java.operator.smgp_3_0.msg.structure.LoginResp;
import self.aub.study.java.operator.smgp_3_0.socket.SMGPSocket;


public class SMGPConnect {
	private static Logger logger = LoggerFactory.getLogger(SMGPConnect.class);

	/**
	 * @param smgpSocket
	 * @param loginMode
	 * @param clientID
	 * @param password
	 * @param tryCount
	 * @return boolean
	 * @author LJX
	 * @date 2011-8-31 下午3:39:26
	 * @comment 连接到SMG且进行Login操作（建立应用层连接）
	 */
	public static boolean connectAndLogin(SMGPSocket smgpSocket,
			byte loginMode, byte[] clientID, byte[] password, int tryCount) {
		if (smgpSocket == null) {
			logger.error("SMGP Error---->SMGPSocket Is Null");
			return false;
		}
		if (smgpSocket.isConnected()) {
			return true;
		}
		if (!connect(smgpSocket)) {
			return false;
		}
		if (login(smgpSocket, loginMode, clientID, password)) {
			// login成功
			return true;
		}
		// login失败，如果尝试次数不大于3次，则重新登录，否则返回登录失败
		if (++tryCount < 3) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return connectAndLogin(smgpSocket, loginMode, clientID, password,
					tryCount);
		}
		return false;
	}

	/**
	 * @param smgpSocket
	 * @return boolean
	 * @author LJX
	 * @date 2011-8-30 下午4:05:04
	 * @comment 建立本地到SMG连接
	 */
	private static boolean connect(SMGPSocket smgpSocket) {
		int tryCount = 0;
		// 连续连接3次都失败，则返回失败，
		while (!smgpSocket.initSMGPSocket()) {
			if (++tryCount < 3) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param smgpSocket
	 * @param loginMode
	 *            登录模式
	 * @param clientID
	 * @param password
	 * @return boolean
	 * @author LJX
	 * @date 2011-8-31 下午3:37:28
	 * @comment Login操作（建立应用层连接）
	 */
	private static boolean login(SMGPSocket smgpSocket, byte loginMode,
			byte[] clientID, byte[] password) {
		byte[] loginPacket = new Login(loginMode, clientID, password)
				.getRequestPacket();
		try {
			smgpSocket.write(loginPacket);
			byte[] headerPacket = new byte[Constant.PACKET_LEN_HEADER];
			int tmpLen;
			if ((tmpLen = smgpSocket.read(headerPacket)) != Constant.PACKET_LEN_HEADER) {
				smgpSocket.closeSMGPSocket();
				logger.error(
						"SMGP Login---->Fail, Header Packet Length Error, Length:{}",
						tmpLen);
				return false;
			}
			Header header = new Header();
			header.dealResponsePacket(headerPacket);
			if (header.getCommandID() != Constant.CMD_LOGIN_RESP) {
				smgpSocket.closeSMGPSocket();
				logger.error(
						"SMGP Login---->Fail, Is Not Login Resp, ConmandID:{}",
						header.getCommandID());
				return false;
			}

			byte[] loginRespPacket = new byte[Constant.PACKET_LEN_LOGIN_RESP];
			if ((tmpLen = smgpSocket.read(loginRespPacket)) != Constant.PACKET_LEN_LOGIN_RESP) {
				smgpSocket.closeSMGPSocket();
				logger.error(
						"SMGP Login---->Fail, Body Packet Length Error, Length:{}",
						tmpLen);
				return false;
			}
			LoginResp loginResp = new LoginResp();
			loginResp.dealResponsePacket(loginRespPacket);
			if (loginResp.getStatus() == Constant.STATUS_SUCCESS) {
				return true;
			} else {
				smgpSocket.closeSMGPSocket();
				logger.error("SMGP Login---->Fail, Status: {}",
						loginResp.getStatus());
				return false;
			}
		} catch (SocketTimeoutException e) {
			smgpSocket.closeSMGPSocket();
			logger.error("SMGP Login---->Fail, Exception:{}", e.toString());
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			smgpSocket.closeSMGPSocket();
			logger.error("SMGP Login---->Fail, Exception:{}", e.toString());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 *@param smgpSocket void
	 *@author LJX
	 *@date 2011-8-31 下午5:50:24
	 *@comment 向SMG提交链路检测包
	 */
	public static void activeTest(SMGPSocket smgpSocket) {
		if (null == smgpSocket || !smgpSocket.isConnected()) {
			return;
		}
		byte[] activeTestPacket = new ActiveTest().getRequestPacket();
		try {
			smgpSocket.write(activeTestPacket);
			while (true) {
				byte[] headerPacket = new byte[Constant.PACKET_LEN_HEADER];
				int tmpLen;
				if ((tmpLen = smgpSocket.read(headerPacket)) != Constant.PACKET_LEN_HEADER) {
					smgpSocket.closeSMGPSocket();
					logger.error(
							"SMGP ActiveTest---->Fail, Header Packet Length Error, Length:{}",
							tmpLen);
					return;
				}
				Header header = new Header();
				header.dealResponsePacket(headerPacket);
				if (header.getCommandID() == Constant.CMD_ACTIVE_TEST_RESP_INT) {
					// 处理 ActiveTest Resp
					logger.debug("SMGP ActiveTest---->Success");
					return;
				} else if (header.getCommandID() == Constant.CMD_ACTIVE_TEST_INT) {
					// 处理 ActiveTest
					byte[] activeTestRespPacket = new ActiveTestResp()
							.getRequestPacket();
					smgpSocket.write(activeTestRespPacket);
					logger.debug("SMGP ActiveTest---->Send ActiveTest Resp");
				} else {
					smgpSocket.closeSMGPSocket();
					logger.error(
							"SMGP ActiveTest---->Fail, Is Not ActiveTest Resp, ConmandID:{}",
							header.getCommandID());
					return;
				}
			}
		} catch (SocketTimeoutException e) {
			smgpSocket.closeSMGPSocket();
			logger.error("SMGP ActiveTest---->Fail, Exception:{}", e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			smgpSocket.closeSMGPSocket();
			logger.error("SMGP ActiveTest---->Fail, Exception:{}", e.toString());
			e.printStackTrace();
		}
	}
}
