package self.aub.study.java.operator.smgp_3_0;

import java.net.SocketTimeoutException;
import java.util.PropertyResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import self.aub.study.java.operator.smgp_3_0.msg.Constant;
import self.aub.study.java.operator.smgp_3_0.msg.structure.ActiveTestResp;
import self.aub.study.java.operator.smgp_3_0.msg.structure.Deliver;
import self.aub.study.java.operator.smgp_3_0.msg.structure.DeliverResp;
import self.aub.study.java.operator.smgp_3_0.msg.structure.Header;
import self.aub.study.java.operator.smgp_3_0.msg.structure.Report;
import self.aub.study.java.operator.smgp_3_0.socket.SMGPSocket;
import self.aub.study.java.operator.smgp_3_0.util.Hex;


public class SMGPDeliver implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(SMGPDeliver.class);

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
	 * 最后提交时间
	 */
	private long lastSubmitTime;

	private SMGPSocket smgpSocket;

	/**
	 * 处理
	 */
	private SMGPMo smgpMo;

	private SMGPReport smgpReport;

	public SMGPDeliver(String configFile, SMGPMo smgpMo, SMGPReport smgpReport) {
		// 初始化配置
		this.smgpMo = smgpMo;
		this.smgpReport = smgpReport;
		PropertyResourceBundle resourceBundle = (PropertyResourceBundle) PropertyResourceBundle
				.getBundle(configFile);
		this.host = resourceBundle.getString("smgw.host");
		this.port = Integer.valueOf(resourceBundle.getString("smgw.port"));
		this.clientID = resourceBundle.getString("sp.client.id").getBytes();
		this.password = resourceBundle.getString("sp.client.pwd").getBytes();
		this.smgpSocket = new SMGPSocket(this.host, this.port);
		this.lastSubmitTime = System.currentTimeMillis();
	}

	@Override
	public void run() {

		while (true) {
			// 接收模式登陆
			while (!SMGPConnect.connectAndLogin(smgpSocket,
					Constant.LOGIN_MODE_RECEIVE, clientID, password, 0)) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 接收数据
			try {
				byte[] headerPacket = new byte[Constant.PACKET_LEN_HEADER];
				int tmpLen;
				if ((tmpLen = smgpSocket.read(headerPacket)) != Constant.PACKET_LEN_HEADER) {
					smgpSocket.closeSMGPSocket();
					logger.error(
							"SMGP Deliver-->Fail, Header Packet Length Error, Length:{}",
							tmpLen);
					continue;
				}

				Header header = new Header();
				header.dealResponsePacket(headerPacket);
				if (header.getCommandID() == Constant.CMD_DELIVER_INT) {
					// 处理deliver
					int deliverPacketLen = header.getTotalLength()
							- Constant.PACKET_LEN_HEADER;
					byte[] deliverPacket = new byte[deliverPacketLen];
					if ((tmpLen = smgpSocket.read(deliverPacket)) != deliverPacketLen) {
						smgpSocket.closeSMGPSocket();
						logger.error(
								"SMGP Deliver-->Fail, Body Packet Length Error, Length:{}",
								tmpLen);
						continue;
					}
					Deliver deliver = new Deliver();
					deliver.dealResponsePacket(deliverPacket);
					// 返回响应
					DeliverResp deliverResp = new DeliverResp(
							deliver.getMsgID(), Constant.STATUS_SUCCESS_bytes);
					smgpSocket.write(deliverResp.getRequestPacket());
					Report report;
					if ((report = deliver.getReport()) != null) {
						// 状态报告
						smgpReport.dealReport(report.getMsgID(),
								deliver.getRecvTime(),
								deliver.getSrcTermID(),
								deliver.getDestTermID(), report.getStat());
					} else if (deliver.getMsgContent() != null) {
						// 上行
						smgpMo.dealMO(Hex.encodeHexStr(deliver.getMsgID()),
								deliver.getRecvTime(),
								deliver.getSrcTermID(),
								deliver.getDestTermID(),
								deliver.getMsgContent());
					}
					
				} else if (header.getCommandID() == Constant.CMD_ACTIVE_TEST_INT) {
					// 处理 activeTest Resp
					byte[] activeTestRespPacket = new ActiveTestResp()
							.getRequestPacket();
					smgpSocket.write(activeTestRespPacket);
					logger.info("SMGP Deliver---->Send ActiveTest Resp");
				} else {
					smgpSocket.closeSMGPSocket();
					logger.error(
							"SMGP Deliver---->Fail, Is Not Deliver Resp, ConmandID:{}",
							header.getCommandID());
				}
				lastSubmitTime = System.currentTimeMillis();
			} catch (SocketTimeoutException e) {
				// 超时，说明暂时没后要接收的数据
				long now = System.currentTimeMillis();
				if (now - lastSubmitTime > Constant.INTERVAL_TIME_ACTIVETEST) {
					// 发送链路检测
					SMGPConnect.activeTest(smgpSocket);
				}
			} catch (Exception e) {
				logger.error("SMGP Deliver-->Fail, Exception:{}", e.toString());
				e.printStackTrace();
				smgpSocket.closeSMGPSocket();
			}
		}
	}

}
