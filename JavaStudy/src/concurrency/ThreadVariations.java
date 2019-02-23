package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName concurrency.ThreadVariations
 * @Description 21.2.9 编码的变体<p>
 * 有时可以通过使用内部类来将线程代码隐藏在类中将会很有用
 * 
 * @author dayday
 * @date 2019年2月20日
 */
public class ThreadVariations {
	public static void main(String[] args) {
		new InnerThread1("innerthread1");
		new InnerThread2("innerthread2");
		new InnerRunnable1("innerrunnable1");
		new InnerRunnable2("innerrunnable2");
		new ThreadMethod("threadmethod").runTask();
	}
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
					System.out.println(this);
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
						System.out.println(this);
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

class InnerRunnable2 {
	private int countDown = 5;
	private Thread t;
	
	public InnerRunnable2(String name) {
		t = new Thread(new Runnable() {
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
					System.out.println("Sleep() interrupted");
				}
			}
			
			@Override
			public String toString() {
				return Thread.currentThread().getName() + ": " + countDown;
			}
		}, name);
		t.start();
	}
}

// a separate method to run some code as a task
class ThreadMethod {
	private int countDown = 5;
	private Thread t;
	private String name;
	
	public ThreadMethod(String name) {
		this.name = name;
	}
	
	/*
	 * ThreadMethod类展示了在方法内部如何创建线程。当你准备好运行
	 * 线程时,就可以调用这个方法，而在线程开始之后，该方法将返回。如
	 * 果该线程只执行辅助操作，而不是该类的重要操作，那么这与在该类
	 * 的构造器内部启动线程相比，可能是一种更加有用而适合的方式
	 * */ 
	public void runTask() {
		if (t == null) {
			t = new Thread(name) {
				@Override
				public void run() {
					try {
						while (true) {
							System.out.println(this);
							if (--countDown == 0) {
								return;
							}
							sleep(10);
						}
					} catch (InterruptedException ex) {
						System.out.println("sleep() interrupted");
					}
				}
			};
			t.start();
		}
	}
}