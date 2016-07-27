package self.aub.study.java.operator.smgp_3_0;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.PropertyResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import self.aub.study.java.operator.smgp_3_0.msg.Constant;
import self.aub.study.java.operator.smgp_3_0.msg.structure.Header;
import self.aub.study.java.operator.smgp_3_0.msg.structure.Submit;
import self.aub.study.java.operator.smgp_3_0.msg.structure.SubmitResp;
import self.aub.study.java.operator.smgp_3_0.socket.SMGPSocket;


public class SMGP_SC {

	private static Logger logger = LoggerFactory.getLogger(SMGP_SC.class);

	/**
	 * 网关主机地址
	 */
	private String host;

	/**
	 * 监听端口
	 */
	private int port;

	/**
	 * 企业ID
	 */
	private byte[] clientID;

	/**
	 * 企业密码
	 */
	private byte[] password;

	/**
	 * 业务代码,10Byte,String
	 */
	private byte[] serviceID;

	/**
	 * 源号码
	 */
	public String srctermID;

	private SMGPSocket smgpSocket;

	public SMGP_SC(String configFile) {
		// 初始化配置
		PropertyResourceBundle resourceBundle = (PropertyResourceBundle) PropertyResourceBundle
				.getBundle(configFile);
		this.host = resourceBundle.getString("smgw.host");
		this.port = Integer.valueOf(resourceBundle.getString("smgw.port"));
		this.clientID = resourceBundle.getString("sp.client.id").getBytes();
		this.password = resourceBundle.getString("sp.client.pwd").getBytes();
		this.serviceID = resourceBundle.getString("submit.serviceid")
				.getBytes();
		this.srctermID = resourceBundle.getString("submit.srctermid");

		this.smgpSocket = new SMGPSocket(this.host, this.port);
	}

	/**
	 * @param phone
	 * @param content
	 * @param extend
	 * @return String
	 * @author LJX
	 * @date 2011-8-25 下午7:36:09
	 * @comment 提交短信到SMG
	 */
	public String submit(String phone, String content, String extend) {
		if (content == null) {
			logger.error("SMGP Submit-->Fail, Content Is Null");
			return null;
		}
		if (phone == null) {
			logger.error("SMGP Submit-->Fail, phone Is Null");
		}
		if (!SMGPConnect.connectAndLogin(smgpSocket, Constant.LOGIN_MODE_SEND,
				clientID, password, 0)) {
			return null;
		}
		String srctermID = extend == null ? this.srctermID : this.srctermID
				.concat(extend);
		int contentLen = content.length();
		if (contentLen <= Constant.SMS_LEN_SHORT) {
			byte[] submitPacket = new Submit(phone, content, srctermID,
					serviceID, false, 1, 1).getRequestPacket();
			return submit(submitPacket, 0);

		}
		if (contentLen > Constant.SMS_LEN_MAX) {
			content = content.substring(0, Constant.SMS_LEN_MAX);
		}
		int smsPkTotal = (content.length() + Constant.SMS_LEN_LONG - 1)
				/ Constant.SMS_LEN_LONG;
		String tmpContent = null;
		String msgID = null;
		for (int i = 1; i <= smsPkTotal; i++) {
			if (i == smsPkTotal) {

				tmpContent = content.substring((i - 1) * Constant.SMS_LEN_LONG);
			} else {
				tmpContent = content.substring((i - 1) * Constant.SMS_LEN_LONG,
						i * Constant.SMS_LEN_LONG);
			}
			byte[] submitPacket = new Submit(phone, tmpContent, srctermID,
					serviceID, true, smsPkTotal, i).getRequestPacket();

			String tmpMsgID = submit(submitPacket, 0);
			// 长短信每条都有自己的MSGID,只返回第一条的，如果第一条发送失败，剩下条数将不发送
			// System.out.println(msgID);
			if (i != 1) {
				continue;
			}
			if (tmpMsgID == null) {
				break;
			}
			msgID = tmpMsgID;
		}
		return msgID;

	}

	/**
	 * @param submitPacket
	 * @param tryCount
	 * @return String
	 * @author LJX
	 * @date 2011-8-25 下午7:18:25
	 * @comment 提交短信到SMG，如果超时则重发，重发3次仍然失败，则失败
	 */
	private String submit(byte[] submitPacket, int tryCount) {
		try {
			smgpSocket.write(submitPacket);
			byte[] headerPacket = new byte[Constant.PACKET_LEN_HEADER];
			int tmpLen;
			if ((tmpLen = smgpSocket.read(headerPacket)) != Constant.PACKET_LEN_HEADER) {
				smgpSocket.closeSMGPSocket();
				logger.error(
						"SMGP Submit-->Fail, Header Packet Length Error, Length:{}",
						tmpLen);
				return null;
			}
			Header header = new Header();
			header.dealResponsePacket(headerPacket);
			if (header.getCommandID() != Constant.CMD_SUBMIT_RESP) {
				logger.error(
						"SMGP Submit-->Fail, Is Not Submit Resp, ConmandID:{}",
						header.getCommandID());
				return null;
			}
			byte[] submitRespPacket = new byte[Constant.PACKET_LEN_SUBMIT_RESP];
			if ((tmpLen = smgpSocket.read(submitRespPacket)) != Constant.PACKET_LEN_SUBMIT_RESP) {
				smgpSocket.closeSMGPSocket();
				logger.error(
						"SMGP Submit-->Fail, Body Packet Length Error, Length:{}",
						tmpLen);
				return null;
			}
			SubmitResp submitResp = new SubmitResp();
			submitResp.dealResponsePacket(submitRespPacket);
			if (submitResp.getStatus() == Constant.STATUS_SUCCESS) {
				smgpSocket.closeSMGPSocket();
				return submitResp.getMsgID();
			} else {
				logger.info("SMGP Submit---->Fail,Error Code:{}",
						submitResp.getStatus());
				return null;
			}
		} catch (SocketTimeoutException e) {
			logger.error("SMGP Submit-->Fail, Exception:{}", e.toString());
			e.printStackTrace();
			if (tryCount++ < 2) {
				logger.info("SMGP Submit-->Try Again");
				return submit(submitPacket, tryCount);
			}
			smgpSocket.closeSMGPSocket();
			return null;
		} catch (IOException e) {
			logger.error("SMGP Submit-->Fail, Exception:{}", e.toString());
			e.printStackTrace();
			// 关闭连接
			smgpSocket.closeSMGPSocket();
			return null;
		}
	}

	public static void main(String[] args) {
		SMGP_SC smgSC = new SMGP_SC("smgp");
		for (int i = 1; i <= 20; i++) {
			String result = smgSC.submit("13366597179", "HHH:" + i, "");
			System.out.println(result);
		}
	}
}
