package self.aub.study.java.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
	
	
	
	private static final int ECHO_MAX = 255;
	
	public UdpServer() {
		System.out.println("====Udp Server Start====");
		try {
			DatagramSocket datagramSocket = new DatagramSocket(8888);
			DatagramPacket datagramPacket = new DatagramPacket(new byte[ECHO_MAX], ECHO_MAX);
			while(true){
				System.out.println("====Udp Server Is Ready====");
				//receive
				datagramSocket.receive(datagramPacket);
				StringBuilder sb = new StringBuilder();
				sb.append(" client at ");
				sb.append(datagramPacket.getAddress().getHostAddress());
				sb.append(" on port ");
				sb.append(datagramPacket.getPort());
				System.out.println(sb);
				//response
				datagramSocket.send(datagramPacket);
//				datagramPacket.setLength(ECHO_MAX);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new UdpServer();
	}
}
