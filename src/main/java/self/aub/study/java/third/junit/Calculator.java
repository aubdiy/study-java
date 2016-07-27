package self.aub.study.java.third.junit;

public class Calculator {
	private static int result;

	public void add(int n) {
		result = result + n;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		Calculator.result = result;
	}

}
