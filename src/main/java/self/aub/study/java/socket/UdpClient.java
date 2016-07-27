package self.aub.study.java.socket;

import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient implements Runnable {
	private static final int TIME_OUT = 3000;

	private static final int MAX_TRIES = 5;

	private static final byte[] addr = { 10, 0, 0, 1 };

	private static byte[] sendContent = "你好".getBytes();

	@Override
	public void run() {
		try {
			InetAddress serverAddress = InetAddress.getByAddress(addr);
			DatagramSocket datagramSocket = new DatagramSocket();
			datagramSocket.setSoTimeout(TIME_OUT);

			DatagramPacket sendPacket = new DatagramPacket(sendContent, sendContent.length, serverAddress, 8888);
			DatagramPacket receiverPacket = new DatagramPacket(new byte[sendContent.length], sendContent.length);

			int tries = 0;
			boolean receivedResponse = false;
			do {
				datagramSocket.send(sendPacket);
				try {
					datagramSocket.receive(receiverPacket);
					receivedResponse = true;
				} catch (InterruptedIOException e) {
					tries += 1;
					System.out.println("Time out, " + (MAX_TRIES - tries) + " more tries...");
				}
			} while (!receivedResponse && (tries < MAX_TRIES));

			if (receivedResponse) {
				System.out.println("Received: " + new String(receiverPacket.getData()));
			} else {
				System.out.println("No response, giving up.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static void main(String[] args) {
		Runnable runnable = new UdpClient();
		Thread thread = new Thread(runnable);
		thread.start();
	}

}
