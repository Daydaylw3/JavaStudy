package thread.cooperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

class LiftOff implements Runnable {
	protected int countDown = 10;
	private static int taskCount = 1;
	private final int id = taskCount++;
	public LiftOff() {
		
	}
	public LiftOff(int countDown) {
		this.countDown = countDown;
	}
	public String status() {
		return "#" + id + "(" + 
	(countDown > 0 ? countDown : "Liftoff!") + "), ";
	}
	
	@Override
	public void run() {
		while(countDown-- > 0) {
			System.out.println(status());
			Thread.yield();
		}
	}
	
}

class LiftOffRunner implements Runnable {
	private BlockingQueue<LiftOff> rockets;
	public LiftOffRunner(BlockingQueue<LiftOff> queue) {
		rockets = queue;
	}
	public void add(LiftOff ol) {
		try {
			rockets.put(ol);
		}catch(InterruptedException e) {
			System.out.println("interrupted during put()");
		}
	}
	@Override
	public void run() {
		try {
			while(!Thread.currentThread().isInterrupted()) {
				LiftOff rocket = rockets.take();
				rocket.run();
			}
		}catch(InterruptedException e) {
			System.out.println("Waking from take()");
		}
		System.out.println("Exiting LiftOffRunner");
	}
	
}
public class TestBlockingQueues {
	static void getKey() {
		try {
			new BufferedReader(
					new InputStreamReader(System.in)).readLine();
		}catch(java.io.IOException e) {
			throw new RuntimeException(e);
		}
	}
	static void getKey(String message) throws IOException {
//		Scanner scan = new Scanner(message);
		System.in.read(message.getBytes());
		getKey();
	}
	static void test(String msg, BlockingQueue<LiftOff> queue) throws IOException {
		System.out.println(msg);
		LiftOffRunner runner = new LiftOffRunner(queue);
		Thread t = new Thread(runner);
		t.start();
		for(int i = 0; i < 5; i++) {
			runner.add(new LiftOff(5));
		}
		getKey("Press 'Enter' (" + msg + ")");
		t.interrupt();
		System.out.println("Finished " + msg + " test");
	}
	public static void main(String[] args) throws IOException {
		test("LinkedBlockingQueue", 
				new LinkedBlockingQueue<LiftOff>());
		test("ArrayBlockingQueue",
				new ArrayBlockingQueue<LiftOff>(3));
		test("SynchronousQueue",
				new SynchronousQueue<LiftOff>());
	}

}
