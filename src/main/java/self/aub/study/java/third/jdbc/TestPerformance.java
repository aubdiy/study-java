package self.aub.study.java.third.jdbc;

import java.util.Properties;

import javax.sql.DataSource;

/**
 * @coment 性能测试
 * @author liujinxin
 */
public class TestPerformance {

	public static final String DB_NAME = "root";
	public static final String DB_PWD = "123456";
	public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/recsys";

	// public static final String DB_NAME = "test";
	// public static final String DB_PWD = "test";
	// public static final String DB_URL =
	// "jdbc:mysql://10.60.32.182:3306/test";

	public static final String DRIVER_CLASSNAME = "com.mysql.jdbc.Driver";

	public static final int MAX_ACTIVE = 100;
	public static final int MIN_ACTIVE = 10;
	public static final int INIT_SIZE = 10;

	private static void testDBCP(String sql, int threadCount, int executeCount) {
		// init DataSource
		DataSource dataSource = DBCP.getDatasource();
		System.out.println("DBCP start : " + System.currentTimeMillis());
		for (int i = 0; i < threadCount; i++) {
			new Execute(sql, executeCount, dataSource).start();
		}

	}

	private static void testC3P0(String sql, int threadCount, int executeCount) {
		// init DataSource
		DataSource dataSource = C3P0.getDatasource();
		System.out.println("C3P0 start : " + System.currentTimeMillis());
		for (int i = 0; i < threadCount; i++) {
			new Execute(sql, executeCount, dataSource).start();
		}
	}

	private static void testTomcatJDBC(String sql, int threadCount, int executeCount) {
		// init DataSource
		DataSource dataSource = TomcatJDBC.getDatasource();
		System.out.println("TomcatJDBC start : " + System.currentTimeMillis());
		for (int i = 0; i < threadCount; i++) {
			new Execute(sql, executeCount, dataSource).start();
		}
	}
	
	private static void testDruid(String sql, int threadCount, int executeCount) {
		// init DataSource
		DataSource dataSource = Druid.getDatasource();
		System.out.println("Druid start : " + System.currentTimeMillis());
		for (int i = 0; i < threadCount; i++) {
			new Execute(sql, executeCount, dataSource).start();
		}
	}


	private static void testProxool(String sql, int threadCount, int executeCount) {
		// init
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Properties properties = new Properties();
		properties.setProperty("proxool.maximum-connection-count", "20");
		properties.setProperty("proxool.house-keeping-test-sql", "select CURRENT_DATE");
		properties.setProperty("user", "root");
		properties.setProperty("password", "123456");
		String alias = "aub";
		String driverClass = "com.mysql.jdbc.Driver";
		String driverUrl = "jdbc:mysql://localhost:3306/test";
		// String url = "proxool." + alias + ":" + driverClass + ":" +
		// driverUrl;
		String url = "proxool." + alias + ":" + driverClass + ":" + driverUrl;

		long start;
		long end;
		long executionTime;
		start = System.currentTimeMillis();
		for (int i = 0; i < threadCount; i++) {
			new Proxool(sql, executeCount, url, properties).start();
		}
		end = System.currentTimeMillis();
		executionTime = end - start;
		System.out.println("Proxool total  : " + executionTime + " ms");
		System.out.println("Proxool average: " + executionTime / (threadCount * executeCount) + " ms");
	}

	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("args lenth error : " + args.length);
			return;
		}
		int type = Integer.valueOf(args[0]);
		int threadCount = Integer.valueOf(args[1]);
		int executeCount = Integer.valueOf(args[2]);
		String sql = args[3];
		System.out.println("type        : " + type);
		System.out.println("threadCount : " + threadCount);
		System.out.println("executeCount : " + executeCount);
		System.out.println("sql         : " + sql);

		switch (type) {
		case 1:
			System.out.println("===================================");
			testDBCP(sql, threadCount, executeCount);
			break;
		case 2:
			System.out.println("===================================");
			testC3P0(sql, threadCount, executeCount);
			break;
		case 3:
			System.out.println("===================================");
			testTomcatJDBC(sql, threadCount, executeCount);
			break;
		case 4:
			System.out.println("===================================");
			testProxool(sql, threadCount, executeCount);
			break;
		case 5:
			System.out.println("===================================");
			testDruid(sql, threadCount, executeCount);
			break;
		default:
			System.out.println("===================================");
			System.out.println("unkown type : " + type);
			break;
		}
	}

}
