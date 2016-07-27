package self.aub.study.java.operator.smgp_3_0.msg.structure;

import self.aub.study.java.operator.smgp_3_0.msg.ResponsePacket;
import self.aub.study.java.operator.smgp_3_0.util.Hex;

public class Report implements ResponsePacket {

	/**
	 * 短消息标识,10Byte
	 */
	public String msgID;

	/**
	 * 取缺省值001
	 */
	public String sub;

	/**
	 * 取缺省值001
	 */
	public String dlvrd;

	/**
	 * 短消息提交时间（格式：年年月月日日时时分分，例如010331200000）
	 */
	public String submitDate;

	/**
	 * 短消息下发时间（格式：年年月月日日时时分分，例如010331200000）
	 */
	public String doneDate;

	/**
	 * 短消息的最终状态（参见第6.2.63.1节短消息状态表）
	 */
	public String stat;

	/**
	 * 参见第6.2.63.2节错误代码表
	 */
	public String err;

	/**
	 * 前3个字节，表示短消息长度（用ASCII码表示），后17个字节表示短消息的内容（保证内容不出现乱码）
	 */
	public String txt;

	@Override
	public void dealResponsePacket(byte[] packet) {

		byte[] msgIDBytes = new byte[10];
		System.arraycopy(packet, 3, msgIDBytes, 0, 10);
		msgID = Hex.encodeHexStr(msgIDBytes);

		byte[] subBytes = new byte[3];
		System.arraycopy(packet, 18, subBytes, 0, 3);
		sub = new String(subBytes);

		byte[] dlvrdBytes = new byte[3];
		System.arraycopy(packet, 28, dlvrdBytes, 0, 3);
		dlvrd = new String(dlvrdBytes);

		byte[] submitDateBytes = new byte[10];
		System.arraycopy(packet, 44, submitDateBytes, 0, 10);
		submitDate = new String(submitDateBytes);

		byte[] doneDateBytes = new byte[10];
		System.arraycopy(packet, 65, doneDateBytes, 0, 10);
		doneDate = new String(doneDateBytes);

		byte[] statBytes = new byte[7];
		System.arraycopy(packet, 81, statBytes, 0, 7);
		stat = new String(statBytes);

		byte[] errBytes = new byte[3];
		System.arraycopy(packet, 93, errBytes, 0, 3);
		err = new String(errBytes);

		byte[] txtBytes = new byte[20];
		System.arraycopy(packet, 102, txtBytes, 0, 20);
		txt = new String(txtBytes);
	}

	public String getMsgID() {
		return msgID;
	}

	public String getSub() {
		return sub;
	}

	public String getDlvrd() {
		return dlvrd;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public String getDoneDate() {
		return doneDate;
	}

	public String getStat() {
		return stat;
	}

	public String getErr() {
		return err;
	}

	public String getTxt() {
		return txt;
	}
}
