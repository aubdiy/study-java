package self.aub.study.java.operator.smgp_3_0.test;

import self.aub.study.java.operator.smgp_3_0.SMGPDeliver;
import self.aub.study.java.operator.smgp_3_0.SMGPMo;
import self.aub.study.java.operator.smgp_3_0.SMGPReport;

public class TestDeliver {
	 
	 public static void main(String[] args) {
		 SMGPMo smgpMo = new TestMO();
		 SMGPReport smgpReport = new TestReport();
		 SMGPDeliver smgpDeliver = new SMGPDeliver("smgp", smgpMo, smgpReport);
		 
		 Thread deliverThread = new Thread(smgpDeliver);
		 deliverThread.start();
		 
		 try {
			Thread.sleep(3600000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
	}
}
