package self.aub.study.java.third.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class H2EmbeddingClient {
	private String dbDir = "./h2db/sample";
	private String user = "root";
	private String password = "123";
	
	public void useH2() {
		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:" + dbDir, user, password);
			Statement stat = conn.createStatement();
			// insert data
//			 stat.execute("DROP TABLE IF EXISTS TEST");
//			stat.execute("CREATE TABLE TEST(NAME VARCHAR)");
//			stat.execute("INSERT INTO TEST VALUES('Hello World')");
			// use data
			ResultSet result = stat.executeQuery("select name from test ");
			int i = 1;
			while (result.next()) {
				System.out.println(i++ + ":" + result.getString("name"));
			}
			result.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		H2EmbeddingClient h2EmbeddingClient = new H2EmbeddingClient();
		h2EmbeddingClient.useH2();
	}
}
