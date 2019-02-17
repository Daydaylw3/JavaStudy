package thread.share;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 添加了一个被互斥调用的锁，使用lock()和unlock()方法在next()内部创建了临界资源
 * 注意return语句必须在try子句中出现，以确保unlock不会过早发生
 * 尽管try-finally所需的代码比synchronize关键字要多，但是这也代表了显式的Lock对象的优点之一：
 * 如果在使用synchronize关键字时，某些事物失败了，那么就会抛出一个异常。但是你没有机会去做任何清理工作。
 * 有了显式的Lock对象，你就可以使用finally子句将系统维护在正确的状态了。
 * */
public class MutexEvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;
	private Lock lock = new ReentrantLock();	//锁对象
	@Override
	public int next() {
		lock.lock();
		try {
			++currentEvenValue;
			Thread.yield();
			++currentEvenValue;
			try {
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(currentEvenValue);
			return currentEvenValue;
		}finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		EvenChecker.test(new MutexEvenGenerator());
	}

}
