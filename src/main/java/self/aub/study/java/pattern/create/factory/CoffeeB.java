package self.aub.study.java.pattern.create.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoffeeB implements Coffee {

	private final static Logger LOG = LoggerFactory.getLogger(CoffeeA.class);

	@Override
	public Coffee generateCoffee() {
		LOG.info("您的B已经做好了！");
		return this;
	}


}
