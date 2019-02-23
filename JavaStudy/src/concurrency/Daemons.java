package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName concurrency.Daemons
 * @Description 21.2.8 后台线程<p>
 * 可以通过调用isDaemon()方法来确认线程是否是一个后台线程, 在后台进程中
 * 创建的线程也是后台线程
 * 
 * @author dayday
 * @date 2019年2月18日
 */
public class Daemons {
	public static void main(String[] args) throws Exception {
		Thread d = new Thread(new Daemon());
		d.setDaemon(true);
		d.start();
		System.out.print("d.isDaemon() = " + d.isDaemon() + ", ");
		// allow the daemon threads to 
		// finish their startup processes:
		TimeUnit.MILLISECONDS.sleep(2000);
	}
}

class Daemon implements Runnable {
	private Thread[] t = new Thread[10];
	
	@Override
	public void run() {
		for (int i = 0; i < t.length; i++) {
			t[i] = new Thread(new DaemonSpawn());
			t[i].start();
			System.out.print("daemonspawn " + i + " started, ");
		}
		// Daemon线程被设置成了后台模式，然后派生出许多子线程，这些线程并没有被显式
		// 地设置为后台模式，不过它们的确是后台线程。接着，Daemon线程进入了无限循环
		// 并在循环里调用yield()方法把控制权交给其他进程
		for (int i = 0; i < t.length; i++) {
			System.out.print("t[" + i + "].isDaemon() = " + t[i].isDaemon() + ", ");
		}
		while (true) {
			Thread.yield();
		}
	}
}

class DaemonSpawn implements Runnable {
	@Override
	public void run() {
		while (true) {
			Thread.yield();
		}
	}
}