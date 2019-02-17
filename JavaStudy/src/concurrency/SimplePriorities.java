package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName concurrency.SimplePriorities
 * @Description 21.2.6 优先级
 * @Todo 用Mac补上注释
 * 
 * @author dayday
 * @date 2019年2月17日
 */
public class SimplePriorities implements Runnable {
	private int countDown = 5;
	private volatile double d;	// no optimization
	private int priority;
	
	public SimplePriorities(int priority) {
		this.priority = priority;
	}
	
	@Override
	public String toString() {
		return Thread.currentThread() + ": " + countDown;
	}
	
	@Override
	public void run() {
		// 优先级是在 run() 的开头部分设定的, 在构造器中设置他们不会
		// 有任何好处, 因为 Executor 在此刻还没有开始执行任务
		Thread.currentThread().setPriority(priority);
		while (true) {
			// 以下的 for 循环是一个大开销的操作, 使得当前线程有较大的机会
			// 被"抢占" 
			// P.S. 可以尝试注释掉 for 中的操作再执行一次
			for (int i = 1; i < 10000; i++) {
				d += (Math.PI + Math.E) / (double)i;
				if (i % 1000 == 0) {
					Thread.yield();
				}
			}
			System.out.println(this);
			if (--countDown == 0) {
				return;
			}
		}
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
		}
		exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
		exec.shutdown();
	}

}
