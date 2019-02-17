package thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class CircularSet{
	private int[] array;
	private int len;
	private int index = 0;
	public CircularSet(int size) {
		array = new int[size];
		len = size;
		for(int i = 0; i < size; i++)
			array[i] = -1;
	}
	public synchronized void add(int i) {
		array[index] = i;
		index = ++index % len;
	}
	public synchronized boolean contain(int val) {
		for(int i = 0; i < len; i++) {
			if (array[i] == val)
				return true;
		}
		return false;
	}
	public int getIndex() {
		return index;
	}
}

/**
 * SerialNumberChecker持有一个静态的CircularSet，它持有所产生的序列数
 * 包含一个内嵌的SerialChecker类，它确保序列数是唯一的
 * */
public class SerialNumberChecker {
	private static final int SIZE = 10;
	private static CircularSet serials =
			new CircularSet(1000);
	private static ExecutorService exec =
			Executors.newCachedThreadPool();
	static class SerialChecker implements Runnable {
		public void run() {
			while(true) {
				int serial = 
						SerialNumberGenerator.nextSerialNumber();
				if(serials.contain(serial)) {
					System.out.println("Duplicate: " + serial + " index: " + serials.getIndex());
					System.exit(0);
				}
				serials.add(serial);
			}
		}
	}
	public static void main(String[] args) throws Exception{
		for(int i = 0; i < SIZE; i++) {
			exec.execute(new SerialChecker());
		}
		if(args.length > 0) {
			TimeUnit.SECONDS.sleep(new Integer(args[0]));
			System.out.println("No duplicates detected");
			System.exit(0);
		}
	}

}
