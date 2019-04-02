package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName concurrency.InterruptingIdiom
 * @Description 21.4.4 检查中断</br>
 * 
 * @author daydaylw3
 * @date Apr 1, 2019
 */
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

// NeedsCleanup这个类强调你经由异常循环离开的时候，必须正确清理资源
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

/*
 * 1.在point1到point2之间,任务是即将进入阻塞状态或者阻塞状态,这个任务就会在第一次试图
 *   调用阻塞之前，经由InterruptedException退出
 * 2.在point2之后,任务是非阻塞状态,那么首先循环将结束,然后所有的本地对象将被销毁,最后
 *   循环经由while语句的顶部退出
 * */
class Blocked3 implements Runnable{
	private volatile double d = 0.0;
	public void run() {
		try {
			while(!Thread.interrupted()) {
				// point1
				NeedsCleanup n1 = new NeedsCleanup(1);
				try {
					System.out.println("Sleeping");
					TimeUnit.SECONDS.sleep(1);
					//point2
					NeedsCleanup n2 = new NeedsCleanup(2);
					try {
						System.out.println("Calculating");
						for(int i = 1; i < 2500000; i++)	// 当你增大计算次数,以增加非阻塞的时间,就可以看到程序通过while循环退出
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