package self.aub.study.java.third.fastjson;

public class SubObject {
	private String subName;

	public SubObject() {
	}

	public SubObject(String subName) {
		this.subName = subName;
	}

	@Override
	public String toString() {
		return "SubObject [subName=" + subName + "]";
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

}
