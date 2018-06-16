package hollis.singletonpattern;

/**
 * 使用final实现单例
 * 
 * 解决DoubleLockSingleton.java出现的问题
 * */
class FinalWrapper<T> {
	public final T value;
	public FinalWrapper(T value) { this.value = value; }
}
public class FinalSingleton {
	private FinalWrapper<FinalSingleton> helperWrapper = null;
	private FinalSingleton() {}
	public FinalSingleton getHelper() {
		FinalWrapper<FinalSingleton> wrapper = helperWrapper;
		
		if(wrapper == null) {
			synchronized(this) {
				if(helperWrapper == null) {
					helperWrapper = new FinalWrapper<FinalSingleton>(new FinalSingleton());
				}
				wrapper = helperWrapper;
			}
		}
		
		return wrapper.value;
	}
}
