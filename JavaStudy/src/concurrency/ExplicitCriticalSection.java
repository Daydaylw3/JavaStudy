package concurrency;

/**
 * @ClassName concurrency.ExplicitCriticalSection
 * @Description 21.3.5 临界区</br>
 * 这段代码不能照抄原书本,因为原书本上的代码有bug
 * 
 * @author daydaylw3
 * @date Mar 21, 2019
 */
public class ExplicitCriticalSection {
	public static void main(String[] args) {
		PairManager man1 = new ExplicitPairManager1(),
				man2 = new ExplicitPairManager2();
		CriticalSection.testApproaches(man1, man2);
	}
}

class ExplicitPairManager1 extends PairManager {
	public void increment() {
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			store(getPair());
		} finally {
			lock.unlock();
		}
	}
	// 父类的getPair()方法获取的是对象自身的锁，并非lock的锁，所以需要重写
	// 该方法，不然getPair()方法针对这个类是非线程安全的
	@Override
	public Pair getPair() {
		lock.lock();
		try {
			return new Pair(p.getX(), p.getY());
		} finally {
			lock.unlock();
		}
	}
}

class ExplicitPairManager2 extends PairManager {
	public void increment() {
		Pair temp;
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			temp = getPair(); 	
								
		} finally {
			lock.unlock();
		}
		store(temp);
	}
	// 父类的getPair()方法获取的是对象自身的锁，并非lock的锁，所以需要重写
	// 该方法，不然getPair()方法针对这个类是非线程安全的
	@Override
	public Pair getPair() {
		lock.lock();
		try {
			return new Pair(p.getX(), p.getY());
		} finally {
			lock.unlock();
		}
	}
}