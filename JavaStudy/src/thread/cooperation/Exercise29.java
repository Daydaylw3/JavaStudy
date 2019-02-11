package thread.cooperation;
/**
 * 修改ToastOMatic.java，使用两个单独的组装线来创建涂有花生黄油和果冻
 * 的吐司三明治（一个用于花生黄油，一个用于果冻，然后把两条线合并）。
 * */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Butterer2 implements Runnable {
	private ToastQueue toastQueue;
	public Butterer2(ToastQueue tq) {
		toastQueue = tq;
	}
	public void run() {
		
	}
}

class Jellyer implements Runnable {
	private ToastQueue toastQueue;
	public Jellyer(ToastQueue tq) {
		toastQueue = tq;
	}
	public void run() {
		
	}
}

class ToastOMatic2 {
	private ToastQueue buttered = new ToastQueue(), 
			jellyed = new ToastQueue();
	private ExecutorService exec = Executors.newCachedThreadPool();
	public ToastOMatic2() {
//		exec.execute();
	}
}

public class Exercise29 {
	public static void main(String[] args) {

	}

}
