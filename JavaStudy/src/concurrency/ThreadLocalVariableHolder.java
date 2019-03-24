package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * @ClassName concurrency.ThreadLocalVariableHolder
 * @Description 21.3.7 线程本地变量存储</br>
 * 防止任务在共享资源上产生冲突的第二种方式是根除对变量的共享。线程本地存储是一种自动化机制，
 * 可以为使用相同变量的每个不同线程都创建不同的存储。
 * 
 * @author daydaylw3
 * @date Mar 22, 2019
 */
public class ThreadLocalVariableHolder {
//	通常ThreadLocal对象当做静态域存储。在创建ThreadLocal时，你只能通过get()和set()方法
//	来访问该对象的内容，其中get()方法将返回与其线程相关联的对象的副本，而set()会将参数插入
//	到为其线程存储的对象中，并返回存储中原有的对象。increment()和get()方法都不是synchronized
//	的，因为ThreadLocal保证不会出现竞争条件
	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
		private Random rand = new Random(47);
		
		@Override
		protected synchronized Integer initialValue() {
			return 0;
		}
	};
	public static void increment() {
		value.set(value.get() + 1);
	}
	public static int get() {
		return value.get();
	}
	public static void main(String[] args) throws Exception{
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++) {
			exec.execute(new Accessor(i));
		}
		TimeUnit.SECONDS.sleep(5);
		exec.shutdown();
	}
}

class Accessor implements Runnable{
	private final int id;
	public Accessor(int idn) {
		id = idn;
	}
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
			Thread.yield();
		}
	}
	public String toString() {
		return "#" + id + ":" + ThreadLocalVariableHolder.get();
	}
}