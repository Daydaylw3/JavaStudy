package thread.criticalsection;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ExplicitPairManager1 extends PairManager {
//	private Lock lock = new ReentrantLock();
	public void increment() {
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			store(getPair());
		}finally {
			lock.unlock();
		}
	}
}

class ExplicitPairManager2 extends PairManager{
//	private Lock lock = new ReentrantLock();
	public void increment() {
		Pair temp;
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			temp = getPair();	//getPair()方法获取的是对象自身的锁，并非lock的锁，所以需要修
								//改getPair()方法，不然getPair()的方法针对这个类是非线程安全的
		}finally {
			lock.unlock();
		}
		store(temp);
	}
}

public class ExplicitCriticalSection {

	public static void main(String[] args) {
		PairManager
			pman1 = new ExplicitPairManager1(),
			pman2 = new ExplicitPairManager2();
		CriticalSection.testApproaches(pman1, pman2);
	}

}
