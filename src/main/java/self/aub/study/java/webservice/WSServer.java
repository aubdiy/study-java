package self.aub.study.java.webservice;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

@WebService
@SOAPBinding(style = Style.RPC)
public class WSServer {

	public String sayHi() {
		return "Hi!";
	}
	public String sayHiName(String name) {
		return "Hi! "+ name;
	}
	

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8088/WSServerAAA", new WSServer());
		while(true){
			
		}
	}


}
