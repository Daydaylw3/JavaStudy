package fortest;

class Info<T> {
	
}
class LimitGenericTest{
	
}
public class LimitGeneric {
	public static void main(String[] args) {
		fun1(new Info<LimitGenericTest>());
//		fun2(new Info<LimitGenericTest>());
//		fun3(new Info<LimitGenericTest>());
		
		fun2(new Info<Number>());
		fun2(new Info<Integer>());
//		fun2(new Info<Object>());
	}
	public static void fun1(Info<?> temp) {	// 可以接收任意的泛型对象
		
	}
	public static void fun2(Info<? extends Number> temp) {	// 只能接收Number及其Number的子类
		
	}
	public static void fun3(Info<? super String> temp) {	// 只能接收String或Object类型的泛型
		
	}
}
