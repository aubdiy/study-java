package self.aub.study.java.operator.smgp_3_0.msg.structure;

import self.aub.study.java.operator.smgp_3_0.msg.Constant;
import self.aub.study.java.operator.smgp_3_0.msg.RequestPacket;
import self.aub.study.java.operator.smgp_3_0.util.Utils;

public class ActiveTest implements RequestPacket {

	@Override
	public byte[] getRequestPacket() {
		byte[] packet = new byte[12];
		// 设置请求头
		System.arraycopy(Utils.intToBytes4(12), 0, packet, 0, 4);
		System.arraycopy(Constant.CMD_ACTIVE_TEST, 0, packet, 4, 4);
		System.arraycopy(Utils.getSequenceID(), 0, packet, 8, 4);

		return packet;
	}

}
