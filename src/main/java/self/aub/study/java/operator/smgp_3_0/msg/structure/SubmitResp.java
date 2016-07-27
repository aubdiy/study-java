package self.aub.study.java.operator.smgp_3_0.msg.structure;

import self.aub.study.java.operator.smgp_3_0.msg.ResponsePacket;
import self.aub.study.java.operator.smgp_3_0.util.Hex;
import self.aub.study.java.operator.smgp_3_0.util.Utils;

public class SubmitResp implements ResponsePacket {

	private String msgID;

	private int status;

	@Override
	public void dealResponsePacket(byte[] packet) {
		// 短消息流水号
		byte[] msgIDBytes = new byte[10];
		System.arraycopy(packet, 0, msgIDBytes, 0, 10);
		msgID = Hex.encodeHexStr(msgIDBytes);

		// 请求返回结果
		byte[] statusBytes = new byte[4];
		System.arraycopy(packet, 10, statusBytes, 0, 4);
		status = Utils.bytes4ToInt(statusBytes);
	}

	public String getMsgID() {
		return msgID;
	}

	public int getStatus() {
		return status;
	}
}
