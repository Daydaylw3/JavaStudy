package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName concurrency.CachedThreadPool
 * @Description 21.2.3 使用Executor
 * 
 * @author daydaylw3
 * @date Feb 12, 2019
 */
public class CachedThreadPool {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		}
		exec.shutdown();
		exec.shutdownNow();
	}
}
