package self.aub.study.java.third.jdbc;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0 {

	private static ComboPooledDataSource datasource;
	static {
		initDataSource();
	}

	public static void initDataSource() {
		long start;
		long end;
		long executionTime;
		start = System.currentTimeMillis();

		datasource = new ComboPooledDataSource();
		try {
			datasource.setDriverClass(TestPerformance.DRIVER_CLASSNAME);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
		datasource.setJdbcUrl(TestPerformance.DB_URL);
		datasource.setUser(TestPerformance.DB_NAME);
		datasource.setPassword(TestPerformance.DB_PWD);
		
		datasource.setInitialPoolSize(TestPerformance.INIT_SIZE);
		datasource.setMinPoolSize(TestPerformance.MIN_ACTIVE);
		datasource.setMaxPoolSize(TestPerformance.MAX_ACTIVE);


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
		System.out.println("C3P0 setup DataSource  : " + executionTime + " ms");
	}

	public static ComboPooledDataSource getDatasource() {
		return datasource;
	}
}
