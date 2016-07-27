package self.aub.study.java;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceUtil {
	private static final String DEVELOPMENT_PROPERTIES_FILE_PAHT = "/development.properties";
	private static final String PRODUCTION_PROPERTIES_FILE_PAHT = "/production.properties";
	private static final Properties PROPERTIES = new Properties();

	static {
		init();
	}

	private static void init() {
		InputStream productionstream = ResourceUtil.class.getResourceAsStream(PRODUCTION_PROPERTIES_FILE_PAHT);
		InputStream developmentStream = ResourceUtil.class.getResourceAsStream(DEVELOPMENT_PROPERTIES_FILE_PAHT);
		try {
			if (productionstream != null) {
				PROPERTIES.load(productionstream);
			}
			if (developmentStream != null) {
				PROPERTIES.load(developmentStream);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return PROPERTIES.getProperty(key);
	}

	public static void main(String[] args) {
		String property = getProperty(null);
		System.out.println(property);
	}
}
