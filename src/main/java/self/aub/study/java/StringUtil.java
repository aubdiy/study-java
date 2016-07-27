package self.aub.study.java;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class StringUtil {
	
	
	/**
	 * 
	 *@param obj	
	 *@return boolean
	 *@author LJX
	 *@date   2010-4-7 下午08:50:28
	 *@comment 判断是否为空
	 */
	public static boolean isEmpty(Object obj){
		if(null == obj){
			return true;
		}else if (obj instanceof String){
			return ((String) obj).length() == 0;
		}else {
			return false;
		}
	}
	
	/**
	 * 
	 *@param string
	 *@return String
	 *@author LJX
	 *@date   2010-4-7 下午08:54:09
	 *@comment 字符串转码：GBK 2 ISO
	 */
	public static String GBK2ISO(String string) {
		if (null != string) {
			try {
				return new String(string.getBytes("GBK"), "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	/**
	 * 
	 *@param string
	 *@return String
	 *@author LJX
	 *@date   2010-4-7 下午08:54:09
	 *@comment 字符串转码：ISO 2 GBK
	 */
	public static String ISO2GBK(String string){
		if(null != string){
			try {
				return new String(string.getBytes("ISO-8859-1"),"GBK");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 
	 *@param string
	 *@return String
	 *@author LJX
	 *@date   2010-4-7 下午08:54:09
	 *@comment 字符串转码：GBK 2 UTF
	 */
	public static String GBK2UTF(String string) {
		if (null != string) {
			try {
				return new String(string.getBytes("GBK"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	/**
	 * 
	 *@param string
	 *@return String
	 *@author LJX
	 *@date   2010-4-7 下午08:54:09
	 *@comment 字符串转码：UTF 2 GBK
	 */
	public static String UTF2GBK(String string){
		if(null != string){
			try {
				return new String(string.getBytes("UTF-8"),"GBK");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	/**
	 * 
	 *@param string
	 *@return String
	 *@author LJX
	 *@date   2010-4-7 下午08:54:09
	 *@comment 字符串转码：ISO 2 UTF
	 */
	public static String ISO2UTF(String string) {
		if (null != string) {
			try {
				return new String(string.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	/**
	 * 
	 *@param string
	 *@return String
	 *@author LJX
	 *@date   2010-4-7 下午08:54:09
	 *@comment 字符串转码：UTF 2 ISO
	 */
	public static String UTF2ISO(String string){
		if(null != string){
			try {
				return new String(string.getBytes("UTF-8"),"ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	/**
	 *@param string
	 *@return InputStream
	 *@author LJX
	 *@date 2010-6-7 下午05:48:20
	 *@comment	输入string 输出 inputStream
	 */
	public static InputStream string2InputStream(String string){
		return new ByteArrayInputStream(string.getBytes());
	}
	
	/**
	 *@param inputStream
	 *@return String
	 *@author LJX
	 *@date 2010-6-7 下午06:13:15
	 *@comment	
	 */
	public static String inputStream2String(InputStream inputStream){
		StringBuffer sb = new StringBuffer();
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String string=null;
			while ((string=br.readLine())!=null) {
				sb.append(string);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * 
	 *@param string
	 *@return String
	 *@author LJX
	 *@date   2010-3-23 下午04:22:56
	 *@comment	去掉字符串的前后空格（全角and半角）
	 */
	public static String trim(String string){
		if(null != string){
			string = string.replaceAll("^[ |　]*", "").replaceAll("[ |　]*$", "");
		}
		return string;
	}

	/**
	 *@param string
	 *@return String
	 *@author LJX
	 *@date 2010-12-8 下午04:03:36
	 *@comment 全角转半角
	 */
	public static String full2half(String string){
		String result = "";
		if(null != string && !"".equals(string)){
			char[] chars = string.toCharArray();
			for(char c : chars){
				if(c == 12288){
					c = 32;
				}else if(c > 65280 && c < 65375){
					c = (char) (c - 65248);
				}
				result = result.concat(String.valueOf(c));
			}
		}
		return result;
	}
	
	/**
	 *@param string
	 *@return String
	 *@author LJX
	 *@date 2010-12-8 下午04:08:39
	 *@comment 
	 */
	public static String half2full(String string){
		String result = "";
		if(null != string && !"".equals(string)){
			char[] chars = string.toCharArray();
			for(char c : chars){
				if(c == 32){
					c = 12288;
				}else if(c > 32 && c < 127){
					c = (char) (c + 65248);
				}
				result = result.concat(String.valueOf(c));
			}
		}
		return result;
	}
	
	/**
	 *@param string
	 *@return String
	 *@author LJX
	 *@date 2010-11-3 下午03:25:43
	 *@comment 
	 */
	public static String encodeByUtf8(String string){
		if(null != string){
			try {
				string =  URLEncoder.encode(string,"UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
				string = "";
			}
		}
		return string;
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
	/*	BigDecimal bd = new BigDecimal(123456789);
		System.out.println(bd.movePointLeft(-2).toString());*/
	/*	InputStream in =string2InputStream("sadfasdf");
		String string = inputStream2String(in);
		System.err.println(string);*/
		
		System.out.println(full2half("123４５６》。＠！＃！＠＃％$＄$"));
	}

}
