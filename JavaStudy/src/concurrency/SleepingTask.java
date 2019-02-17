package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName concurrency.SleepingTask
 * @Description 21.2.5 休眠<p>
 * 影响任务行为的一种简单方法是调用sleep(), 这将使任务中止执行给定的时间, 
 * 在LiftOff类中，要是把对yield()的调用换成是调用sleep(),将得到如下结果:
 * 
 * @see concurrency.LiftOff
 * 
 * @author daydaylw3
 * @date Feb 12, 2019
 */
public class SleepingTask extends LiftOff {
	@Override
	public void run() {
		try {
			while(countDown-- > 0) {
				System.out.print(status());
				// old-style
				// Thread.sleep(100);
				// java se5/6-style
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException ex) {
			// 异常不能跨线程传播回 main(), 所以必须在本地处理所有在任务内部
			// 产生的异常
			System.err.println("interrupted");
		}
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new SleepingTask());
		}
		exec.shutdown();
	}
}