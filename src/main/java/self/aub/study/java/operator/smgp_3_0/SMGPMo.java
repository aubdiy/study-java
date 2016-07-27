package self.aub.study.java.operator.smgp_3_0;

public abstract class SMGPMo {
	
	/**
	 *@param msgID		短消息的MsgID
	 *@param recvTime	接收到短消息的时间（格式：YYYYMMDDHHMMSS）
	 *@param srcTermID	短消息发送号码
	 *@param destTermID	短消息接收号码
	 *@param msgContent 短消息内容
	 *@author LJX
	 *@date 2011-9-7 下午7:58:25
	 *@comment 
	 */
	public abstract void dealMO(String msgID, String recvTime, String srcTermID, String destTermID, String msgContent);
}
