package thread.exceptionthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 如果你知道将要在代码中处处使用相同的异常处理器
 * 那么更简单的方式是在Thread类中设置一个静态域
 * 并将这个处理器设置为默认的未捕获的异常处理器
 * 这个处理器只有在不存在线程专有的未捕获异常处理器的情况下才会被调用
 * 系统会检查线程专有版本，如果没有发现
 * 则检查线程组是否有其专有的uncaughtException()方法
 * 如果也没有再调用defaultUncatchExceptionHandler
 * */
public class SettingDefaultHandler {

	public static void main(String[] args) {
		//匿名内部类实现的异常处理器
		Thread.setDefaultUncaughtExceptionHandler(
				new Thread.UncaughtExceptionHandler() {
					public void uncaughtException(Thread t, Throwable e) {
						System.out.println("The exception is "+ e);
					}
				});
		ExecutorService exec = Executors.newCachedThreadPool(new MyThreadFactory());
		exec.execute(new ExceptionThread());
	}

}
