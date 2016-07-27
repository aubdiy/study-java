package self.aub.study.java.pattern.create.singleton;

public class DiaoSiA {
	private static DiaoSiA diaoSiA = new DiaoSiA();

	private DiaoSiA() {
	}

	public static DiaoSiA getInstance(){
		return  diaoSiA;
	}
}
