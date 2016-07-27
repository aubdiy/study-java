package self.aub.study.java.pattern.structure.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Adapter {
	private final static Logger LOG = LoggerFactory.getLogger(Adapter.class);

	public static English adpate(Chinaness chinaness) {
		LOG.info("接到中文内容：{}", chinaness.getContent());
		English english = English.getInstance("Hello");
		LOG.info("翻译成英文为：{}", english.getContent());
		return english;
	}
	
	public static Chinaness adpate(English english) {
		LOG.info("接到英文内容：{}", english.getContent());
		Chinaness chinaness = Chinaness.getInstance("您好");
		LOG.info("翻译成中文为：{}", chinaness.getContent());
		return chinaness;
	}
}
