package hollis.singletonpattern;

/**
 * Volatile关键字改进的双重校验锁单例
 * 
 * 解决DoubleLockSingleton.java在多线程下出现指令重排而出现的
 * 线程安全的问题
 * 
 * 但是会出现序列化的问题
 * */
public class VolatileSingleton {
	//使用volatile防止指令重排
	private static volatile VolatileSingleton instance;
	private VolatileSingleton() {}
	public static VolatileSingleton getInstance() {
		if(instance == null) {
			synchronized(VolatileSingleton.class) {
				if(instance == null)
					instance = new VolatileSingleton();
			}
		}
		return instance;
	}
}
