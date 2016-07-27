package self.aub.study.java.socket;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TcpClientPool {

	// 最大线程数
	private static final int MAX_POOL_SIZE = 5;
	// 核心线程数
	private static final int _CORE_POOL_SIZE = 5;
	// 线程空闲时间
	private static final int _KEEP_ALIVE_TIME = 3000;
	// 线程队列大小
	private static final int _POOL_QUEUE_SIZE = 100;
	// 线程池维护线程所允许的空闲时间的单位:秒
	private static final TimeUnit KEEP_ALIVE_UNIT = TimeUnit.SECONDS;

	/**
	 * 线程池所使用的缓冲队列
	 */
	private static final ArrayBlockingQueue<Runnable> WORK_QUEUE = new ArrayBlockingQueue<Runnable>(
			_POOL_QUEUE_SIZE);

	/**
	 * 线程池对拒绝任务的处理策略
	 */
	private static final RejectedExecutionHandler REJECTED_HANDLER = new ThreadPoolExecutor.AbortPolicy();

	private static ThreadPoolExecutor threadPool;

	static {
		init();
	}

	/**
	 * 初始化
	 */
	private static final void init() {
		// 构造一个线程池
		threadPool = new ThreadPoolExecutor(_CORE_POOL_SIZE,
				MAX_POOL_SIZE, _KEEP_ALIVE_TIME, KEEP_ALIVE_UNIT,
				WORK_QUEUE, REJECTED_HANDLER);
	}

	/**
	 *@param record
	 *@return boolean
	 *@author LJX
	 *@date 2011-3-21 下午07:09:34
	 *@comment
	 */
	public static synchronized boolean putTask(String record) {
		TcpClient client = new TcpClient(record);
		try {
			threadPool.execute(client);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(1);
		for (int i = 0; i < 100; i++) {
			TcpClientPool.putTask("client" + i);
		}
	}

}
