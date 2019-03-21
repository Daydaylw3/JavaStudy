package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName concurrency.AtomicEvenGenerator.java
 * @Description 21.3.4 原子类</br>
 * 
 * @author dayday
 * @date 2019年3月20日
 */
public class AtomicEvenGenerator extends IntGenerator {
	private AtomicInteger i = new AtomicInteger(0);
	
	@Override
	public int next() {
		return i.addAndGet(2);
	}
	
	public static void main(String[] args) {
		EvenChecker.test(new AtomicEvenGenerator());
	}
}
