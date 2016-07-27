package self.aub.study.java.operator.smgp_3_0.msg.structure;

import java.io.UnsupportedEncodingException;

import self.aub.study.java.operator.smgp_3_0.msg.Constant;
import self.aub.study.java.operator.smgp_3_0.msg.RequestPacket;
import self.aub.study.java.operator.smgp_3_0.util.Utils;


/**
 * @author Administrator
 * 
 */
public class Submit implements RequestPacket {

	/**
	 * 短消息类型,1Byte,Integer<br/>
	 * 0＝MO消息（终端发给SP）<br/>
	 * 6＝MT消息（SP发给终端，包括WEB上发送的点对点短消息）<br/>
	 * 7＝点对点短消息<br/>
	 */
	private byte[] msgType;

	/**
	 * SP是否要求返回状态报告,1Byte,Integer<br/>
	 * 0＝不要求返回状态报告<br/>
	 * 1＝要求返回状态报告<br/>
	 */
	private byte[] needReport;

	/**
	 * 短消息发送优先级,1Byte,Integer<br/>
	 * 0＝低优先级<br/>
	 * 1＝普通优先级<br/>
	 * 2＝较高优先级<br/>
	 * 3＝高优先级
	 */
	private byte[] priority;

	/**
	 * 业务代码,10Byte,String
	 */
	private byte[] serviceID;
	/**
	 * 收费类型,2Byte,String<br/>
	 * 对计费用户采取的收费类型 只针对于MT<br/>
	 * 00＝免费，此时FixedFee和FeeCode无效；<br/>
	 * 01＝按条计信息费，此时FeeCode表示每条费用，FixedFee无效；<br/>
	 * 02＝按包月收取信息费，此时FeeCode无效，FixedFee表示包月费用；<br/>
	 * 03＝按封顶收取信息费，FeeCode表示每条费用，FixedFee表示封顶费用;<br/>
	 * 若按条收费的费用总和达到或超过封顶费后，则按照封顶费用收取信息费；<br/>
	 * 若按条收费的费用总和没有达到封顶费用，则按照每条费用总和收取信息费。
	 */
	private byte[] feeType;

	/**
	 * 资费代码,单位为分,6Byte,String <br/>
	 * 每条短信的费率
	 */
	private byte[] feeCode;

	/**
	 * 包月费/封顶费,单位为分,6Byte,String
	 */
	private byte[] fixedFee;

	/**
	 * 短消息格式,1Byte,Integer<br/>
	 * 0＝ASCII编码<br/>
	 * 3＝短消息写卡操作<br/>
	 * 4＝二进制短消息<br/>
	 * 8＝UCS2编码<br/>
	 * 15＝GB18030编码<br/>
	 * 246（F6）＝(U)SIM相关消息
	 */
	private byte[] msgFormat;

	/**
	 * 短消息有效时间,17Byte,String<br/>
	 * 格式遵循SMPP3.3以上版本协议,短消息有效时间在转发过程中保持不变
	 */
	private byte[] validTime;

	/**
	 * 短消息定时发送时间,17Byte,String<br/>
	 * 格式遵循SMPP3.3以上版本协议。 短消息定时发送时间在转发过程中保持不变
	 */
	private byte[] atTime;

	/**
	 * 短信息发送方号码,21Byte,String<br/>
	 * 对于MT消息，SrcTermID格式为“118＋SP服务代码＋其它（可选）”，<br/>
	 * 例如SP服务代码为1234时，SrcTermID可以为1181234或118123456等。
	 */
	private byte[] srcTermID;

	/**
	 * 计费用户号码,21Byte,String<br/>
	 * 非空时，表示对计费用户号码计费
	 */
	private byte[] chargeTermID;

	/**
	 * 短消息接收号码总数(小于100),1Byte,Integer
	 */
	private byte[] destTermIDCount;

	/**
	 * 短消息接收号码,21Byte,String
	 */
	private byte[] destTermID;

	/**
	 * 短消息长度,1Byte,Integer
	 */
	private byte[] msgLength;

	/**
	 * 短消息内容,MsgLength,String
	 */
	private byte[] msgContent;

	/**
	 * 保留,8Byte,String
	 */
	private byte[] reserve;

	private boolean isLong;

	private int pkTotal;

	private int pkNumber;
	
	private int phonesCount;

	public Submit(String phones, String content, String srctermID,
			byte[] serviceID, boolean isLong, int pkTotal, int pkNumber) {
		this.msgType = Constant.SUBMIT_MSGTYPE_MT;
		this.needReport = Constant.SUBMIT_REPORT_NEED;
		this.priority = Constant.SUBMIT_PRIORITY_LOW;

		this.serviceID = new byte[10];
		if (serviceID.length > 10) {
			System.arraycopy(serviceID, 0, this.serviceID, 0, 10);
		} else {
			System.arraycopy(serviceID, 0, this.serviceID, 0, serviceID.length);
		}

		this.feeType = Constant.SUBMIT_FEETYPE_FREE;
		this.feeCode = Constant.SUBMIT_FEECODE;
		this.fixedFee = Constant.SUBMIT_FIXEDFEE;
		// this.msgFormat = Constant.SUBMIT_MSGFORMAT_GB18030;
		this.msgFormat = Constant.SUBMIT_MSGFORMAT_UCS2;
		this.validTime = Constant.SUBMIT_VALIDTIME;
		this.atTime = Constant.SUBMIT_ATTIME;

		this.srcTermID = new byte[21];
		byte[] srcTermIDArr = srctermID.getBytes();
		if (srcTermIDArr.length > 21) {
			System.arraycopy(srcTermIDArr, 0, this.srcTermID, 0, 21);
		} else {
			System.arraycopy(srcTermIDArr, 0, this.srcTermID, 0,
					srcTermIDArr.length);
		}

		this.chargeTermID = Constant.SUBMIT_CHARGETERMID;
		
		
		String[] phoneArr = phones.split(Constant.SPLIT_PHONE);
		this.phonesCount = phoneArr.length > Constant.SMS_DESTTERMIDCOUNT_MAX ? 
				Constant.SMS_DESTTERMIDCOUNT_MAX : phoneArr.length;
		
		this.destTermIDCount = new byte[1];
		destTermIDCount[0] = (byte) (0xFF & phonesCount);
		this.destTermID = new byte[21 * phonesCount];
		for (int i = 0; i < phonesCount; i++) {
			System.arraycopy(phoneArr[i].getBytes(), 0, this.destTermID, i * 21, 11);
		}

		this.msgLength = new byte[1];
		byte[] contentArr;
		try {
			contentArr = content.getBytes("UnicodeBigUnmarked");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			contentArr = new byte[0];
		}

		if (!isLong) {
			this.msgLength[0] = (byte) contentArr.length;
			this.msgContent = contentArr;
		} else {
			int contentLen = contentArr.length;
			int msgLen = contentLen + 6;
			this.msgLength[0] = (byte) (msgLen);
			this.msgContent = new byte[msgLen];
			byte[] msgContentHeader = { 0x05, 0x00, 0x03, 0x02, (byte) pkTotal,
					(byte) pkNumber };
			System.arraycopy(msgContentHeader, 0, this.msgContent, 0, 6);
			System.arraycopy(contentArr, 0, this.msgContent, 6, contentLen);
		}
		this.reserve = Constant.SUBMIT_RESERVE;
		this.isLong = isLong;
		this.pkTotal = pkTotal;
		this.pkNumber = pkNumber;

	}

	@Override
	public byte[] getRequestPacket() {
		byte[] tlv = new TLV().getPacket(isLong, pkTotal, pkNumber);
		// 基本消息长度+手机号码长度+短信长度
		int phonesLength = 21 * phonesCount;
		int commonLength = 126 + phonesLength + msgContent.length;
		int totalLength = commonLength + tlv.length;
		byte[] packet = new byte[totalLength];

		// 设置请求头
		System.arraycopy(Utils.intToBytes4(totalLength), 0, packet, 0, 4);
		System.arraycopy(Constant.CMD_SUBMIT, 0, packet, 4, 4);
		System.arraycopy(Utils.getSequenceID(), 0, packet, 8, 4);

		// 设置请求体
		System.arraycopy(msgType, 0, packet, 12, 1);
		System.arraycopy(needReport, 0, packet, 13, 1);
		System.arraycopy(priority, 0, packet, 14, 1);
		System.arraycopy(serviceID, 0, packet, 15, 10);
		System.arraycopy(feeType, 0, packet, 25, 2);
		System.arraycopy(feeCode, 0, packet, 27, 6);
		System.arraycopy(fixedFee, 0, packet, 33, 6);
		System.arraycopy(msgFormat, 0, packet, 39, 1);
		System.arraycopy(validTime, 0, packet, 40, 17);
		System.arraycopy(atTime, 0, packet, 57, 17);
		System.arraycopy(srcTermID, 0, packet, 74, 21);
		System.arraycopy(chargeTermID, 0, packet, 95, 21);
		System.arraycopy(destTermIDCount, 0, packet, 116, 1);
		System.arraycopy(destTermID, 0, packet, 117, phonesLength);
		int index = 117 + phonesLength;
		System.arraycopy(msgLength, 0, packet, index, 1);
		index += 1; 
		System.arraycopy(msgContent, 0, packet, index, msgContent.length);
		index += msgContent.length;
		System.arraycopy(reserve, 0, packet, index, 8);
		System.arraycopy(tlv, 0, packet, commonLength, tlv.length);

		return packet;
	}

}
