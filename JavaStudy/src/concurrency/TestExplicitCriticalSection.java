package concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName concurrency.TestExplicitCriticalSection
 * @Description 测试ExplicitCriticalSection出现的问题,
 * 为ExplicitCriticalSection的简化版
 * 
 * @see concurrency.ExplicitCriticalSection
 * 
 * @author daydaylw3
 * @date Mar 21, 2019
 */
public class TestExplicitCriticalSection {
	public static void main(String[] args) {
		B b = new B();
		new Thread(new TaskIncrement(b)).start();
		new Thread(new TaskCheck(b)).start();
	}
}
class TaskIncrement implements Runnable {
	private B b;
	public TaskIncrement(B b) {
		this.b = b;
	}
	@Override
	public void run() {
		while (true) {
			b.increment();
		}
	}
}
class TaskCheck implements Runnable {
	private B b;
	public TaskCheck(B b) {
		this.b = b;
	}
	@Override
	public void run() {
		while (true) {
			if (! b.getB().checkEqual()) {
				throw new RuntimeException(b.getB().toString());
			}
		}
	}
}
class B {
	private int x = 0;
	private int y = 0;
	
	public B() {
		
	}
	public B(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void incrementX() {
		x++;
	}
	public void incrementY() {
		y++;
	}
	public boolean checkEqual() {
		return x == y;
	}
	@Override
	public String toString() {
		return "x = " + x + ", y = " + y;
	}
	protected Lock lock = new ReentrantLock();
	
	public B getB() {
		lock.lock();
		try {
			return new B(getX(), getY());
		} finally {
			lock.unlock();
		}
	}
	
	public void increment() {
		lock.lock();
		try {
			incrementX();
			incrementY();
		} finally {
			lock.unlock();
		}
	}
}