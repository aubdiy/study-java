package self.aub.study.java;

import java.util.HashMap;
import java.util.Map;

public class Convert {
	private static final Map<String, String> BASE_NUM_DATA = initBaseNumData();
	private static final Map<String, String> BASE_UNIT_DATA = initBaseUnitData();

	private static Map<String, String> initBaseNumData() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("零", "0");
		result.put("壹", "1");
		result.put("贰", "2");
		result.put("叁", "3");
		result.put("肆", "4");
		result.put("伍", "5");
		result.put("陆", "6");
		result.put("柒", "7");
		result.put("捌", "8");
		result.put("玖", "9");
		
		result.put("一", "1");
		result.put("二", "2");
		result.put("三", "3");
		result.put("四", "4");
		result.put("五", "5");
		result.put("六", "6");
		result.put("七", "7");
		result.put("八", "8");
		result.put("九", "9");
		return result;
	}
	private static Map<String, String> initBaseUnitData() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("拾", "0");
		result.put("十", "0");
		result.put("百", "00");
		result.put("佰", "00");
		result.put("仟", "000");
		result.put("千", "000");
		result.put("万", "0000");
		result.put("亿", "00000000");
		return result;
	}
	public static void main(String[] args) {
		BASE_NUM_DATA.get("零");
		BASE_UNIT_DATA.get("十");
	}
}
