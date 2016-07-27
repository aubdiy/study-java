package self.aub.study.java.pattern.create.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * builder pattern(建造者模式)
 * 
 * @author Aub
 * 
 */
public class DiaoSi {

	public static void main(String[] args) {
		List<DinnerMenu> sequence = new ArrayList<DinnerMenu>();
		sequence.add(DinnerMenu.vegetable);
		sequence.add(DinnerMenu.wine);
		sequence.add(DinnerMenu.fruit);
		Dinner meet = new Dinner();
		meet.setSequence(sequence);
		meet.doit();
	}
}
