package self.aub.study.java.operator.smgp_3_0.test;

import self.aub.study.java.operator.smgp_3_0.SMGPReport;

public class TestReport extends SMGPReport {

	@Override
	public void dealReport(String msgID,String recvTime,
			String srcTermID, String destTermID,String Stat) {
		System.out.println("==================");
		System.out.println("状态报告");
		System.out.println("msgID:"+msgID);
		System.out.println("recvTime:"+recvTime);
		System.out.println("Stat:"+Stat);
		System.out.println("==================");

	}

}
