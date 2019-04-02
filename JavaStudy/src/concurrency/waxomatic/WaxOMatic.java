package concurrency.waxomatic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName concurrency.waxomatic.WaxOMatic
 * @Description 21.5.1 wait()与notifyAll()</br>
 * 
 * 
 * @author daydaylw3
 * @date Apr 2, 2019
 */
public class WaxOMatic {
	public static void main(String[] args) throws Exception {
		Car car = new Car();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOff(car));
		exec.execute(new WaxOn(car));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}

class Car {
	private boolean waxOn = false;
	public synchronized void waxed() {
		waxOn = true;	//打蜡结束，准备抛光
		notifyAll();
	}
	public synchronized void buffed() {
		waxOn = false;	//抛光结束，准备打蜡
		notifyAll();		
	}
	public synchronized void waitForWaxing() throws InterruptedException{
		while(!waxOn)
			wait();
	}
	public synchronized void waitForBuffing() throws InterruptedException{
		while(waxOn)
			wait();
	}
}

class WaxOn implements Runnable{
	private Car car;
	public WaxOn(Car c) {
		car = c;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				System.out.print("Wax On! ");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed();
				car.waitForBuffing();
			}
		}catch(InterruptedException e) {
			System.out.println("Exiting via interrupt");
		}
		System.out.println("Ending Wax On task");
	}
}

class WaxOff implements Runnable {
	private Car car;
	public WaxOff(Car c) {
		car = c;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				System.out.print("Wax Off! ");
				TimeUnit.MILLISECONDS.sleep(200);
				car.buffed();
				car.waitForWaxing();
			}
		}catch(InterruptedException e) {
			System.out.println("Exitiing via interrupt");
		}
		System.out.println("End Wax Off task");
	}
}
