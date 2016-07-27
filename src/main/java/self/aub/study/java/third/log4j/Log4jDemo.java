package self.aub.study.java.third.log4j;

import org.apache.log4j.Logger;

public class Log4jDemo {
	private static Logger log = Logger.getLogger(Log4jDemo.class);
	public static void main(String[] args) {
		long l1 = System.currentTimeMillis();
		for (int i = 0; i < 1; i++) {
			log.debug("====log4j: debug");
			log.info("====log4j: info");
			log.warn("====log4j: warn");
			log.error("====log4j: error");
			log.fatal("====log4j: fatal");
			log.error("1");
		}
		long l2 = System.currentTimeMillis();
		System.out.println(l2-l1);

	}
}
