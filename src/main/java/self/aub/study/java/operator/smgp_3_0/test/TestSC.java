package self.aub.study.java.operator.smgp_3_0.test;

import self.aub.study.java.operator.smgp_3_0.SMGP_SC;

public class TestSC {
private static SMGP_SC smgpSC = new SMGP_SC("smgp");
	
	public static void main(String[] args) {
		String phone = "13366597179";
		String content = "内容";
		String extend = "123";
		String result = smgpSC.submit(phone, content, extend);
		if(result == null){
			//失败
		}else{
			//smgid
		}
		System.out.println(result);
	}

}
