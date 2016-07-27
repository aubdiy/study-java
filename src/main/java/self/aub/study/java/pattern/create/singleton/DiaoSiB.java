package self.aub.study.java.pattern.create.singleton;

public class DiaoSiB {
	private static DiaoSiB diaoSiB;

	private DiaoSiB() {
	}

	public static DiaoSiB getInstance(){
		if(diaoSiB == null ){
			diaoSiB = new DiaoSiB();
		}
		return  diaoSiB;
	}
	
}
