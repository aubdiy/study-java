package self.aub.study.java.operator.smgp_3_0.msg.structure;

import java.text.SimpleDateFormat;
import java.util.Date;

import self.aub.study.java.operator.smgp_3_0.msg.Constant;
import self.aub.study.java.operator.smgp_3_0.msg.RequestPacket;
import self.aub.study.java.operator.smgp_3_0.util.DigestUtils;
import self.aub.study.java.operator.smgp_3_0.util.Hex;
import self.aub.study.java.operator.smgp_3_0.util.Utils;


public class Login implements RequestPacket {
	
	private byte[] clientID;
	
	private byte[] password;
	
	private byte loginMode;
	
	private String timeStamp;
	
	private byte clientVersion;;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
	
	public Login(byte loginMode, byte[] clientID, byte[] password) {
		this.clientID = clientID;
		this.password = password;
		this.loginMode = loginMode;
		this.timeStamp = sdf.format(new Date());
		this.clientVersion = Constant.SMGP_CLIENT_VERSION;
	}

	

	@Override
	public byte[] getRequestPacket() {
				
		byte[] packet = new byte[42];
		//设置请求头
		System.arraycopy(Utils.intToBytes4(42), 0, packet, 0, 4);
		System.arraycopy(Constant.CMD_LOGIN, 0, packet, 4, 4);
		System.arraycopy(Utils.getSequenceID(), 0, packet, 8, 4);
		
		//设置请求体
		System.arraycopy(clientID, 0, packet, 12, 8);
		int pwdLength = password.length;
		byte[] wait2md5 = new byte[25+pwdLength];
		System.arraycopy(clientID, 0, wait2md5, 0, 8);
		System.arraycopy(password, 0, wait2md5, 15, pwdLength);
		System.arraycopy(timeStamp.getBytes(), 0, wait2md5, 15+pwdLength, 10);
		byte[] authenticatorClient = DigestUtils.encodeMD5(wait2md5);
		System.arraycopy(authenticatorClient, 0, packet, 20, 16);
		packet[36]=loginMode;
		System.arraycopy(Utils.intToBytes4(Integer.valueOf(timeStamp)), 0, packet, 37, 4);
		packet[41]=clientVersion;
		
		return packet;
	}
	
	public static void main(String[] args) {
		byte[] body = new byte[30];
		int s =Integer.valueOf("0301000000");
		System.out.println(body[29]);
//		System.arraycopy(s.getBytes(), 0, body, 0, 8);
		System.out.println(Hex.encodeHex(Utils.intToBytes4(s)));
	}

}
