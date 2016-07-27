package self.aub.study.java.operator.smgp_3_0.msg;


public class Constant {
	

	/*****************************************************************************************/
	/*    SMGP协议客户端版本号							                                         */
	/*****************************************************************************************/
	public static final byte SMGP_CLIENT_VERSION 		= 0x3;
	
	/*****************************************************************************************/
	/*    请求标识表示SMGP数据包的类型，请求包的请求标识和应答包的请求标识一一对应                                                                                         */
	/*****************************************************************************************/
	public static final byte[] CMD_LOGIN				= {0,0,0,1}; 	// 0x00000001客户端登录
	public static final byte[] CMD_SUBMIT 				= {0,0,0,2};	// 0x00000002提交短消息
	public static final byte[] CMD_DELIVER 				= {0,0,0,3}; 	// 0x00000003下发短消息
	public static final byte[] CMD_FORWARD 				= {0,0,0,5}; 	// 0x00000005短消息前转
	public static final byte[] CMD_ACTIVE_TEST 			= {0,0,0,4}; 	// 0x00000004链路检测
	public static final byte[] CMD_EXIT 				= {0,0,0,6}; 	// 0x00000006退出请求
	public static final byte[] CMD_QUERY 				= {0,0,0,7}; 	// 0x00000007SP统计查询
	public static final byte[] CMD_QUERY_TE_ROUTE 		= {0,0,0,8}; 	// 0x00000008查询TE路由
	public static final byte[] CMD_QUERY_SP_ROUTE 		= {0,0,0,9}; 	// 0x00000009查询SP路由
	public static final byte[] CMD_ACTIVE_TEST_RESP		= {-128,0,0,4}; // 0x80000004链路检测应答
	public static final byte[] CMD_DELIVER_RESP 		= {-128,0,0,3}; // 0x80000003下发短消息应答

	public static final int CMD_ACTIVE_TEST_INT 		= 0x00000004; 	// 0x00000004链路检测
	public static final int CMD_ACTIVE_TEST_RESP_INT	= 0x80000004; 	// {-128,0,0,4}链路检测应答
	public static final int CMD_DELIVER_INT				= 0x00000003;	// 0x00000003下发短消息
	
	public static final int CMD_LOGIN_RESP				= 0x80000001;	// {-128,0,0,1}客户端登录应答
	public static final int CMD_SUBMIT_RESP 			= 0x80000002; 	// {-128,0,0,2}提交短消息应答
	public static final int CMD_FORWARD_RESP 			= 0x80000005; 	// {-128,0,0,5}短消息前转应答
	public static final int CMD_EXIT_RESP 				= 0x80000006; 	// {-128,0,0,6}退出应答
	public static final int CMD_QUERY_RESP 				= 0x80000007; 	// {-128,0,0,7}SP统计查询应答
	public static final int CMD_QUERY_TE_ROUTE_RESP		= 0x80000008; 	// {-128,0,0,8}查询TE路由应答
	public static final int CMD_QUERY_SP_ROUTE_RESP 	= 0x80000009; 	// {-128,0,0,9}查询SP路由应答
	
	/*****************************************************************************************/
	/*    长度定义									                                             */
	/*****************************************************************************************/
	public static final int PACKET_LEN_HEADER 			= 12; 			// 消息头长度
	public static final int PACKET_LEN_LOGIN_RESP		= 21; 			// login resp消息体长度
	public static final int PACKET_LEN_SUBMIT_RESP		= 14; 			// submit resp消息体长度
	
	public static final int SMS_LEN_SHORT				= 70; 			// 单条短短信最大字数
	public static final int SMS_LEN_LONG				= 67; 			// 单条长短信最大字数
	public static final int SMS_LEN_MAX					= 17085; 		// 单次短信最大字数=255*67
	public static final int SMS_DESTTERMIDCOUNT_MAX		= 100; 		    // 群发时一次提交最大号码数

	/*****************************************************************************************/
	/*    时间定义					                                                             */
	/*****************************************************************************************/
	public static final int INTERVAL_TIME_ACTIVETEST 	= 60000; 		// 长连接方式链路检测间隔时间
	
	
	/*****************************************************************************************/
	/*    SMGP登陆模式							                                     		     */
	/*****************************************************************************************/
	public static final byte LOGIN_MODE_SEND			= 0;			// 发送短消息（send mode）
	public static final byte LOGIN_MODE_RECEIVE			= 1;			// 接收短消息（receive mode）
	public static final byte LOGIN_MODE_TRANSMIT		= 2;			// 收发短消息（transmit mode）	
	
	/*****************************************************************************************/
	/*    SMGP返回状态						                                     		         */
	/*****************************************************************************************/
	public static final int STATUS_SUCCESS				= 0;			// 成功	
	public static final byte[] STATUS_SUCCESS_bytes		= {0,0,0,0};	// 成功	
	
	/*****************************************************************************************/
	/*    SMGP Submit 常量定义					                                     		     */
	/*****************************************************************************************/
	public static final byte[] SUBMIT_MSGTYPE_MO		= {0};			// MO消息（终端发给SP）
	public static final byte[] SUBMIT_MSGTYPE_MT		= {6};			// MT消息（SP发给终端，包括WEB上发送的点对点短消息）	
	public static final byte[] SUBMIT_MSGTYPE_P2P		= {7};			// 点对点短消息	
	
	public static final byte[] SUBMIT_REPORT_WITHOUT	= {0};			// 无需状态报告
	public static final byte[] SUBMIT_REPORT_NEED		= {1};			// 需要状态报告
	
	public static final byte[] SUBMIT_PRIORITY_LOW		= {0};			// 最低优先级
	public static final byte[] SUBMIT_PRIORITY_COMMON	= {1};			// 普通优先级
	public static final byte[] SUBMIT_PRIORITY_HIGHER	= {2};			// 较高优先级
	public static final byte[] SUBMIT_PRIORITY_HIGHEST	= {3};			// 最高优先级
	
	public static final byte[] SUBMIT_FEETYPE_FREE		= {48,48};		// "00"对用户免费
	public static final byte[] SUBMIT_FEETYPE_EACH		= {48,49};		// "01"对用户按条计费
	public static final byte[] SUBMIT_FEETYPE_MONTH		= {48,50};		// "02"对用户包月计费
	
	public static final byte[] SUBMIT_FEECODE			= {0,0,0,0,0,0};// 每条短信的费率,单位分
	public static final byte[] SUBMIT_FIXEDFEE			= {0,0,0,0,0,0};// 包月费/封顶费,单位为分
	
	
	public static final byte[] SUBMIT_MSGFORMAT_ASC2	= {0};			// 0=ASC2编码
	public static final byte[] SUBMIT_MSGFORMAT_UCS2	= {8};			// 0=UCS2编码
	public static final byte[] SUBMIT_MSGFORMAT_GB18030	= {15};			// 15=GB18030编码（文字短消息）
	
	public static final byte[] SUBMIT_VALIDTIME	 		= {48,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};	// 短消息有效时间
	public static final byte[] SUBMIT_ATTIME			= {48,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};	// 短消息定时发送时间

	public static final byte[] SUBMIT_CHARGETERMID		= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};	
	public static final byte[] SUBMIT_DESTTERMIDCOUNT	= {1};	
	public static final byte[] SUBMIT_RESERVE			= {0,0,0,0,0,0,0,0};	
	
	/*****************************************************************************************/
	/*    SMGP TLV 常量定义					                                     		     */
	/*****************************************************************************************/
	public static final byte[] TLV_TP_UDHI				= {0,2};	
	public static final byte[] TLV_PKTOTAL				= {0,9};	
	public static final byte[] TLV_PKNUMBER				= {0,10};	
	
	/*****************************************************************************************/
	/*    SMGP Deliver 常量定义					                                     		 */
	/*****************************************************************************************/
	public static final int DELIVER_IS_MO				= 0;	
	public static final int DELIVER_IS_REPORT			= 1;	
	
	/*****************************************************************************************/
	/*    SMGP 分隔符  常量定义					                                     		     */
	/*****************************************************************************************/
	
	public static final String SPLIT_PHONE				= ",";
	private static String  showByteArray(byte[] data){
		if(null == data){
			return null;
		}
		StringBuilder sb = new StringBuilder("{");
		for(byte b:data){
			sb.append(b).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("}");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String s = "";
		System.out.println(showByteArray(s.getBytes()));
		byte[] b= {0,0,0,0,0,0,0,0,0,0};
		System.out.println(new String(b));
	}
	
}
