package self.aub.study.java.operator.smgp_3_0;

public abstract class SMGPReport {
	/**
	 *@param msgID		状态报告对应原短消息的MsgID
	 *@param recvTime	接收到短消息的时间（格式：YYYYMMDDHHMMSS）
	 *@param srcTermID	短消息发送号码
	 *@param destTermID	短消息接收号码
	 *@param Stat 		短消息的最终状态: <br/>
	 *					 DELIVRD	短消息转发成功<br/>
	 *					 EXPIRED	短消息超过有效期<br/>
	 *					 DELETED	短消息已经被删除<br/>
	 *					 UNDELIV	短消息是不可转发的<br/>
	 *					 ACCEPTD	短消息已经被最终用户接收(为保持与SMPP兼容，保留)<br/>
	 *					 UNKNOWN	未知短消息状态<br/>
	 *					 REJECTD	短消息被拒绝(为保持与SMPP兼容，保留)<br/>
	 *@author LJX
	 *@date 2011-9-7 下午7:58:17
	 *@comment 
	 */
	public abstract void dealReport(String msgID, String recvTime, String srcTermID, String destTermID, String Stat);
}
