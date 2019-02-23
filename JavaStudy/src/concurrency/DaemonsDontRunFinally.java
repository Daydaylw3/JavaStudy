package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName concurrency.DaemonsDontRunFinally
 * @Description 21.2.8 后台线程<p>
 * 后台进程在不执行finally子句的情况下就会终止其run()方法
 * 
 * @author dayday
 * @date 2019年2月18日
 */
public class DaemonsDontRunFinally {
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new ADaemon());
		t.setDaemon(true);	// 注释掉这句话, finally 子句就会执行
		t.start();
	}
}

class ADaemon implements Runnable {
	@Override
	public void run() {
		try {
			System.out.println("starting adaemon");
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("exiting via interruptedexception");
		} finally {
			System.out.println("this should always run?");
		}
	}
}
