package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName concurrency.ExceptionThread
 * @Description 21.2.14 捕获异常</br>
 * 由于线程的本质特性，使得你不能捕获从线程中逃逸的异常, 
 * 一旦异常逃逸出任务的run()方法，他就会向外传播到控制台
 * 
 * @author dayday
 * @date 2019年2月23日
 */
public class ExceptionThread extends Thread {

	public void run() {
		throw new RuntimeException();
	}
	
	public static void main(String[] args) {
		try {
			ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(new ExceptionThread());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
