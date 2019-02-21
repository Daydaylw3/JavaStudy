package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName concurrency.SimpleDaemons
 * @Description 21.2.8 后台线程<p>
 * 所谓后台(daemon) 线程，是指在程序运行的时候在后台
 * 提供一种通用服务的线程，并且这种线程并不属于程序中不
 * 可或缺的部分。因此，当所有的非后台线程结束时，程序也
 * 就终止了，同时会杀死进程中的所有后台线程。反过来说，
 * 只要有任何非后台线程还在运行，程序就不会终止。比如，
 * 执行main()的就是一个非后台线程。
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
