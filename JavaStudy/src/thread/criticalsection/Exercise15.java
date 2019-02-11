package thread.criticalsection;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Exer{
	private volatile int i = 0, j = 0, k = 0;
	private Object objA = new Object();
	private Object objB = new Object();
	private Object objC = new Object();
	private void incrementI() {
		i++;
	}
	private void incrementJ() {
		j++;
	}
	private void incrementK() {
		k ++;
	}
	
	public void a() {
		synchronized(objA) {
			for(int i = 0; i < 2; i++) {
				incrementI();
			}	
			System.out.println("a() increment" + " i=" + getI());
		}
	}
	public void b() {
		synchronized(objB) {
			for(int i = 0; i < 2; i++) {
				incrementJ();
			}
			System.out.println("b() incrementJ" + " j=" + getJ());
		}
	}
	public void c() {
		synchronized(objC) {
			incrementK();
			System.out.println("c() incrementK" + " k=" + getK());
		}
	}
	public int getI() {
		return i;
	}
	public int getJ() {
		return j;
	}
	public int getK() {
		return k;
	}
}

class Task implements Runnable{
	private Exer ex;
	private String method;
	public Task(Exer ex, String method) {
		this.ex = ex;
		this.method = method;
	}
	public void run() {
		int i = 0;
		while(i < 5) {
			if("a".equals(method))
				ex.a();
			else if("b".equals(method))
				ex.b();
			else if("c".equals(method))
				ex.c();
			else
				ex.a();
			i++;
		}
	}
}

public class Exercise15 {
	
	public static void main(String[] args) {
		final Exer ex = new Exer();
		System.out.println(ex.getI());
		ExecutorService exec  = Executors.newCachedThreadPool();
		exec.execute(new Task(ex, "a"));
		exec.execute(new Task(ex, "b"));
		exec.execute(new Task(ex, "c"));
		new Timer().schedule(new TimerTask() {
			public void run() {
				System.out.println("end");
				System.out.println(ex.getI() + " " + ex.getJ() + " " + ex.getK());
				System.exit(0);
			}
		}, 10);
	}

}
