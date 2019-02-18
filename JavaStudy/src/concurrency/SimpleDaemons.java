package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName concurrency.SimpleDaemons
 * @Description 21.2.8 后台线程<p>
 * @Todo 用Mac补上注释
 * 
 * @author dayday
 * @date 2019年2月17日
 */
public class SimpleDaemons implements Runnable {
	
	@Override
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " " + this);
			}
		} catch (InterruptedException ex) {
			System.out.println("sleep() interrupted");
		}
	}
	
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {
			Thread daemon = new Thread(new SimpleDaemons());
			daemon.setDaemon(true);	// must call before start();
			daemon.start();
		}
		System.out.println("all daemon started");
		// 不睡眠一会会看不见任何的后台打印结果
		TimeUnit.MILLISECONDS.sleep(1000);
	}
}
