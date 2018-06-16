package hollis.singletonpattern;

/**
 * 简单单例
 * 
 * 这种实现方式我们称之为饿汉式。所谓饿汉。这是个比较形象的比喻
 * 对于一个饿汉来说，他希望他想要用到这个实例的时候就能够立即拿
 * 到，而不需要任何等待时间。所以，通过static的静态初始化方式，
 * 在该类第一次被加载的时候，就有一个SimpleSingleton的实例被创
 * 建出来了。 这样就保证在第一次想要使用该对象时，他已经被初始化
 * 好了。
 * */
public class SimpleSingleton {
	//内部实例化一个实例
	private static SimpleSingleton instance = new SimpleSingleton();
	//私有构造方法
	private SimpleSingleton() {}
	public static SimpleSingleton getInstance() {
		return instance;
	}
}
