package thread.cooperation;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class MealBlockingQueue {
	private enum Status { FRESH, COOKED, SERVERED }
	private Status status = Status.FRESH;
	private final int id;
	public MealBlockingQueue(int idn) {
		id = idn;
	}
	public void makeMeal() {
		status = Status.COOKED;
	}
	public void serverMeal() {
		status = Status.SERVERED;
	}
	public Status getStatus() {
		return status;
	}
	public int getId() {
		return id;
	}
	public String toString() {
		return "meal#" + id + " is " + status;
	}
}
class KitchenTableQueue extends ArrayBlockingQueue<MealBlockingQueue>{
	public KitchenTableQueue() {
		super(10);
	}
}

class WaitnessBlockingQueue implements Runnable {
	private KitchenTableQueue table;
	
	public WaitnessBlockingQueue(KitchenTableQueue queue) {
		table = queue;
	}
	public void run() {
		try {
			while(!Thread.currentThread().isInterrupted()) {
				MealBlockingQueue meal = table.take();
				meal.serverMeal();
				System.out.println(meal);
			}
		}catch(InterruptedException e) {
			System.out.println("Waitness interrupted");
		}
		System.out.println("Waitness off");
	}
}

class CookBlockingQueue implements Runnable {
	private KitchenTableQueue table;
	private int num = 0;
	public CookBlockingQueue(KitchenTableQueue queue) {
		table = queue;
	}
	public void run() {
		try {
			while(!Thread.currentThread().isInterrupted()) {
				TimeUnit.MICROSECONDS.sleep(1);
				MealBlockingQueue meal = new MealBlockingQueue(num++);
				meal.makeMeal();
				System.out.println(meal);
				table.put(meal);
//				if(num == 1000) {
//					while(!table.isEmpty()) {}
//				}
			}
		}catch(InterruptedException e) {
			System.out.println("Cook interrupted");
		}
		System.out.println("Cook off");
	}
}

public class Exercise24BlockingQueue {
	public static void main(String[] args) throws Exception{
		KitchenTableQueue table = new KitchenTableQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new CookBlockingQueue(table));
		exec.execute(new WaitnessBlockingQueue(table));
		TimeUnit.SECONDS.sleep(4);
		exec.shutdownNow();
	}

}
