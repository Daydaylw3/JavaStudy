package generics;

/**
 * 一个最普通的泛型类
 * 此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
 * 在实例化泛型类时，必须指定T的具体类型
 * */
public class Generic<T> {
	//T的类型由外部指定
	private T key;
	
	public Generic(T key) {	//T的类型由外部指定
		this.key = key;
	}
	
	public T getKey() {	//T的类型由外部指定
		return key;
	}
}
