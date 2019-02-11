package thread.cooperation;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;

/**
 * 使用wait()和notifyAll()解决单个生产者、单个消费者问题。生产者不能溢出接受者的缓冲区
 * 而这在生产者比消费者速度快时完全有可能发生。如果消费者比生产者速度快，那么消费者不能读
 * 取多次相同的数据。不要对生产者和消费者的相对速度作任何假设。
 * */
class Dinner{
	private volatile int mealBeg = 0, 
			mealEnd = 0;
	private final int[] meal = new int[10];
	public Dinner() {
		for(int i =0; i < 10; i++)
			meal[i] = -1;
	}
	public synchronized int serverMeal() {
		return meal[(mealBeg++) % 10];
	}
	public synchronized void makeMeal(int n) {
		meal[(mealEnd++) % 10] = n;
	}
	public synchronized int getMealNum() {
		return mealEnd - mealBeg;
	}
}
class Waitness implements Runnable {
	private Kitchen kitchen;
	private int MealNum = 0;
	public Waitness(Kitchen k) {
		kitchen = k;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized (this) {
					while(kitchen.dinner.getMealNum() == 0)	//空即等待
						wait();
				}
				synchronized(kitchen.cook) {
					MealNum = kitchen.dinner.serverMeal();
					System.out.println("Waitness Server for the meal #" + MealNum);
					kitchen.cook.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(1);
			}
		}catch(InterruptedException e) {
			System.out.println("Waitness Stop Server");
		}
	}
}
class Cook implements Runnable {
	private Kitchen kitchen;
	private int mealNo = 0;
	public Cook(Kitchen k) {
		kitchen = k;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized(this) {
					while(kitchen.dinner.getMealNum() == 10)	//满即等待
						wait();
				}
				if(mealNo == 1000) {
					if(kitchen.dinner.getMealNum() == 0) {
						System.out.println("Run out of food!");
						kitchen.exec.shutdownNow();
					}
				}else if(mealNo < 1000) {
					synchronized (kitchen.waitness) {
						kitchen.dinner.makeMeal(++mealNo);
						System.out.println("Cook Ready for the meal #" + mealNo);
						kitchen.waitness.notifyAll();
					}
				}
				TimeUnit.MILLISECONDS.sleep(1);
			}
				
		}catch(InterruptedException e) {
			System.out.println("Cook Stop Cooking");
		}
	}
}
class Kitchen{
	Dinner dinner;
	Cook cook = new Cook(this);
	Waitness waitness = new Waitness(this);
	ExecutorService exec = Executors.newCachedThreadPool();
	public Kitchen() {
		dinner = new Dinner();
		exec.execute(cook);
		exec.execute(waitness);
	}
}
public class Exercise24 {
	public static void main(String[] args) {
		new Kitchen();
	}
}
