package thread.criticalsection;

class DualSynch {
	private Object syncObject = new Object();
	public synchronized void f() {
		for(int i = 0; i < 5; i++) {
			System.out.println("f()");
			Thread.yield();
		}
	}
	public void g() {
		synchronized(syncObject) {
			for(int i = 0; i < 5; i++) {
				System.out.println("g()");
				Thread.yield();
			}
		}
	}
	public void m() {
		synchronized(this) {
			for(int i = 0; i < 5; i++) {
				System.out.println("m()");
				Thread.yield();
			}
		}
	}
}
/**
 * DualSync.f()通过同步整个方法在this同步，而g()有一个在syncObject上同步的
 * synchronized块。因此这两个同步是互相独立的。通过在main()中创建调用f()的Thread
 * 对这一点进行了演示，因为main()线程是被用来调用g()的。从输出可以看到，这两个方式在
 * 同时运行，因此任何一个方法都没有因为另一个方法的同步而被阻塞。
 * 如果换成了m()，则会发生阻塞
 * */
public class SyncObject {
	public static void main(String[] args) {
		final DualSynch ds = new DualSynch();
		new Thread() {
			public void run() {
				ds.f();
			}
		}.start();
		new Thread() {
			public void run() {
				ds.m();
			}
		}.start();
		ds.g();
	}
}
