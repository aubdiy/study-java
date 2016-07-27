package self.aub.study.java.pattern.create.builder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dinner {
	private final static Logger LOG = LoggerFactory.getLogger(Dinner.class);

	private List<DinnerMenu> sequence;

	private void soup() {
		LOG.info("这是汤");
	}

	private void vegetable() {
		LOG.info("这是蔬菜");
	}

	private void wine() {
		LOG.info("这是酒");
	}

	private void fruit() {
		LOG.info("水果");
	}

	public void setSequence(List<DinnerMenu> sequence) {
		this.sequence = sequence;
	}

	public void doit() {
		if (sequence == null) {
			LOG.info("屌丝，别忘了了解下MM的吃饭习惯哦！");
			return;
		}
		LOG.info("亲，大餐开始咯，请享用！");
		for (DinnerMenu menu : sequence) {
			switch (menu) {
			case soup:
				this.soup();
				break;
			case vegetable:
				this.vegetable();
				break;
			case wine:
				this.wine();
				break;
			case fruit:
				this.fruit();
				break;
			default:
				break;
			}
		}
		LOG.info("亲，吃的如何呀！(^_^)");
	}
}
