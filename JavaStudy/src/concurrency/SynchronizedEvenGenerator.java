package concurrency;


/**
 * @ClassName concurrency.SynchronizedEvenGenerator
 * @Description 21.3.2 解决共享资源竞争</br>
 * <pre>
 * 共享资源一般是以对象的形式存在的内存片段
 * 要控制对共享资源的访问：
 *  1. 得先把他包装进一个对象
 *  2. 然后把所有要访问这个资源的方法标记为synchronized
 *  注意：在使用并发时，将共享域设置为private时非常重要的, 否则, synchronize关键字
 *  将不能防止其他任务直接访问域
 * </pre>
 * 
 * @author dayday
 * @date 2019年2月24日
 */
public class SynchronizedEvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;
	
	// 通过加入 synchronized关键字, 可以防止不希望的线程访问
	@Override
	public synchronized int next() {
		try {
			++currentEvenValue;
			Thread.yield();
			Thread.sleep(100);
			++currentEvenValue;
			System.out.println(currentEvenValue);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new SynchronizedEvenGenerator(), 6);
	}

}
/*
 * 一个任务可以多次获得对象的锁。
 * 如果一个方法在同一个对象上调用了第二个方法，后者又调用了同一对象上的另一个方法，就会发生这种情况。
 * JVM负责跟踪对象被加锁的次数。如果一个对象被解锁（即锁被完全释放），其计数变成0。在任务第一次给对象加锁的时候
 * 计数变为1，每当这个相同的任务在这个对象上获得锁时，计数器都会递增。
 * 显然只有首先获得了锁的任务才能允许多次获取多个锁。
 * 每当任务离开一个synchronize方法，计数器递减，当计数器为0的时候，锁被完全释放。
 */

/*
 * 第一个进入next()的任务将获得锁，任何其他试图获得锁的任务都将从其开始尝试之时被阻塞
 * 直到第一个任务释放锁。通过这种方式，任何时刻只能有一个任务可以通过由互斥量看护的代码。
 */
