package self.aub.study.java.third.apache.commons;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class Codecs {
	private static final String DEFAULT_CHARSET = "UTF-8";

	private Codecs() {
	}

	/**
	 * <B>转换为十六进制字符串</B><br>
	 * 将byte数组转换为十六进制字符串
	 * 
	 * @author liujinxin
	 * @param data
	 * @return 十六进制字符串
	 */
	public static String encodeHex(byte[] data) {
		return Hex.encodeHexString(data);
	}

	/**
	 * <B>转换为十六进制字符串</B><br>
	 * 将字符串使用默认字符集（utf-8）转换为十六进制字符串
	 * 
	 * @author liujinxin
	 * @param data
	 * @return 十六进制字符串
	 */
	public static String encodeHex(String data) {
		return encodeHex(data, DEFAULT_CHARSET);
	}

	/**
	 * <B>转换为十六进制字符串</B><br>
	 * 将字符串使用指定字符集转换为十六进制字符串
	 * 
	 * @author liujinxin
	 * @param data
	 * @param charset
	 * @return 十六进制字符串
	 */
	public static String encodeHex(String data, String charset) {
		try {
			return encodeHex(data.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("Hex encoder exception", e);
		}
	}

	/**
	 * <B>将十六进制字符串还原为字节数组</B><br>
	 * 
	 * @author liujinxin
	 * @param data
	 * @return 字节数组
	 */
	public static byte[] decodeHex(String data) {
		try {
			return Hex.decodeHex(data.toCharArray());
		} catch (DecoderException e) {
			throw new IllegalStateException("Hex Decoder exception", e);
		}
	}

	/**
	 * <B>将十六进制字符串还原</B><br>
	 * 将十六进制字符串使用默认字符集（utf-8）还原成字符串
	 * 
	 * @author liujinxin
	 * @param data
	 * @return
	 */
	public static String decodeHex2Str(String data) {
		return decodeHex2Str(data, DEFAULT_CHARSET);
	}

	/**
	 * <B>将十六进制字符串还原</B><br>
	 * 将十六进制字符串使用指定字符集还原成字符串
	 * 
	 * @author liujinxin
	 * @param data
	 * @param charset
	 * @return
	 */
	public static String decodeHex2Str(String data, String charset) {
		final byte[] decodeBytes = decodeHex(data);
		try {
			return new String(decodeBytes, charset);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("Hex Decoder exception", e);
		}
	}
}
