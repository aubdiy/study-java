package self.aub.study.java.operator.smgp_3_0.msg.structure;

import self.aub.study.java.operator.smgp_3_0.msg.ResponsePacket;
import self.aub.study.java.operator.smgp_3_0.util.Utils;

public class LoginResp implements ResponsePacket {

	// 请求返回状态
	private int status;
	// 确认码
	private String authenticatorServer;
	// 服务端支持最高版本号
	private int serverVersion;

	@Override
	public void dealResponsePacket(byte[] packet) {

		// 状态
		byte[] statusBytes = new byte[4];
		System.arraycopy(packet, 0, statusBytes, 0, 4);
		status = Utils.bytes4ToInt(statusBytes);

		// 确认码
		byte[] autSerBytes = new byte[16];
		System.arraycopy(packet, 4, autSerBytes, 0, 16);
		authenticatorServer = new String(autSerBytes);

		// 服务端支持最高版本号
		serverVersion = packet[20];
	}

	public int getStatus() {
		return status;
	}

	public String getAuthenticatorServer() {
		return authenticatorServer;
	}

	public int getServerVersion() {
		return serverVersion;
	}
}
