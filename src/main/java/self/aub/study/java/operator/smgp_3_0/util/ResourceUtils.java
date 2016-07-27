package self.aub.study.java.operator.smgp_3_0.util;

import java.util.PropertyResourceBundle;

public class ResourceUtils {
	private static PropertyResourceBundle resourceBundle;
	
	public ResourceUtils(String resourceFile){
		resourceBundle = (PropertyResourceBundle) PropertyResourceBundle.getBundle(resourceFile);
	}

	public static String getString(String key) {
			return resourceBundle.getString(key);
	}

	public static void main(String[] args) {
		System.out.println(getString("s6"));
	}
}
