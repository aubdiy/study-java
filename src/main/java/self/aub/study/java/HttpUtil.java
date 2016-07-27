package self.aub.study.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	/**
	 *@param url
	 *@return String
	 *@author LJX
	 *@date 2010-11-3 下午02:23:35
	 *@comment 
	 */
	public static String requestByGet(String url) {
		try {
			URL httpUrl = new URL(url);
			HttpURLConnection httpUrlConn = (HttpURLConnection) httpUrl
					.openConnection();
			httpUrlConn.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					httpUrlConn.getInputStream()));
			String line;
			String result = "";
			while ((line = in.readLine()) != null) {
				result += line;
			}
			in.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	 
	 /**
	 *@param url
	 *@param param
	 *@return String
	 *@author LJX
	 *@date 2010-11-3 下午02:23:41
	 *@comment 
	 */
	public static String requestByPost(String url, String param) {
		try {
			URL httpUrl = new URL(url);
			HttpURLConnection httpConn = (HttpURLConnection) httpUrl
					.openConnection();
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setRequestMethod("POST");
			PrintWriter out = new PrintWriter(httpConn.getOutputStream());
			out.print(param);
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream()));
			String line;
			String result = "";
			while ((line = in.readLine()) != null) {
				result += line;
			}
			in.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {

	}
	
}
