package self.aub.study.java.third.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackDemo {
	private static Logger log = LoggerFactory.getLogger(LogbackDemo.class);

	public static void main(String[] args) {
		long l1 = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
//			log.trace("======trace");
//			log.debug("======debug");
//			log.info("======info");
//			log.warn("======warn");
//			log.error("billing======error");
			log.error("1");
		}
		long l2 = System.currentTimeMillis();
		System.out.println(l2-l1);
	}

}
