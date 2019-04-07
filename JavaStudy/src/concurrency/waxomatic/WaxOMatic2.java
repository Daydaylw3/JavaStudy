package concurrency.waxomatic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName concurrency.waxomatic.WaxOMatic2.java
 * @Description 21.5.3 生产者与消费者</br>
 * 使用显式的Lock和Condition对象重写WaxOMatic
 * 
 * @see concurrency.waxomatic.WaxOMatic
 * @author dayday
 * @date 2019年4月7日
 */
public class WaxOMatic2 {
	public static void main(String[] args) throws Exception {
		Car2 car = new Car2();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOn2(car));
		exec.execute(new WaxOff2(car));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}

class Car2 {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean waxOn = false;
	
	public void waxed() {
		lock.lock();
		try {
			waxOn = true;
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public void buffed() {
		lock.lock();
		try {
			waxOn = false;
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public void waitForWaxing() throws InterruptedException {
		lock.lock();
		try {
			while (!waxOn) {
				condition.await();
			}
		} finally {
			lock.unlock();
		}
	}
	
	public void waitForBuffing() throws InterruptedException {
		lock.lock();
		try {
			while (waxOn) {
				condition.await();
			}
		} finally {
			lock.unlock();
		}
	}
}

class WaxOn2 implements Runnable {
	private Car2 car;
	public WaxOn2(Car2 c) {
		car = c;
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("wax on! ");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed();
				car.waitForBuffing();
			}
		} catch (InterruptedException e) {
			System.out.println("exiting via interrupt");
		}
		System.out.println("end wax on task");
	}
}

class WaxOff2 implements Runnable {
	private Car2 car;
	public WaxOff2(Car2 c) {
		car = c;
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				car.waitForWaxing();
				System.out.println("wax off! ");
				TimeUnit.MICROSECONDS.sleep(200);
				car.buffed();
			}
		} catch (InterruptedException e) {
			System.out.println("exiting via interrupt");
		}
		System.out.println("end wax off task");
	}
}