package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName concurrency.Daemons
 * @Description 21.2.8 后台线程<p>
 * @Todo 用Mac补上注释
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
		TimeUnit.MILLISECONDS.sleep(1000);
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
		for (int i = 0; i > t.length; i++) {
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