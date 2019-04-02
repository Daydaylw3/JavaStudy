package concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName concurrency.Interrupting.java
 * @Description 21.4.3 中断</br>
 * 为了调用interrupt()，你必须持有Thread对象。新的concurrent类库似乎在避免对Thread对象的直接操作，转而
 * 尽量通过Executor来执行所有操作。如果你在Executor上调用shutdownNow()，那么它将发送一个interrupt()调
 * 用给它启动的所有线程。然而，有时你也会希望只中断某个单一任务。如果使用Executor，那么通过调用submit()而
 * 不是executor()来启动任务，就可以持有该任务的上下文。submit()将返回一个泛型Future<?>，其中有一个未修饰
 * 的参数，因为你永远都不会在其上调用get()——持有这种Future的关键在于你可以在其上调用cancel()，并因此可以使
 * 用它来中断某个特定任务。如果你将true传递给cancel()，那么它就会拥有在该线程上调用interrupt()以停止这个线
 * 程的权限。因此，cancel()是一种中断由Executor启动的单个线程的方式。
 * 
 * 1、你能够中断对sleep()的调用（或者任何要求抛出InterruptedException的调用）
 * 2、你不能中断正在试图获取synchronized锁 或者 试图执行I/O操作的线程
 * 
 * @author dayday
 * @date 2019年3月31日
 */
public class Interrupting {
	private static ExecutorService exec = 
			Executors.newCachedThreadPool();
	static void test(Runnable r) throws InterruptedException {
		Future<?> f = exec.submit(r);	//启动线程
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("Interrupting " + r);
		f.cancel(true);	// 如果线程正在运行，通过这行来中断他
		System.out.println("Interrupt sent to " + r);
	}
	public static void main(String[] args) throws Exception{
		test(new SleepBlocked());
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		TimeUnit.SECONDS.sleep(3);
		System.out.println("Aborting with System.exit(0)");
		System.exit(0);
	}
}

/*
 * 可中断的阻塞
 * */
class SleepBlocked implements Runnable {
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
		System.out.println("Exiting SleepBlocked.run()");
	}
	public String toString() {
		return this.getClass().getName();
	}
}

/*
 * 不可中断的阻塞
 * */
class IOBlocked implements Runnable{
	private InputStream in;
	public IOBlocked(InputStream is) {
		in = is;
	}
	public void run() {
		try {
			System.out.println("Waiting for read():");
			in.read();
		} catch(IOException e) {
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("Interrupted from blocked I/O");
			} else {
				throw new RuntimeException();
			}
		}
		System.out.println("Exit IOBlocked.run()");	//没有被执行
	}
	public String toString() {
		return this.getClass().getName();
	}
}

/*
 * 不可中断的阻塞
 * */
class SynchronizedBlocked implements Runnable {
	public synchronized void f() {
		while (true) {
			Thread.yield();
		}
	}
	public SynchronizedBlocked() {
		new Thread() {
			public void run() {
				f();		// 这个线程获得了该对象的锁，而且永远不会释放
			}
		}.start();
	}
	public void run() {
		System.out.println("Trying to call f()");
		f();
		System.out.println("Exit SynchronizedBlocked.run()");		//没有被执行
	}
	public String toString() {
		return this.getClass().getName();
	}
}
