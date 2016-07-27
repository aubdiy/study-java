package self.aub.study.java.operator.smgp_3_0.test;

import self.aub.study.java.operator.smgp_3_0.SMGPMo;

public class TestMO extends SMGPMo{

	@Override
	public void dealMO(String msgID,String recvTime, String srcTermID, String destTermID,
			String msgContent) {
		System.out.println("==================");
		System.out.println("短信上行");
		System.out.println("msgID:"+msgID);
		System.out.println("recvTime:"+recvTime);
		System.out.println("srcTermID:"+srcTermID);
		System.out.println("destTermID:"+destTermID);
		System.out.println("msgContent:"+msgContent);
		System.out.println("==================");
	}

}
