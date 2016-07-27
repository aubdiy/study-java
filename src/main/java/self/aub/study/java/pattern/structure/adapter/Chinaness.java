package self.aub.study.java.pattern.structure.adapter;

public class Chinaness {
	private String content;

	private Chinaness(String content) {
		this.content = content;
	}

	public static Chinaness getInstance(String content) {
		return new Chinaness(content);
	}

	public String getContent() {
		return content;
	}
}
