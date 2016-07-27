package self.aub.study.java.operator.smgp_3_0.msg.structure;

import self.aub.study.java.operator.smgp_3_0.msg.Constant;

public class TLV {
//	private boolean isTPUdhi;
//	private boolean isPkTotal;
//	private boolean isPkNumber;

	public byte[] getPacket(boolean isLong, int pkTotal,int pkNumber) {
		if(!isLong){
			return new byte[0];
		}
		byte[] result = new byte[15];
		
		//tpudhi
		byte[] tpudhiArr = new byte[5];
		byte[] tpudhiLen = { 0, 1 };
		System.arraycopy(Constant.TLV_TP_UDHI, 0, tpudhiArr, 0, 2);
		System.arraycopy(tpudhiLen, 0, tpudhiArr, 2, 2);
		tpudhiArr[4] = 0x01;
		
		System.arraycopy(tpudhiArr, 0, result, 0, 5);
		
		//pkTotal
		byte[] pkTotalArr = new byte[5];
		byte[] pkTotalLen = { 0, 1 };
		System.arraycopy(Constant.TLV_PKTOTAL, 0, pkTotalArr, 0, 2);
		System.arraycopy(pkTotalLen, 0, pkTotalArr, 2, 2);
		pkTotalArr[4] = (byte)pkTotal;

		System.arraycopy(pkTotalArr, 0, result, 5, 5);
		
		//pkNumber
		byte[] pkNumberArr = new byte[5];
		byte[] pkNumberLen = { 0, 1 };
		System.arraycopy(Constant.TLV_PKNUMBER, 0, pkNumberArr, 0, 2);
		System.arraycopy(pkNumberLen, 0, pkNumberArr, 2, 2);
		pkNumberArr[4] = (byte)pkNumber;
	
		System.arraycopy(tpudhiArr, 0, result, 10, 5);
		
		return result;
	}

}
