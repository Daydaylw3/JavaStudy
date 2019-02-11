package thread.exceptionthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 展示了如何捕获线程中的异常：
 * 1、需要一个Handler类来实现Thread.UncatchExceptionHandler接口
 * 2、创建的线程需要在setUncatchExceptionHandler()方法中指定Handler类
 * */
/* 
 * 实现了Thread.UncaughtExceptionHandler接口
 */
class MyUncatchExceptionHandler implements Thread.UncaughtExceptionHandler{
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("caught " + e);
	}
}

/**
 * JDK线程池由一个ThreadFactory来创建新的线程
 * 默认情况下为Executor.defaultThreadFactory
 * 我们可以自定义ThreadFactory，增加对线程创建与销毁的控制
 * */
class MyThreadFactory implements ThreadFactory {
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setUncaughtExceptionHandler(new MyUncatchExceptionHandler());
		return t;
	}
}

public class CaptureUncatchException {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new MyThreadFactory());
		exec.execute(new ExceptionThread());
	}

}
