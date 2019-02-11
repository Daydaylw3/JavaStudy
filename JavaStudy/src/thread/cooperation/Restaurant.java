package thread.cooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 这样一个饭店，他有一个厨师和一个服务员。这个服务员必须等待厨师准备好膳食。当
 * 厨师准备好时，他会通知服务员，之后服务员上菜，然后返回继续等待。这是一个任务
 * 协作的示例：厨师代表生产者，而服务员代表消费者。两个任务必须在膳食被生产和被
 * 消费时进行握手，而系统必须以有序的方式关闭。
 * */
class Meal{
	private final int orderNum;
	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}
	public String toString() {
		return "Meal " + orderNum;
	}
}

class WaitPerson implements Runnable{
	private Restaurant restaurant;
	public WaitPerson(Restaurant r) {
		restaurant = r;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized(this) {
					while(restaurant.meal == null) {
						wait();
					}
				}
				System.out.println("WaitPerson got " + restaurant.meal);
				synchronized(restaurant.chef) {
					restaurant.meal = null;
					restaurant.chef.notifyAll();
				}
			}
		} catch(InterruptedException e) {
			System.out.println("WaitPerson interrupted");
		}
	}
}

class Chef implements Runnable{
	private Restaurant restaurant;
	private int count = 0;
	public Chef(Restaurant r) {
		restaurant = r;
	}
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized(this) {
					while(restaurant.meal != null) {
						wait();
					}
				}
				if(++count == 10) {
					System.out.println("Out of food, closing");
					restaurant.exec.shutdownNow();
				}
				System.out.print("Order up! ");
				synchronized(restaurant.waitPerson) {
					restaurant.meal = new Meal(count);
					restaurant.waitPerson.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		}catch(InterruptedException e) {
			System.out.println("Chef interrupted");
		}
	}
}

public class Restaurant {
	Meal meal;
	ExecutorService exec = Executors.newCachedThreadPool();
	WaitPerson waitPerson = new WaitPerson(this);
	Chef chef = new Chef(this);
	public Restaurant() {
		exec.execute(waitPerson);
		exec.execute(chef);
	}
	public static void main(String[] args) {
		new Restaurant();
	}
}
