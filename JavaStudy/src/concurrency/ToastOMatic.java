package concurrency;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.TimeUnit;


/**
 * @ClassName concurrency.ToastOMatic
 * @Description 21.5.4 生产者-消费者与队列</br>
 * 吐司BlockingQueue
 * 
 * @author daydaylw3
 * @date Apr 8, 2019
 */
public class ToastOMatic {
	public static void main(String[] args) throws Exception {
		ToastQueue dryQueue = new ToastQueue(),
				butteredQueue = new ToastQueue(),
				finishedQueue = new ToastQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Toaster(dryQueue));
		exec.execute(new Butterer(dryQueue, butteredQueue));
		exec.execute(new Jammer(butteredQueue, finishedQueue));
		exec.execute(new Eater(finishedQueue));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}

class Toast{
	public enum Status { DRY, BUTTERED, JAMMED }
	private Status status = Status.DRY;
	private final int id;
	public Toast(int idn) {
		id = idn;
	}
	public void butter() {
		status = Status.BUTTERED;
	}
	public void jam() {
		status = Status.JAMMED;
	}
	public Status getStatus() {
		return status;
	}
	public int getId() {
		return id;
	}
	public String toString() {
		return "Toast " + id + ": " + status;
	}
}

class ToastQueue extends LinkedBlockingQueue<Toast>{}

// 制作吐司
class Toaster implements Runnable {
	private ToastQueue toastQueue;
	private int count = 0;
	private Random rand = new Random(47);
	
	public Toaster(ToastQueue tq) { toastQueue = tq; }
	
	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				TimeUnit.MILLISECONDS.sleep(
						100 + rand.nextInt(500));
				Toast t = new Toast(count++);
				System.out.println(t);
				toastQueue.put(t);
			}
		} catch (InterruptedException e) {
			System.out.println("Toaster interrupted");
		}
		System.out.println("Toaster off");
	}
}

// 抹黄油
class Butterer implements Runnable {
	private ToastQueue dryQueue, butteredQueue;
	
	public Butterer(ToastQueue dry, ToastQueue buttered) {
		dryQueue = dry;
		butteredQueue = buttered;
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				Toast t = dryQueue.take();
				t.butter();
				System.out.println(t);
				butteredQueue.put(t);
			}
		} catch (InterruptedException e) {
			System.out.println("Butterer interrupted");
		}
		System.out.println("Butterer off");
	}
}
// 涂果酱
class Jammer implements Runnable{
	private ToastQueue butteredQueue, finishedQueue;
	
	public Jammer(ToastQueue buttered, ToastQueue finished) {
		butteredQueue = buttered;
		finishedQueue = finished;
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				Toast t = butteredQueue.take();
				t.jam();
				System.out.println(t);
				finishedQueue.put(t);
			}
		} catch(InterruptedException e) {
			System.out.println("Jammer interrupted");
		}
		System.out.println("Jammer off");
	}
}

// 食用
class Eater implements Runnable {
	private ToastQueue finishedQueue;
	private int counter = 0;
	public Eater(ToastQueue finished) {
		finishedQueue = finished;
	}
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				Toast t = finishedQueue.take();
				if(t.getId() != counter++ ||
						t.getStatus() != Toast.Status.JAMMED) {	//当获取的顺序有问题，或者吐司的状态不对 即 报错
					System.out.println(">>>> Error: " + t);
					System.exit(1);
				} else {
					System.out.println("Chomp! " + t);
				}
			}
		} catch(InterruptedException e) {
			System.out.println("Eater interrupted");
		}
		System.out.println("Eater off");
	}
}
