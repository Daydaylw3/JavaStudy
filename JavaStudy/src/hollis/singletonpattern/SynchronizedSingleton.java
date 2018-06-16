package hollis.singletonpattern;

/**
 * 线程安全的懒汉式单例
 * 
 * 这种写法能够在多线程中很好的工作，而且看起来它也具备很好的延迟加载
 * 遗憾的是，他效率很低，因为99%情况下不需要同步:该方法的所有操作都是
 * 同步进行的，但是对于非第一次创建对象的情况， 也就是没有进入if语句中
 * 的情况，根本不需要同步操作，可以直接返回instance该方法的所有操作都
 * 是同步进行的， 但是对于非第一次创建对象的情况，也就是没有进入if语句
 * 中的情况，根本不需要同步操作，可以直接返回instance
 * */
public class SynchronizedSingleton {
	//定义实例
	private static SynchronizedSingleton instance;
	private SynchronizedSingleton() {}
	//对该方法加锁
	public static synchronized SynchronizedSingleton getInstance() {
		//在对象被使用的时候才实例化
		if(instance == null)
			instance = new SynchronizedSingleton();
		return instance;
	}
}
