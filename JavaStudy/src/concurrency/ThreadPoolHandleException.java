package concurrency;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池异常处理
 * <p> 1, try-catch捕获
 * <p> 2, submit执行, Future.get()接收异常
 * <p> 3, 实例化时, 传入自己的ThreadFactory, 设置Thread.uncaughtExceptionHandler
 * 处理未检测的异常
 * <p> 4, 重写ThreaPoolExecutor.afterExecute方法, 处理传递的异常引用
 * 
 * @ClassName concurrency.ThreadPoolHandleException
 * 
 * @author daydaylw3
 * @date Aug 5, 2019
 */
public class ThreadPoolHandleException {
	public static void main(String[] args) {
		// 异常被吃掉了
		Thread t1 = new Thread(() -> {
			ExecutorService exec = Executors.newFixedThreadPool(5);
			for (int i = 0; i < 5; i++)
				exec.submit(() -> {		// <--- 这里只能用submit()方法, execute()方法异常会抛出当前线程
					System.out.println("current thread name: " + Thread.currentThread().getName());
					throw new NullPointerException();
				});
			exec.shutdown();
		});
		// 1, try-catch
		Thread t2 = new Thread(() -> {
			ExecutorService exec = Executors.newFixedThreadPool(5);
			for (int i = 0; i < 5; i++)
				exec.execute(() -> {	// <--- 这里用submit()方法或者execute()方法, 异常都能被捕获到
					System.out.println("current thread name: " + Thread.currentThread().getName());
					try {
						throw new NullPointerException();
					} catch (Exception e) {
						System.out.println("exception caught");
					}
				});
			exec.shutdown();
		});
		// 2, Future.get()
		Thread t3 = new Thread(() -> {
			ExecutorService exec = Executors.newFixedThreadPool(5);
			for (int i = 0; i < 5; i++) {
				Future<Object> future = exec.submit(() -> {
					System.out.println("current thread name: " + Thread.currentThread().getName());
					throw new NullPointerException();
				});
				try {
					future.get();
				} catch (Exception e) {
					System.out.println("exception caught");
				}
			}
			exec.shutdown();
		});
		// 3, Thread.uncaughtExceptionHandler
		Thread t4 = new Thread(() -> {
			ExecutorService exec = Executors.newFixedThreadPool(3, r -> {
				Thread t = new Thread(r);
				t.setUncaughtExceptionHandler((t11, e) -> {
					System.out.println(t11.getName() + " throw Exception: " + e);
				});
				return t;
			});
			for (int i = 0; i < 3; i++)
				exec.execute(() -> {
					throw new NullPointerException();
				});
			exec.shutdown();
		});
		// 4, 重写ThreaPoolExecutor.afterExecute方法
		Thread t5 = new Thread(() -> {
			ExecutorService exec = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()) {
				@Override
				protected void afterExecute(Runnable r, Throwable t) {
					super.afterExecute(r, t);
					if (t == null && r instanceof Future<?>) {
						try {
							Object result = ((Future<?>) r).get();
						} catch (CancellationException ce) {
							t = ce;
						} catch (ExecutionException e) {
							t = e.getCause();
						} catch (InterruptedException e) {
							t = e;
						} catch (NullPointerException e) {
							System.out.println(e);
						}
					}
					if (t != null) {
						System.out.println("throw Exception: " + t);
					}
				}
			};
			for (int i = 0; i < 3; i++)
				exec.execute(() -> {
					throw new NullPointerException();
				});
			exec.shutdown();
		});
		
//		t1.start();
//		t2.start();
//		t3.start();
//		t4.start();
		t5.start();
	}
}
