package self.aub.study.java.pattern.structure.adapter;

public class EnglishGirl {
	public static English sayEnglish() {
		return English.getInstance("Hello");
	}

	public static English lissern(English english){
		return sayEnglish();
	}
	
}
