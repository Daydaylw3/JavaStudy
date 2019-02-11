package thread.cooperation;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * notifyAll()将唤醒“所有正在等待的任务”。这是否意味着在程序的任何地方，任何处于wait()状态中
 * 的任务都将被任何对notifyAll()的调用唤醒呢？——事实上，当notifyAll()因某个特定锁而被调用时
 * 只有等待这个锁的任务才会被唤醒。
 * */
class Blocker {
	synchronized void waitingCall() {
		try {
			while(!Thread.interrupted()) {
				wait();
				System.out.print(Thread.currentThread() + " ");
			}
		}catch(InterruptedException e) {
			//OK to exit this way
		}
	}
	synchronized void prod() {
		notify();
	}
	synchronized void prodAll() {
		notifyAll();
	}
}

class Task implements Runnable {
	static Blocker blocker = new Blocker();
	public void run() {
		blocker.waitingCall();
	}
}

class Task22 implements Runnable {
	static Blocker blocker = new Blocker();
	public void run() {
		blocker.waitingCall();
	}
}

/**
 * Task和Task22每个都有其自己的Blocker对象，因此每个Task对象都会在Task.blocker上阻塞，而每个Task22都会在Task22.blocker上阻塞
 * */
public class NotifyVsNotifyAll {
	public static void main(String[] args) throws Exception{
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++) {
			exec.execute(new Task());
		}
		exec.execute(new Task22());
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			boolean prod = true;
			public void run() {
				if(prod) {
					System.out.print("\nnotify() ");
					Task.blocker.prod();
					prod = false;
				}else {
					System.out.print("\nnotifyAll() ");
					Task.blocker.prodAll();
					prod = true;
				}
			}
		}, 400, 400);
		TimeUnit.SECONDS.sleep(5);
		timer.cancel();
		System.out.println("\nTimer canceled");
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println("Task22.blocker.prodAll() ");
		Task22.blocker.prodAll();
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println("\nShutting down");
		exec.shutdownNow();
	}

}
