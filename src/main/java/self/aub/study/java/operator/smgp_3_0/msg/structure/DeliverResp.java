package self.aub.study.java.operator.smgp_3_0.msg.structure;

import self.aub.study.java.operator.smgp_3_0.msg.Constant;
import self.aub.study.java.operator.smgp_3_0.msg.RequestPacket;
import self.aub.study.java.operator.smgp_3_0.util.Utils;

public class DeliverResp implements RequestPacket {
	/**
	 * 短消息流水号,10Byte,String
	 */
	private byte[] msgID;

	/**
	 * 状态,4Byte,Integer
	 */
	private byte[] status;

	public DeliverResp(byte[] msgID, byte[] status) {
		this.msgID = msgID;
		this.status = status;
	}

	@Override
	public byte[] getRequestPacket() {

		byte[] packet = new byte[26];
		// 设置请求头
		System.arraycopy(Utils.intToBytes4(26), 0, packet, 0, 4);
		System.arraycopy(Constant.CMD_DELIVER_RESP, 0, packet, 4, 4);
		System.arraycopy(Utils.getSequenceID(), 0, packet, 8, 4);

		// 设置请求体
		System.arraycopy(msgID, 0, packet, 12, 10);
		System.arraycopy(status, 0, packet, 22, 4);

		return packet;
	}

}
