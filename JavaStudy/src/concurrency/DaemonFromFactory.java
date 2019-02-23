package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName concurrency.DaemonFromFactory
 * @Description 21.2.8 后台线程<p>
 * 你现在可以用一个新的DaemonThreadFactory作为参数传递给Executor.newCachedThreadPool()
 * 
 * @see concurrency.DaemonThreadFactory
 * 
 * @author dayday
 * @date 2019年2月17日
 */
public class DaemonFromFactory implements Runnable {
	
	@Override
	public void run() {
		try {
			while(true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " " + this);
			}
		}catch(InterruptedException e) {
			System.out.print("sleep() interrupted");
		}
	}
	
	public static void main(String[] args) throws Exception{
		ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for(int i = 0; i < 10; i++) {
			exec.execute(new DaemonFromFactory());
		}
		System.out.println("All daemons started");
		TimeUnit.MILLISECONDS.sleep(150);
	}
	
}
