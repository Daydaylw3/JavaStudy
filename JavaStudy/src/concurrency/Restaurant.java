package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName concurrency.Restaurant.java
 * @Description 21.5.3 生产者与消费者</br>
 * 考虑这么一个饭店，它有一个厨师和一个服务员。这个服务员必须等待厨师准备好膳食。
 * 当厨师准备好时，他会通知服务员，之后服务员上菜，然后返回继续等待。这是一个任务
 * 协作的示例：厨师代表生产者，服务员代表消费者。两个任务必须在膳食被生产和消费时
 * 进行握手，而系统必须以有序的方式关闭。
 * 
 * @author dayday
 * @date 2019年4月7日
 */
public class Restaurant {
	Meal meal;
	ExecutorService exec = Executors.newCachedThreadPool();
	WaitPerson waitPerson = new WaitPerson(this);
	Chef chef = new Chef(this);
	
	public Restaurant() {
		exec.execute(chef);
		exec.execute(waitPerson);
	}
	
	public static void main(String[] args) {
		new Restaurant();
	}
}

class Meal {
	private final int orderNum;
	
	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}
	
	@Override
	public String toString() {
		return "Meal " + orderNum;
	}
}

class Chef implements Runnable {
	private Restaurant restaurant;
	private int count = 0;
	
	public Chef(Restaurant r) {
		restaurant = r;
	}
	
	@Override
	public void run() {
		try {
			while (! Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal != null) {
						this.wait();		// 等待meal被取走
					}
				}
				if (++ count == 10) {
					System.out.println("out of meal, closing!");
					restaurant.exec.shutdownNow();
					System.exit(0);
				}
				TimeUnit.MICROSECONDS.sleep(100);
				System.out.print("order up! ");
				synchronized (restaurant.waitPerson) {
					restaurant.meal = new Meal(count);
					restaurant.waitPerson.notifyAll();
				}
			}	// end of whild
		} catch (InterruptedException e) {
			System.out.println("chef interrupted");
		}
	}
}

class WaitPerson implements Runnable {
	private Restaurant restaurant;
	
	public WaitPerson(Restaurant r) {
		restaurant = r;
	}
	
	@Override
	public void run() {
		try {
			while (! Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal == null) {
						this.wait();		// 等待meal的制作
					}
				}
				System.out.println("waitperson got " + restaurant.meal);
				synchronized (restaurant.chef) {
					restaurant.meal = null;
					restaurant.chef.notifyAll();
				}
			}
		} catch (InterruptedException e) {
			System.out.println("waitperson interrupted");
		}
	}
}