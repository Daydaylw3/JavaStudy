package thread.cooperation;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Car2{
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean waxOn = false;
	public void waxed() {
		lock.lock();
		try {
			waxOn = true;
			condition.signalAll();
		}finally {
			lock.unlock();
		}
	}
	public void buffed() {
		lock.lock();
		try {
			waxOn = false;
			condition.signalAll();
		}finally {
			lock.unlock();
		}
	}
	public void waitForWaxing() throws InterruptedException{
		lock.lock();
		try {
			while(!waxOn) {
				condition.await();
			}
		}finally {
			lock.unlock();
		}
	}
	public void waitForBuffing() throws InterruptedException{
		lock.lock();
		try{
			while(waxOn) {
				condition.await();
			}
		}finally {
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
		try{
			while(!Thread.currentThread().isInterrupted()) {
				System.out.println("Wax On!");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed();
				car.waitForBuffing();
			}
		}catch(InterruptedException e) {
			System.out.println("Exiting via interrupt");
		}
		System.out.println("Exiting Wax On task");
	}
}

class WaxOff2 implements Runnable{
	private Car2 car;
	public WaxOff2(Car2 c) {
		car = c;
	}
	@Override
	public void run() {
		try {
			while(!Thread.currentThread().isInterrupted()) {
				car.waitForWaxing();
				System.out.println("Wax Off!");
				TimeUnit.MILLISECONDS.sleep(200);
				car.buffed();
			}
		}catch(InterruptedException e) {
			System.out.println("Exiting via interrupt");
		}
		System.out.println("Exiting Wax Off task");
	}
}

public class WaxOMatic2 {
	
	public static void main(String[] args) throws Exception{
		Car2 car = new Car2();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOn2(car));
		exec.execute(new WaxOff2(car));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}

}
