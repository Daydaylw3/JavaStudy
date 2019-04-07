package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName thread.cooperation.Exercise22.java
 * @Description 21.5.1 wait()与notifyAll()</br>
 * 创建一个忙等待示例；第一个任务休眠一段时间，然后将一个标志设为true，而第二个任务
 * 在一个while循环中观察这个标志（就是忙等待），然后当该标志位true时，将其设置回false
 * 然后向控制台报告这个变化。请注意程序在忙等待中浪费了多少时间，然后创建该程序的第二
 * 个版本，其中将使用wait()而不是忙等待。
 * 
 * @author dayday
 * @date 2019年4月6日
 */
public class Exercise22 {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		Flag flag1 = new Flag(), flag2 = new Flag();
		exec.execute(new Exer22Task1(flag1));
		exec.execute(new Exer22Task2(flag1));
		exec.execute(new Exer22Task3(flag2));
		exec.execute(new Exer22Task4(flag2));
		exec.shutdown();
	}
}
class Flag {
	private volatile boolean flag = false;
	
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
/*
 * task1和task2为忙等示例
 * task3和task4为wait示例
 * */
class Exer22Task1 implements Runnable {
	private Flag flag;
	Exer22Task1(Flag flag) {
		this.flag = flag;
	}
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(2);
			flag.setFlag(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class Exer22Task2 implements Runnable {
	private Flag flag;
	private int count = 0;
	Exer22Task2(Flag flag) {
		this.flag = flag;
	}
	@Override
	public void run() {
		while (true) {
			count++;
			if (flag.getFlag()) {
				flag.setFlag(false);
				System.out.println("end: " + count);
				break;
			}
		}
	}
}
class Exer22Task3 implements Runnable {
	private Flag flag;
	Exer22Task3(Flag flag) {
		this.flag = flag;
	}
	@Override
	public void run() {
		try {
			synchronized (flag) {
				TimeUnit.SECONDS.sleep(2);
				flag.setFlag(true);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class Exer22Task4 implements Runnable {
	private Flag flag;
	private int count = 0;
	Exer22Task4(Flag flag) {
		this.flag = flag;
	}
	@Override
	public void run() {
		synchronized (flag) {
			while (true) {
				count++;
				if (flag.getFlag()) {
					System.out.println("end: " + count);
					break;
				}
				try {
					flag.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	// end of while
		}
	}
}