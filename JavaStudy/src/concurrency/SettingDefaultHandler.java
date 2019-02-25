package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName concurrency.SettingDefaultHandler
 * @Description 21.2.14 捕获异常</br>
 * 如果你知道将要在代码中处处使用相同的异常处理器那么更简单的
 * 方式是在Thread类中设置一个静态域并将这个处理器设置为默认的
 * 未捕获的异常处理器, 并将这个处理器设置为默认的未捕获的异常
 * 处理器. 这个处理器只有在不存在线程专有的未捕获异常处理器的情
 * 况下才会被调用, 系统会检查线程专有版本，如果没有发现, 则检查
 * 线程组是否有其专有的uncaughtException()方法, 如果也没有再调用
 * defaultUncatchExceptionHandler
 * 
 * @author dayday
 * @date 2019年2月23日
 */
public class SettingDefaultHandler {

	public static void main(String[] args) {
		//匿名内部类实现的异常处理器
		Thread.setDefaultUncaughtExceptionHandler(
				new Thread.UncaughtExceptionHandler() {
					public void uncaughtException(Thread t, Throwable e) {
						System.out.println("The exception is "+ e);
					}
				});
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new ExceptionThread());
	}
}
