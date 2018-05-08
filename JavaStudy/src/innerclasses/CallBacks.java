package innerclasses;

interface Incrementable{
	void increment();
}
class Callee1 implements Incrementable {
	private int i  = 0;
	public void increment() {
		i++;
		System.out.println(i);
	}
}
class MyIncrement {
	public void increment() {
		System.out.println("other operation");
	}
	static void f(MyIncrement mi) {
		mi.increment();
	}
}
class Callee2 extends MyIncrement {
	private int i = 0;
	public void increment() {
		super.increment();
		i ++;
		System.out.println(i);
	}
	//用Closure来实现回调，返回Callee2对象的实例
	private class Closure implements Incrementable {

		@Override
		public void increment() {
			Callee2.this.increment();
		}
		
	}
	Incrementable getCallbackReference() {
		return new Closure();
	}
}

class Caller {
	private Incrementable callbackReference;
	Caller(Incrementable cbh) {
		callbackReference = cbh;
	}
	void go() {
		callbackReference.increment();
	}
}
/*
 * 这个例子展示了外围类实现一个接口和内部类实现此接口之间的区别。就代码而言，Caller1是简单
 * 的解决方式。Callee2继承自MyIncrement，后者已经有了一个不同的Increment()方法，并且与
 * Incrementable接口期望的Increment()方法不同如果Callee2继承了MyIncrement，就不能为了
 * Incrementable的用途而覆盖Increment()方法，于是只能用内部类独立地实现Incrementable。
 * 当你创建一个内部类时，并没有在外围类的接口中添加东西，也没有修改外围类的接口。
 * */
public class CallBacks {
	public static void main(String[] args) {
		Callee1 c1 = new Callee1();
		Callee2 c2 = new Callee2();
		MyIncrement.f(c2);
		Caller caller1 = new Caller(c1);
		Caller caller2 = new Caller(c2.getCallbackReference());
		caller1.go();
		caller2.go();
	}
}
