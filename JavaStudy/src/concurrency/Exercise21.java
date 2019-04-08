package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName thread.cooperation.Exercise21.java
 * @Description 21.5.1 wait()与notifyAll()</br>
 * 创建两个Runnable, 其中一个的run()方法启动并调用wait(), 第二个类应该捕获第一个Runnable对象的
 * 引用, 其run()方法应该在一定的秒数之后, 为第一个任务调用notifyAll(), 从而使第一个任务可以显示一条信息
 * 
 * @author dayday
 * @date 2019年4月6日
 */
public class Exercise21 {
	public static void main(String[] args) throws Exception{
		ExecutorService exec = Executors.newCachedThreadPool();
		Exer21Task1 task1 = new Exer21Task1();
		Exer21Task2 task2 = new Exer21Task2(task1);
		exec.execute(task1);
		exec.execute(task2);
		exec.shutdown();
	}
}
class Exer21Task1 implements Runnable {
	@Override
	public void run() {
		try {
			synchronized (this) {
				wait();
				System.out.println("task1 wake up!");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class Exer21Task2 implements Runnable {
	private Exer21Task1 obj;
	Exer21Task2(Exer21Task1 obj) {
		this.obj = obj;
	}
	@Override
	public void run() {
		try {
			synchronized (obj) {
				TimeUnit.SECONDS.sleep(3);
				obj.notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
