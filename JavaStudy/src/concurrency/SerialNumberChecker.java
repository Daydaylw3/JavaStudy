package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName concurrency.SerialNumberChecker
 * @Description 21.3.3 原子性与易变性</br>
 * 
 * @author daydaylw3
 * @date Mar 18, 2019
 */
public class SerialNumberChecker {
	private static final int SIZE = 10;
	private static CircularSet serials = new CircularSet(1000);
	private static ExecutorService exec = Executors.newCachedThreadPool();
	
	static class SerialChecker implements Runnable {
		@Override
		public void run() {
			while (true) {
				int serial = SerialNumberGenerator.nextSerialNumber();
				if (serials.contains(serial)) {
					System.out.println("duplicate: " + serial);
					System.exit(0);
				}
				serials.add(serial);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < SIZE; i++) {
			exec.execute(new SerialChecker());
		}
		TimeUnit.SECONDS.sleep(4);
		System.out.println("no duplicates detected");
		System.exit(0);
	}
}

class CircularSet {
	private int[] array;
	private int len;
	private int index = 0;
	
	public CircularSet(int size) {
		array = new int[size];
		len = size;
		for (int i = 0; i < size; i++) {
			array[i] = -1;
		}
	}
	
	public synchronized void add(int i) {
		array[index] = i;
		index = ++ index % len;
	}
	
	public synchronized boolean contains(int val) {
		for (int i = 0; i < len; i++) {
			if (array[i] == val) {
				return true;
			}
		}
		return false;
	}
}
