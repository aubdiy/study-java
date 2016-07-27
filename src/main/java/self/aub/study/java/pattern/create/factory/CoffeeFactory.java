package self.aub.study.java.pattern.create.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoffeeFactory {

	private final static Logger LOG = LoggerFactory.getLogger(CoffeeFactory.class);

	public static Coffee generateCoffee(CoffeeName coffeeName) {
		Coffee result = null;
		LOG.info("欢迎您光临，本店环境优雅，泡MM最佳之地...(^_^)");
		switch (coffeeName) {
		case A:
			LOG.info("感谢您选用A");
			result = new CoffeeA();
			break;
		case B:
			LOG.info("感谢您选用B");
			result = new CoffeeB();
			break;
		default:
			LOG.info("对不起，本店没有您要的咖啡！");
			break;
		}
		if (result != null) {
			LOG.info("这是您的咖啡，请拿好！(^_^)");
		}
		return result;
	}
}
