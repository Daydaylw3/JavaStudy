package thread.cooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Condition {
	private volatile boolean condition;
	public Condition() {
		condition = false;
	}
	public synchronized boolean getCondition() {
		return condition;
	}
	public synchronized void changeCondition() {
		if(condition)
			condition = false;
		else 
			condition = true;
	}
}

class Task1 implements Runnable {
	private Condition d;
	public Task1(Condition d) {
		this.d = d;
	}
	public void run() {
		try {
			while(!d.getCondition()) {
				synchronized(d) {
					d.wait();
					System.out.println("Waked up by task2");
					System.exit(0);
				}
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting via Interrupt");
		}
	}
}

class Task2 implements Runnable {
	private Condition d;
	public Task2(Condition d) {
		this.d = d;
	}
	public void run() {
		try {
			synchronized(d) {
				TimeUnit.SECONDS.sleep(1);
				d.changeCondition();
				d.notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

//class Task1 implements Runnable{
//	private volatile boolean condition;
//	public Task1() {
//		condition = false;
//	}
//	public void changeCondition() {
//		if(condition)
//			condition = false;
//		else
//			condition = true;
//	}
//	public void run() {
//		synchronized(this) {
//			try {
//				while(!condition) {
//					System.out.println("Begin to wait");
//					this.wait();
//					System.out.println("Waked up by task2");
//				}
//				System.out.println("Exiting via while");
//			}catch(InterruptedException e) {
//				System.out.println("Exiting via interrupted");
//			}
//		}
//	}
//}
//
//class Task2 implements Runnable{
//	private Task1 t1;
//	public Task2(Task1 task1) {
//		t1 = task1;
//	}
//	public void run() {
//		synchronized(t1) {
//			try {
//				//睡眠，模拟处理的过程
//				System.out.println("task2 Operating..");
//				TimeUnit.SECONDS.sleep(1);
//				t1.changeCondition();
//				t1.notifyAll();
//			}catch(InterruptedException e) {
//				
//			}
//		}
//	}
//}

public class Exercise21 {
	public static void main(String[] args) throws Exception{
		Condition d = new Condition();
//		System.out.println(d.getCondition());
		ExecutorService exec = Executors.newCachedThreadPool();
//		Task1 t1 = new Task1();
//		exec.execute(t1);
//		exec.execute(new Task2(t1));
		Task1 t1 = new Task1(d);
		exec.execute(t1);
		TimeUnit.SECONDS.sleep(2);
		exec.execute(new Task2(d));
		TimeUnit.SECONDS.sleep(1);
		exec.shutdownNow();
	}
}
