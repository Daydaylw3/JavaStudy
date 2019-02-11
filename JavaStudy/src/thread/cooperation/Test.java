package thread.cooperation;

import java.io.BufferedWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class Num {
	public static Object obj = new Object();
	private static int count = 0;
	private int num = 0;
	public void inc() {
		num++;
	}
	public int getNum() {
		return num;
	}
	public  void incCount() {
		synchronized(obj) {
			count ++;
		}
	}
	public synchronized int getCount() {
		return count;
	}
}

class NumBlockingQueue extends LinkedBlockingQueue<Num>{}

class Increase implements Runnable{
	private NumBlockingQueue block;
	public Increase(NumBlockingQueue b) {
		block = b;
	}
	@Override
	public void run() {
		try {
			Num n = block.take();
			for(int i = 0; i < 100; i++) {
				n.inc();
			}
			block.put(n);
		}catch(InterruptedException e) {
			
		}
	}	
}

class Increment implements Runnable {
	Num num = new Num();
	BufferedWriter bw = null;
	public Increment() {
		
	}

	@Override
	public void run() {
		for(int i = 0; i < 100; i++)
			num.incCount();
	}
	
}

public class Test {
	public static void test() throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 100; i++)
			exec.execute(new Increment());
		TimeUnit.SECONDS.sleep(4);
		exec.shutdown();
		System.out.println(new Num().getCount());
	}
	public static void main(String[] args) throws Exception{
//		Num num = new Num();
//		NumBlockingQueue block = new NumBlockingQueue();
//		block.put(num);
//		ExecutorService exec = Executors.newCachedThreadPool();
//		for(int i = 0; i < 1000; i ++) {
//			exec.execute(new Increase(block));
//		}
//		TimeUnit.SECONDS.sleep(3);
//		exec.shutdownNow();
//		System.out.println(num.getNum());
		test();
	}
		
}
