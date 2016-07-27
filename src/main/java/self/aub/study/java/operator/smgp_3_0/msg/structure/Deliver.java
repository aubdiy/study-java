package self.aub.study.java.operator.smgp_3_0.msg.structure;

import java.io.UnsupportedEncodingException;

import self.aub.study.java.operator.smgp_3_0.msg.Constant;
import self.aub.study.java.operator.smgp_3_0.msg.ResponsePacket;


public class Deliver implements ResponsePacket {

	/**
	 * 短消息流水号,10Byte
	 */
	private byte[] msgID;

	/**
	 * 是否为状态报告,1Byte<br/>
	 * 0＝不是状态报告<br/>
	 * 1＝是状态报告<br/>
	 * 其它保留。
	 */
	private int isReport;

	/**
	 * 短消息格式,1Byte
	 */
	private int msgFormat;

	/**
	 * 短消息接收时间,14Byte
	 */
	private String recvTime;

	/**
	 * 短消息发送号码,21Byte
	 */
	private String srcTermID;

	/**
	 * 短消息接收号码,21Byte
	 */
	private String destTermID;

	/**
	 * 短消息长度,1Byte
	 */
	private int msgLength;

	/**
	 * 短消息内容,msgLength Byte
	 */
	private String msgContent;

	/**
	 * 保留,8Byte
	 */
	private String reserve;

	/**
	 * 状态报告
	 */
	private Report report;

	@Override
	public void dealResponsePacket(byte[] packet) {
		// 短消息流水号
		msgID = new byte[10];
		System.arraycopy(packet, 0, msgID, 0, 10);

		// 是否为状态报告
		// byte[] isReportBytes = new byte[1];
		// System.arraycopy(packet, 10, isReportBytes, 0, 1);
		// isReport = Utils.bytes4ToInt(isReportBytes);
		isReport = packet[10];

		// 短消息格式
		// byte[] msgFormatBytes = new byte[1];
		// System.arraycopy(packet, 11, msgFormatBytes, 0, 1);
		// msgFormat = Utils.bytes4ToInt(msgFormatBytes);
		msgFormat = packet[11];

		// 短消息接收时间
		byte[] recvTimeBytes = new byte[14];
		System.arraycopy(packet, 12, recvTimeBytes, 0, 14);
		recvTime = new String(recvTimeBytes);

		// 短消息发送号码
		byte[] srcTermIDBytes = new byte[21];
		System.arraycopy(packet, 26, srcTermIDBytes, 0, 21);
		srcTermID = new String(srcTermIDBytes).trim();

		// 短消息接收号码
		byte[] destTermIDBytes = new byte[21];
		System.arraycopy(packet, 47, destTermIDBytes, 0, 21);
		destTermID = new String(destTermIDBytes).trim();

		// 短消息长度
		// byte[] msgLengthBytes = new byte[1];
		// System.arraycopy(packet, 68, msgLengthBytes, 0, 1);
		// msgLength = Utils.bytes4ToInt(msgLengthBytes);
		msgLength = packet[68];

		// 短消息内容
		byte[] msgContentBytes = new byte[msgLength];
		System.arraycopy(packet, 69, msgContentBytes, 0, msgLength);

		if (isReport == Constant.DELIVER_IS_REPORT) {
			// 状态报告
			report = new Report();
			report.dealResponsePacket(msgContentBytes);
		} else if (isReport == Constant.DELIVER_IS_MO) {
			// mo
			try {
				msgContent = new String(msgContentBytes,
						msgFormat == 8 ? "UnicodeBigUnmarked" : "GB18030");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		// 保留
		byte[] reserveBytes = new byte[8];
		System.arraycopy(packet, 69 + msgLength, reserveBytes, 0, 8);
		reserve = new String(reserveBytes);
	}

	public byte[] getMsgID() {
		return msgID;
	}

	public int getIsReport() {
		return isReport;
	}

	public int getMsgFormat() {
		return msgFormat;
	}

	public String getRecvTime() {
		return recvTime;
	}

	public String getSrcTermID() {
		return srcTermID;
	}

	public String getDestTermID() {
		return destTermID;
	}

	public int getMsgLength() {
		return msgLength;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public String getReserve() {
		return reserve;
	}

	public Report getReport() {
		return report;
	}
}
