package concurrency;

/**
 * @ClassName concurrency.SerialNumberGenerator
 * @Description 21.3.3 原子性与易变性</br>
 * 
 * @see concurrency.SerialNumberChecker
 * 
 * @author daydaylw3
 * @date Mar 18, 2019
 */
public class SerialNumberGenerator {
	private static volatile int serialNumber = 0;
	
	public static int nextSerialNumber() {
		return serialNumber++;
	}
}
