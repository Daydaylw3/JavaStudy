package thread.lock;

/**
 * 真正的问题在于SerialNumberGenerator.nextSerialNumber()在没有同步的情况下对共享可变值进行了访问
 * 基本上，如果一个域可能被多个任务同时访问，或者这个任务中至少有一个写入任务，那么就应该将这个域设置为
 * volatile
 * */
public class SerialNumberGenerator {
	private static volatile int serialNumber = 0;
	public static synchronized int nextSerialNumber() {
		return serialNumber++;	//非线程安全
	}
}
