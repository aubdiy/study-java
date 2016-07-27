package self.aub.study.java.third.jdbc;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.sql.DataSource;

/**
 * @coment 稳定性测试，定时执行查询语句
 * @author liujinxin
 */
public class TestStability {

	public static void main(String[] args) {
		String sql = "select * from user where name='1'";
		Timer timer = new Timer();
		// timer.scheduleAtFixedRate(new DBCP_Task(sql), new Date(), 10000);
		// timer.scheduleAtFixedRate(new C3P0_TASK(sql), new Date(), 10000);
		timer.scheduleAtFixedRate(new TomcatJDBC_Task(sql), new Date(), 10000);

		System.out.println("===============start===================");

		try {
			Thread.sleep(86400000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class DBCP_Task extends TimerTask {
	private static DataSource dataSource = DBCP.getDatasource();

	private String sql;

	public DBCP_Task(String sql) {
		this.sql = sql;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1; i++) {
			new Execute(this.sql, 1, dataSource).start();
		}
		System.out.println("DBCP_Task done");
	}
}

class C3P0_TASK extends TimerTask {
	private static DataSource dataSource = C3P0.getDatasource();

	private String sql;

	public C3P0_TASK(String sql) {
		this.sql = sql;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1; i++) {
			new Execute(this.sql, 1, dataSource).start();
		}
		System.out.println("C3P0_TASK done");
	}
}

class TomcatJDBC_Task extends TimerTask {
	private static DataSource dataSource = TomcatJDBC.getDatasource();

	private String sql;

	public TomcatJDBC_Task(String sql) {
		this.sql = sql;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1; i++) {
			new Execute(this.sql, 1, dataSource).start();
		}
		System.out.println("TomcatJDBC_Task done");
	}
}