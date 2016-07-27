package self.aub.study.java.third.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;

public class TestJdbc {

	public static void main(String[] args) throws SQLException {

		DataSource ds = TomcatJDBC.getDatasource();
		Connection conn = ds.getConnection();
		PreparedStatement ps = conn
				.prepareStatement("select * from sys_user where id=? and loginName=? and createTime > ?");
		ps.setString(1, "1");
		ps.setString(2, "ln");
		ps.setString(3, "2001-12-01");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString(1));
			System.out.println(rs.getString(2));
			System.out.println(rs.getObject(3));
			System.out.println(rs.getObject(4));
			System.out.println(rs.getObject(5));
			System.out.println(rs.getString(6));

		}
	}
}
