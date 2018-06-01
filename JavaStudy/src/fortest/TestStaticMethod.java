package fortest;


/**
 * ①构造方法和 static方法有一点区别，但是还未能证明 构造方法是否是隐式的static方法
 * ②类载时先加载了static域和static代码块，再执行了构造方法
 * ③静态域与静态代码块 的初始化是 从定义的上下顺序来初始化的
 * */
public class TestStaticMethod {
	
	public static String init = "init";
	
	static {
		System.out.println(init);
		//这里报错：Cannot reference a field before it is defined
		//说明静态域 与 静态代码块 的初始化是 从定义的上下顺序来初始化的 
//		System.out.println(init2);
		System.out.println("static block up the construction");
	}
	public static String init2 = "init2";
	public TestStaticMethod() {
		//可是在构造方法中却可以调用非静态方法，这么看来，
		//构造方法还和静态方法有一点区别
		say();
		
		System.out.println(init);
	}
	
	static {
		System.out.println(init2);
		System.out.println("static block under the construction");
	}
	
	public static void f() {
		//这里会报错，因为静态方法调用了一个非静态的方法
		//Cannot make a static reference to the non-static method say() from the type TestStaticMethod
//		say();
	}
	private void say() {
		System.out.println("say()");
	}
	public static void main(String[] args) {
		new TestStaticMethod();
	}

}
