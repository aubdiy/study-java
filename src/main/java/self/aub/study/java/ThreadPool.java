package self.aub.study.java;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
	private static final int CORE_POOL_SIZE = 10;
	private static final int MAX_POOL_SIZE = 20;
	private static final long KEEP_ALIVE_TIME = 5;

	private static BlockingQueue<Runnable> queue = new LinkedBlockingDeque<Runnable>();

	private static ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
			TimeUnit.MINUTES, queue);

	private ThreadPool() {

	}

	public static boolean execute(Runnable command) {
		try {
			executor.execute(command);
			return true;
		} catch (RejectedExecutionException e) {
			return false;
		}
	}
}
