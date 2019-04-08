package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName concurrency.MissedSignal.java
 * @Description 21.5.1 wait()与notifyAll() --- 错失的信号</br>
 * T1与T2协作的时候，有可能会错过某个信号：
 * 当T1调用notify()时，T2线程任务已经执行到了point1与monitor.wait()之间，
 * 这时候，T2再调用monitor.wati()，因此T2将无限地等待T1的信号，从而产生死锁
 * 
 * @author dayday
 * @date 2019年4月6日
 */
public class MissedSignal {
	public static void main(String[] args) {
		Object monitor = new Object();
		MissedSignal m = new MissedSignal();
		Thread1 T1 = m.new Thread1(monitor);
		Thread2 T2 = m.new Thread2(monitor);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(T1);
		exec.execute(T2);
		exec.shutdown();
	}
	
	class Thread1 implements Runnable {
		private Object monitor;
		Thread1(Object monitor) {
			this.monitor = monitor;
		}
		@Override
		public void run() {
			synchronized (monitor) {
				monitor.notify();
				System.out.println("通知完毕, T1 out");
			}
		}
	}
	
	class Thread2 implements Runnable {
		private Object monitor;
		Thread2(Object monitor) {
			this.monitor = monitor;
		}
		@Override
		public void run() {
			while (true) {
				System.out.println("point1");
				synchronized (monitor) {
					try {
						monitor.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}	// end of while (true)
		}
	}
}
