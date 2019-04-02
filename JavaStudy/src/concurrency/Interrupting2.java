package concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName concurrency.Interrupting2
 * @Description 21.4.3 中断</br>
 * 无论在任何时刻只要任务 以不可中断的方式被阻塞，都有潜在的会锁住程序的可能
 * Java SE5并发类库添加了一个特性，即在ReentrantLock上阻塞的任务具备可以
 * 被中断的能力，这与在synchronized方法或临界区上阻塞的任务完全不同
 * 
 * @author daydaylw3
 * @date Apr 1, 2019
 */
public class Interrupting2 {
	public static void main(String[] args) throws Exception{
		Thread t = new Thread(new Blocked2());
		t.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Issuing t.interrupt()");
		// 尽管不太可能,但是对t.interrupt()的调用确实可以发生在blocked.f()的调用之前
		t.interrupt();
	}
}

class BlockedMutex {
	private Lock lock = new ReentrantLock();
	public BlockedMutex() {
		//获取所创建对象上自身的锁Lock，并且从不释放这个锁。
		lock.lock();
	}
	public void f() {
		try {
			/*
			 * 1）如果当前线程未被中断，则获取锁。 
			 * 2）如果该锁没有被另一个线程保持，则获取该锁并立即返回，将锁的保持计数设置为 1。 
			 * 3）如果当前线程已经保持此锁，则将保持计数加 1，并且该方法立即返回。
			 * 4）如果锁被另一个线程保持，则出于线程调度目的，禁用当前线程，并且在发生以下两种情况之一以前，该线程将一直处于休眠状态：
			 * 		1）锁由当前线程获得；或者 
			 * 		2）其他某个线程中断当前线程。
			 * 5）如果当前线程获得该锁，则将锁保持计数设置为 1。 
			 * 		如果当前线程：
			 * 		1）在进入此方法时已经设置了该线程的中断状态；或者 
			 * 		2）在等待获取锁的同时被中断。 则抛出 InterruptedException，并且清除当前线程的已中断状态。
			 * 6）在此实现中，因为此方法是一个显式中断点，所以要优先考虑响应中断，而不是响应锁的普通获取或 重入获取。
			 * */
			lock.lockInterruptibly();	// 特殊的获取锁的方式，在未获取锁的时候会阻塞，但是会响应中断
			
			System.out.println("lock acquired in f()");
		}catch(InterruptedException e) {
			System.out.println("Interrupted from lock acquisition in f()");
		}
	}
}

class Blocked2 implements Runnable {
	BlockedMutex blocked = new BlockedMutex();
	public void run() {
		System.out.println("Waiting for f() in BlockedMutex");
		blocked.f();
		System.out.println("Broken out of blocked call");
	}
}