package hollis.singletonpattern;

/**
 * 懒汉式单例
 * 
 * 懒汉，就是不会提前把实例创建出来，将类对自己的实例化延迟到
 * 第一次被引用的时候
 * 
 * 在多线程下存在线程安全的问题
 * */
public class LazySingleton {
	//定义实例
	private static LazySingleton instance;
	private LazySingleton() {}
	public LazySingleton getInstance() {
		//在对象被使用的时候才实例化
		if(instance == null)
			instance = new LazySingleton();
		return instance;
	}
}
