package concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName concurrency.AttemptLocking
 * @Description 21.3.2 解决共享资源竞争</br>
 * 使用synchronize关键字不能尝试着获取锁且最终获取锁失败，或者尝试着获取锁一段时间，然后放弃它。
 * ReentrantLock允许你尝试着获取但最终未获得锁，这样如果其他人已经获取了这个锁，那你就可以决定离开去
 * 执行一些其他事情，而不是等待直至这个锁被释放，就如同在untimed()方法中看到的：在timed()方法中，做
 * 出了尝试去获取锁，该尝试可以再2秒后失败。在main()中，作为匿名类而创建了一个单独的线程来获取锁，这使
 * 得untimed()和timed()方法对某些事物产生竞争
 * 
 * @author daydaylw3
 * @date Mar 18, 2019
 */
public class AttemptLocking {
	private ReentrantLock reentrantLock = new ReentrantLock();
	public void untimed() {
		boolean capture = reentrantLock.tryLock();
		try {
			System.out.println("tryLock(): " + capture);
		}finally {
			if(capture) {
				reentrantLock.unlock();
			}
		}
	}
	public void timed() {
		boolean capture = false;
		try {
			capture = reentrantLock.tryLock(2, TimeUnit.SECONDS);
		}catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
		try {
			System.out.println("tryLock(2, TimeUnit.SECONDS): " + 
					capture);
		}finally {
			if(capture)
				reentrantLock.unlock();
		}
	}
	public static void main(String[] args) {
		final AttemptLocking al = new AttemptLocking();
		al.untimed();
		al.timed();
		new Thread(){
			{
				setDaemon(true);
			}
			public void run() {
				al.reentrantLock.lock();
				System.out.println("acquired");
			}
		}.start();
		Thread.yield();
		al.untimed();
		al.timed();
	}

}
