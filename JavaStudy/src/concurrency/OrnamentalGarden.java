package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName concurrency.OrnamentalGarden.java
 * @Description 21.4.1 装饰性花园</br>
 * 在这个仿真程序中，花园委员会希望了解每天通过多个大门进入公园的总人数。
 * 每个大门都有一个计数器，并且任何一个计数器数值递增时，就表示花园中的总
 * 人数的共享计数器也会递增
 * 
 * @author dayday
 * @date 2019年3月31日
 */
public class OrnamentalGarden {
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new Entrance(i));
		}
		TimeUnit.SECONDS.sleep(3);
		Entrance.cancel();
		exec.shutdown();
		if (! exec.awaitTermination(250, TimeUnit.MILLISECONDS)) {
			System.out.println("Some tasks were not terminated!");
		}
		/*
		 * Entrance对象仍然是有效的，因为在构造器中，每个Entrance对象都存储在称为entrances的静态List<Entrance>中
		 * */
		System.out.println("Total: " + Entrance.getTotal());
		System.out.println("Sum of Entrances: " + Entrance.sumEntrances());
	}
}

/**
 * 计数器类
 */
class Count {
	private int count = 0;
	private Random rand = new Random(47);
	public synchronized int increment() {
		int temp = count;
		// 有50%的几率Yield
		if (rand.nextBoolean()) {
			Thread.yield();
		}
		return (count = ++temp);
	}
	public synchronized int value() {
		return count;
	}
}

/**
 * 入口类
 */
class Entrance implements Runnable {
	private static Count count = new Count();
	private static List<Entrance> entrances = 
			new ArrayList<Entrance>();
	private int number = 0;	// 某个特定入口进入的参观者的数量
	private final int id;
	private static volatile boolean canceled = false;		// 不需要同步对其的访问
	public static void cancel() {
		canceled = true;
	}
	public Entrance(int id) {
		this.id = id;
		entrances.add(this);
	}
	// 递增number和count对象，然后休眠100毫秒
	public void run() {
		while (!canceled) {
			synchronized(this) {
				++number;
			}
			System.out.println(this + " Total: " + count.increment());
			try {
				TimeUnit.MICROSECONDS.sleep(100);
			}catch(InterruptedException e) {
				System.out.println("sleep interrupted");
			}
		}
		System.out.println("Stoping " + this);
	}
	public synchronized int getValue() {
		return number;
	}
	public String toString() {
		return "Entrance " + id + ": " + getValue();
	}
	public static int getTotal() {
		return count.value();
	}
	public static int sumEntrances() {
		int sum = 0;
		for (Entrance entrance : entrances) {
			sum += entrance.getValue();
		}
		return sum;
	}
}
