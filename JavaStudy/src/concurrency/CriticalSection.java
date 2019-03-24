package concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @ClassName concurrency.CriticalSection.java
 * @Description 21.3.5 临界区</br>
 * 有时只是希望多个线程同时访问方法内部的部分代码而不是防止访问整个方法。
 * 通过这种方式方法分离出来的代码段被称为 “临界区”，它也使用synchronized
 * 关键字建立。这里，synchronized被用来指定某个对象，此对象的锁被用来对花
 * 括号内的代码进行同步控制：
 * synchronized(syncObject) {}
 * 这也被称为“同步控制块”；在进入此段代码前，必须得到syncObject对象的锁。
 * 使用同步代码块，而不是对整个方法进行同步控制，可以使多个任务访问对象的时间
 * 性能得到显著提升。
 * 
 * @author dayday
 * @date 2019年3月20日
 */
public class CriticalSection {
	static void testApproaches(PairManager pman1, PairManager pman2) {
		ExecutorService exec = Executors.newCachedThreadPool();
		PairManipulator 
			pm1 = new PairManipulator(pman1),
			pm2 = new PairManipulator(pman2);
		PairChecker 
			pcheck1 = new PairChecker(pman1),
			pcheck2 = new PairChecker(pman2);
		exec.execute(pm1);
		exec.execute(pm2);
		exec.execute(pcheck1);
		exec.execute(pcheck2);
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch(InterruptedException e) {
			System.out.println("Sleep interrupted");
		}
		System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
		System.exit(0);
	}
	public static void main(String[] args) {
		PairManager 
			pman1 = new PairManager1(),
			pman2 = new PairManager2();
		testApproaches(pman1, pman2);
	}
}

class Pair{	//非线程安全
	private int x, y;
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Pair() {
		this(0, 0);
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
	public String toString() {
		return "x: " + x + " y: " + y;
	}
	
	@SuppressWarnings("serial")
	public class PairValuesNotEqualException extends RuntimeException {
		public PairValuesNotEqualException() {
			super("Pair values not equal: " + Pair.this);
		}
	}
	
	public void checkState() {
		if(x != y)
			throw new PairValuesNotEqualException();
	}
}
/**
 * 演示了如何把一个非线程安全的类，在其他类的保护和控制下，运用于多线程的环境：PairManager类持有一个Pair对象
 * 对并控制对它的一切访问。
 * PairManager类的结构：它的一些功能在基类中实现，并且一个或者多个抽象方法在派生类中定义，这种结构在设计模式
 * 中被称为：模板方法。
 * PairManager1中，整个increment()方法是被同步控制的，在PairManager2中，increment()方法使用同步控制块进行同步。
 * */
//把Pair类保护进一个线程安全的类中
abstract class PairManager {
	AtomicInteger checkCounter = new AtomicInteger(0);
	protected Pair p = new Pair();
	protected Lock lock = new ReentrantLock();
	// 将一个Pair对象添加到了synchronized ArrayList中，所以这个操作是线程安全的。
	private List<Pair> storage =
			Collections.synchronizedList(new ArrayList<Pair>());	
	public synchronized Pair getPair() {
		//保存一个副本以保证原始数据安全
		return new Pair(p.getX(), p.getY());
	}
	protected void store(Pair p) {
		storage.add(p);
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch(InterruptedException ignore) {
			
		}
	}
	public abstract void increment();
}
//不使用临界区
class PairManager1 extends PairManager{
	@Override
	public synchronized void increment() {
		p.incrementX();
		p.incrementY();
		store(getPair());
	}
}
//使用临界区
class PairManager2 extends PairManager{
	@Override
	public void increment() {
		Pair temp;
		synchronized(this) {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		}
		store(temp);
	}
}
/**
 * 被创建用来测试两种不同类型的PairManager
 * */
class PairManipulator implements Runnable {
	private PairManager pm;
	public PairManipulator(PairManager pm) {
		this.pm = pm;
	}
	public void run() {
		while(true) {
			pm.increment();
		}
	}
	public String toString() {
		return "Pair: " + pm.getPair() +
				" checkCounter = " + pm.checkCounter.get();
	}
}
/**
 * 为了跟踪可以运行测试的频率，PairChecker在每次成功运行后都会递增checkCount
 * */
class PairChecker implements Runnable {
	private PairManager pm;
	public PairChecker(PairManager pm) {
		this.pm = pm;				
	}
	public void run() {
		while(true) {
			pm.checkCounter.incrementAndGet();	// checkCounter++
			try {
				pm.getPair().checkState();
			} catch(Exception e) {
				System.out.println(pm.getPair().toString());
				throw e;
			}
		}
	}
}
