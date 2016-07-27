package self.aub.study.java.third.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class Execute extends Thread {
	private String sql;

	private int executeCount;

	private DataSource datasource;

	public Execute(String sql, int executeCount, DataSource datasource) {
		this.sql = sql;
		this.executeCount = executeCount;
		this.datasource = datasource;
	}

	@Override
	public void run() {
		for (int i = 0; i < executeCount; i++) {
			executeSQL(sql);
		}
		long end = System.currentTimeMillis();
		System.out.println("end : " + end);
	}

	private void executeSQL(String sql) {
		Connection con = null;
		try {
			con = datasource.getConnection();
//			Statement st = con.createStatement();
//			System.out.println(st);
//			st.execute(sql);
//			ResultSet rs = st.execute(sql);
			// int cnt = 1;
			// while (rs.next()) {
			// System.out.println((cnt++) + ". ID:" + rs.getString("id") +
			// " Name:" + rs.getString("name"));
			// }
//			rs.close();
//			st.close();
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
}
