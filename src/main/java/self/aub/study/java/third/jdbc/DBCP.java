package self.aub.study.java.third.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class DBCP extends Thread {

	private static BasicDataSource datasource;
	static{
		initDataSource();
	}
	public static void initDataSource() {
		long start;
		long end;
		long executionTime;
		start = System.currentTimeMillis();

		datasource = new BasicDataSource();
		datasource.setUrl(TestPerformance.DB_URL);
		datasource.setDriverClassName(TestPerformance.DRIVER_CLASSNAME);
		datasource.setUsername(TestPerformance.DB_NAME);
		datasource.setPassword(TestPerformance.DB_PWD);

		datasource.setInitialSize(TestPerformance.INIT_SIZE);
		datasource.setMaxActive(TestPerformance.MAX_ACTIVE);
		datasource.setMinIdle(TestPerformance.MIN_ACTIVE);
		datasource.setMaxIdle(TestPerformance.MAX_ACTIVE);

		//心跳重连配置
//		datasource.setTestWhileIdle(false);
//		datasource.setValidationQuery("SELECT 1");
//		datasource.setTimeBetweenEvictionRunsMillis(10000);

		//回收空链接配置
//		datasource.setRemoveAbandoned(true);
//		datasource.setRemoveAbandonedTimeout(5);
//		datasource.setMinEvictableIdleTimeMillis(30000);

//		ds.setTestOnBorrow(true);
//		ds.setTestOnReturn(false);
//		ds.setMaxWait(10000);
//		ds.setLogAbandoned(true);


		for (int i = 0; i < 10; i++) {
			Connection con = null;
			try {
				con = datasource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		end = System.currentTimeMillis();
		executionTime = end - start;
		System.out.println("DBCP setup DataSource  : " + executionTime + " ms");

	}
	public static BasicDataSource getDatasource() {
		return datasource;
	}
}
