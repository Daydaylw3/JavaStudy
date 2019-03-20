package concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName concurrency.AtomicIntegerTest.java
 * @Description 21.3.4 原子类</br>
 * Java SE5引入了诸如AtomicInteger、AtomicLong、AtomicReference等特殊的原子性变量类
 * 他们提供原子性条件更新操作
 * 
 * @author dayday
 * @date 2019年3月20日
 */
public class AtomicIntegerTest implements Runnable {
	private AtomicInteger i = new AtomicInteger();
	public int getValue() {
		return i.get();
	}
	public void evenIncrement() {
		i.addAndGet(2);
	}
	
	@Override
	public void run() {
		while(true) {
			evenIncrement();
		}
	}

	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			public void run() {
				System.out.println("Aborting");
				System.exit(0);
			}
		}, 5000);
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicIntegerTest at = new AtomicIntegerTest();
		exec.execute(at);
		while(true) {
			int val = at.getValue();
			if(val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}

}
