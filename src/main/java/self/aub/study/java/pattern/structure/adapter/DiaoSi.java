package self.aub.study.java.pattern.structure.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiaoSi {
	private final static Logger LOG = LoggerFactory.getLogger(DiaoSi.class);

	public static Chinaness sayChinaness() {
		return Chinaness.getInstance("您好");
	}

	public static void main(String[] args) {
		English content = Adapter.adpate(sayChinaness());
		English result = EnglishGirl.lissern(content);
		Chinaness chinanessResult = Adapter.adpate(result);
		LOG.info("英国MM说：{}", chinanessResult.getContent());
	}
}
