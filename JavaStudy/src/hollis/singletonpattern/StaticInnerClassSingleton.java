package hollis.singletonpattern;

/**
 * 静态内部类实现单例
 * 
 * 解决饿汉式单例会造成不必要的消耗的问题
 * */
public class StaticInnerClassSingleton {
	//在<p>静态内部类</p>中初始化实例对象
	private static class SingletonHolder {
		private static final StaticInnerClassSingleton INSTANCE =
				new StaticInnerClassSingleton();
	}
	private StaticInnerClassSingleton() {}
	//对外提供获取实例的静态方法
	public static StaticInnerClassSingleton getInstance() {
		return SingletonHolder.INSTANCE;
	}
}
