package concurrency;

/**
 * @ClassName concurrency.Joining
 * @Description 21.2.11 加入一个线程</br>
 * <pre>
 * 1. {@link Thread#join()}方法的使用:
 * 在 t1 中调用 t2.join() 则 t1线程会挂起, 直至t2线程结束
 * 
 * 2. {@link Thread#join(long)}方法的使用:
 * 加上了超时参数<code>long millis</code>, 如果t2线程在millis内都没有结
 * 束的话, join()方法总能返回
 * </pre>
 * 
 * @author dayday
 * @date 2019年2月23日
 */
public class Joining {
	public static void main(String[] args){
		Sleeper sleepy = new Sleeper("Sleepy", 1500),
				grumpy = new Sleeper("Grumpy", 1500);
		Joiner dopey = new Joiner("Dopey", sleepy),
				doc = new Joiner("Doc", grumpy);
		grumpy.interrupt();
	}
}

class Sleeper extends Thread {
	private int duration;
	public Sleeper(String name, int sleepTime) {
		super(name);
		duration = sleepTime;
		start();
	}
	public void run() {
		try {
			sleep(duration);
		} catch (InterruptedException e) {
			System.out.println(getName() + " was interrupted. " + 
		"isInterrupted(): " + isInterrupted());
			return;
		}
		System.out.println(getName() + " has awakened");
	}
}

class Joiner extends Thread {
	private Sleeper sleeper;
	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();
	}
	public void run() {
		try{
			sleeper.join();
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
		System.out.println(getName() + " join completed");
	}
}

