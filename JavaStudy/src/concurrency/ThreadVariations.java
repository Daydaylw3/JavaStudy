package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName concurrency.ThreadVariations
 * @Description 21.2.9 编码的变体<p>
 * 
 * @author dayday
 * @date 2019年2月20日
 */
public class ThreadVariations {
	
}

class InnerThread1 {
	private int countDown = 5;
	private Inner inner;
	
	private class Inner extends Thread {
		Inner(String name) {
			super(name);
			start();
		}
		
		@Override
		public void run() {
			try {
				while (true) {
					System.out.print(this);
					if (--countDown == 0) {
						return;
					}
					sleep(10);
				}
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}
		}
		
		@Override
		public String toString() {
			return getName() + ": " + countDown;
		}
	}
	
	public InnerThread1(String name) {
		inner = new Inner(name);
	}
}

// using an anonymous inner class
class InnerThread2 {
	private int countDown = 5;
	private Thread t;
	
	public InnerThread2(String name) {
		t = new Thread(name) {
			@Override
			public void run() {
				try {
					while (true) {
						System.out.print(this);
						if (--countDown == 0) {
							return;
						}
						TimeUnit.MILLISECONDS.sleep(10);
					}
				} catch (InterruptedException ex) {
					System.out.println("sleep() interrupted");
				}
			}
			
			@Override
			public String toString() {
				return Thread.currentThread().getName() + ": " + countDown;
			}
		};
		t.start();
	}
}

// using a named runnable implementation
class InnerRunnable1 {
	private int countDown = 5;
	private Inner inner;
	
	private class Inner implements Runnable {
		Thread t;
		Inner(String name) {
			t = new Thread(this, name);
			t.start();
		}
		
		@Override
		public void run() {
			try {
				while (true) {
					System.out.println(this);
					if (--countDown == 0) {
						 return ;
					}
					TimeUnit.MILLISECONDS.sleep(10);
				} 
			} catch (InterruptedException ex) {
				System.out.println("sleep() interrupted");
			}
		}
		
		@Override
		public String toString() {
			return t.getName() + ": " + countDown;
		}
	}
	
	public InnerRunnable1(String name) {
		inner = new Inner(name);
	}
}