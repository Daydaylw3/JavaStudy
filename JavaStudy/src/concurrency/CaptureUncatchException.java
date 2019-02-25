package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @ClassName concurrency.CaptureUncatchException
 * @Description 21.2.14 捕获异常</br>
 * 展示了如何捕获线程中的异常：
 * <pre>
 * 1、需要一个Handler类来实现Thread.UncatchExceptionHandler接口
 * 2、创建的线程需要在setUncatchExceptionHandler()方法中指定Handler类:
 *   <code>t.setUncatchExceptionHandler(Thread.UncatchExceptionHandle)</code>
 * </pre>
 * 
 * @author dayday
 * @date 2019年2月23日
 */
public class CaptureUncatchException {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new MyThreadFactory());
		exec.execute(new ExceptionThread2());
		// or this way without threadfactory
		Thread t1 = new Thread(new ExceptionThread2());
		t1.setUncaughtExceptionHandler(new MyUncatchExceptionHandler());
		t1.start();
		// or this way
		Thread t2 = new Thread(new ExceptionThread2());
		t2.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(t.getName() + " new way catch " + e);
				t.start();
			}
		});
		t2.setName("thread2");
		t2.start();
	}
}

class ExceptionThread2 implements Runnable {
	@Override
	public void run() {
		Thread t = Thread.currentThread();
		System.out.println("run() by " + t);
		System.out.println("eh = " + t.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}
}

class MyUncatchExceptionHandler implements Thread.UncaughtExceptionHandler {
	/* (non-Javadoc)
	 * @see java.lang.Thread.UncaughtExceptionHandler#uncaughtException(java.lang.Thread, java.lang.Throwable)
	 */
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("caught " + e);
	}
}

/*
 * JDK线程池由一个ThreadFactory来创建新的线程
 * 默认情况下为Executor.defaultThreadFactory
 * 我们可以自定义ThreadFactory，增加对线程创建与销毁的控制
 */
class MyThreadFactory implements ThreadFactory {
	/* (non-Javadoc)
	 * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
	 */
	@Override
	public Thread newThread(Runnable r) {
		System.out.println(this + " creating new thread");
		Thread t = new Thread(r);
		System.out.println("created " + t);
		// key point
		t.setUncaughtExceptionHandler(new MyUncatchExceptionHandler());
		System.out.println("eh = " + t.getUncaughtExceptionHandler());
		
		return t;
	}
}