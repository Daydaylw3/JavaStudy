package hollis.singletonpattern;

/**
 * 双重校验锁单例
 * 
 * 对SynchronizedSingleton.java的改进，通过使用同步代码块的方式减小了锁的范围
 * 这样可以大大提高效率：对于已经存在singleton的情况，无须同步，直接return
 * */
public class DoubleLockSingleton {
	private static DoubleLockSingleton instance;
	private DoubleLockSingleton() {}
	public static DoubleLockSingleton getInstance() {
		if(instance == null) {
			synchronized(DoubleLockSingleton.class) {
				if(instance == null)
					instance = new DoubleLockSingleton();
			}
		}
		return instance;
	}
}
