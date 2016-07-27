package self.aub.study.java.webservice.client;

public class Test {

	/**
	 * 生成客户端
	 * wsimport -p [包名]  -keep -d E:/test/ http://localhost:8088/WSServer?wsdl
	 * wsimport -p com.targtime.ChannelCenter.ws  -keep -d E:/test/ http://127.0.0.1:29807/SMSReportWS?wsdl 
	 */
	
	public static void main(String[] args) {
		WSServer ws = new WSServerService().getWSServerPort();
		String s = ws.sayHi();
		System.out.println(s);
		String s1 = ws.sayHiName("你好");
		System.out.println(s1);
		
	}
}
