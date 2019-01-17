package hollis.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadId {
	// Atomic integer containing the next thread id to be assigned
	private static final AtomicInteger nextId = new AtomicInteger(0);
	// Thread local variable containing each thread'id
	private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return nextId.getAndIncrement();
		}
	};
	// returns the current thread's unique id, assigning it if necessary
	public static int get() {
		return threadId.get();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("threadName = " + Thread.currentThread().getName() + 
							", threadId = " + ThreadId.get());
				}
			}).start();
		}
	}
}
