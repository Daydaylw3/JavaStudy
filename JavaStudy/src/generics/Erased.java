package generics;

/**
 * 擦除丢失了在泛型代码中执行某些操作的能力
 * 任何在运行时需要知道确切类型信息的操作都
 * 将无法工作
 * */
public class Erased<T> {
	private final int SIZE = 100;
	public static void f(Object arg) {
//		if(arg instanceof T) {}
//		T var = new T();
//		T[] array = new T[SIZE];
//		T[] array = (T)new Object[SIZE];
	}
}
