package self.aub.study.java.operator.smgp_3_0.util;

public class Utils {

	private static int sequenceID = 0x00000000;

	public synchronized static byte[] getSequenceID() {
		if (sequenceID < 0x7FFFFFFF) {
			++sequenceID;
		} else {
			sequenceID = 0x00000000;
		}
		return intToBytes4(sequenceID);
	}

	public static byte[] intToBytes4(int i) {
		byte[] mybytes = new byte[4];
		mybytes[3] = (byte) (0xFF & i);
		mybytes[2] = (byte) ((0xFF00 & i) >> 8);
		mybytes[1] = (byte) ((0xFF0000 & i) >> 16);
		mybytes[0] = (byte) ((0xFF000000 & i) >> 24);
		return mybytes;
	}

	public static int bytes4ToInt(byte mybytes[]) {
		int tmp = (0xff & mybytes[0]) << 24 | (0xff & mybytes[1]) << 16
				| (0xff & mybytes[2]) << 8 | 0xff & mybytes[3];
		return tmp;
	}

	public static void main(String[] args) {
//		byte[] b ={1,0,0,0};
		byte[] b ={-128,0,0,2};
		int i = bytes4ToInt(b);
		System.out.println(i);
		System.out.println(0x80000002);
	}
}
