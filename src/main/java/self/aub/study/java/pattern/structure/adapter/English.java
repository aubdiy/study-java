package self.aub.study.java.pattern.structure.adapter;

public class English {
	private String content;

	private English(String content) {
		this.content = content;
	}

	public static English getInstance(String content) {
		return new English(content);
	}

	public String getContent() {
		return content;
	}
}
