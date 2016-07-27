package self.aub.study.java.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient implements Runnable {

    String reqStr = "";

    public TcpClient(String reqStr) {
        this.reqStr = reqStr;
    }

    @Override
    public void run() {
        Socket socket = null;
        PrintWriter pw = null;
        BufferedReader br = null;
        try {
            // socket = new Socket("127.0.0.1", 8888);
            socket = new Socket("CDM1C08-209019032.wdds.com", 10218);
            //socket = new Socket("10.8.210.166", 44444);
            pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println(reqStr);
            br = new BufferedReader(new
                    InputStreamReader(socket.getInputStream()));
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);

            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != pw) {
                pw.close();
            }
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != socket) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new TcpClient("stat");
        for (int i = 0; i < 1; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
