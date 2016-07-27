package self.aub.study.java.operator.smgp_3_0.msg.structure;

import self.aub.study.java.operator.smgp_3_0.msg.ResponsePacket;
import self.aub.study.java.operator.smgp_3_0.util.Utils;

public class Header implements ResponsePacket{
	/**
     * 包长度,包括包头和包体的总长度
     */
    protected int totalLength;

    /**
     * 发送包的命令字id
     */
    protected int commandID;

    /**
     * 发送序列号，步长为1,达到最大值后循环使用
     */
    protected int sequenceID;

	@Override
	public void dealResponsePacket(byte[] packet) {
		// 数据包长度
		byte[] totalLengthBytes = new byte[4];
		System.arraycopy(packet, 0, totalLengthBytes, 0, 4);
		this.totalLength = Utils.bytes4ToInt(totalLengthBytes);

		// 命令id
		byte[] commandIDBytes = new byte[4];
		System.arraycopy(packet, 4, commandIDBytes, 0, 4);
		this.commandID = Utils.bytes4ToInt(commandIDBytes);

		// sequenceID
		byte[] sequenceIDBytes = new byte[4];
		System.arraycopy(packet, 8, sequenceIDBytes, 0, 4);
		this.sequenceID = Utils.bytes4ToInt(sequenceIDBytes);		
	}
	
	public int getTotalLength() {
		return totalLength;
	}

	public int getCommandID() {
		return commandID;
	}

	public int getSequenceID() {
		return sequenceID;
	}
}
