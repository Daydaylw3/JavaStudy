package thread.interrupted;

import java.util.concurrent.TimeUnit;
/**
 * NeedsCleanup这个类强调你经由异常循环离开的时候，必须正确清理资源
 * */
class NeedsCleanup {
	private final int id;
	public NeedsCleanup(int ident){
		id = ident;
		System.out.println("NeedsCleanup " + id);
	}
	public void cleanup() {
		System.out.println("Cleaning up " + id);
	}
}
/**
 * ①在point1到point2之间，任务是即将进入阻塞状态或者阻塞状态
 * ②在point2之后，任务是非阻塞状态
 * */
class Blocked3 implements Runnable{
	private volatile double d = 0.0;
	public void run() {
		try {
			while(!Thread.interrupted()) {
				//point1
				NeedsCleanup n1 = new NeedsCleanup(1);
				try {
					System.out.println("Sleeping");
					TimeUnit.SECONDS.sleep(1);
					//point2
					NeedsCleanup n2 = new NeedsCleanup(2);
					try {
						System.out.println("Calculating");
						for(int i = 1; i < 2500000; i++)
							d = d + (Math.PI + Math.E) / d;
						System.out.println("Finished time-consuming operation");
					} finally {
						n2.cleanup();
					}
				} finally {
					n1.cleanup();
				}
			}
			System.out.println("Exiting via while() test");
		} catch(InterruptedException e) {
			System.out.println("Exiting via InterruptedException");
		}
	}
}

/**
 * 通过使用不同的延迟，可以在任务处在不同的状态时退出Blocked3.run()：在阻塞的sleep()中调用；或者在非阻塞的数学运算中调用；
 * 在①状态中，这个任务就会在第一次试图调用阻塞之前，经由InterruptedException退出。在这种情况下，在异常被抛出之时唯一被创
 * 建出来的NeedsCleanup对象将被清除，而也就有了在catch子句中执行其他任何清理工作的机会
 * 在②状态中，那么首先循环将结束，然后所有的本地对象将被销毁，最后循环经由while语句的顶部退出。
 * */
public class InterruptingIdiom {
	public static void main(String[] args) throws Exception{
		Thread t = new Thread(new Blocked3());
		int time = (int)(Math.random() * 7200);
		System.out.println("Time: " + time);
		t.start();
		TimeUnit.MILLISECONDS.sleep(time);
		t.interrupt();
	}

}
