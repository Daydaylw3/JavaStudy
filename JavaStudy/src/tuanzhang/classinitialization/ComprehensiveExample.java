package tuanzhang.classinitialization;

//实例变量初始化、实例代码块初始化以及构造函数初始化综合实例
class Foo {
	int i = 1;
	
	Foo() {
		System.out.println(i);
		int x = getValue();
		System.out.println(x);
	}
	
	{
		i = 2;
	}
	
	protected int getValue() {
		return i;
	}
}

class Bar extends Foo {
	int j = 1;
	
	Bar() {
		j = 2;
	}
	
	{
		j = 3;
	}
	
	@Override
	protected int getValue() {
		return j;
	}
}

public class ComprehensiveExample {
	public static void main(String[] args) {
		Bar bar = new Bar();
		System.out.println(bar.getValue());
	}
}
/*Foo类构造函数的等价变换
 * Foo() {
 * 	i = 1;
 * 	i = 2;
 * 	System.out.println(i);
 * 	int x = getValue();
 * 	System.out.println(x);
 * }
 * 
 *Bar类构造函数的等价变换 
 * Bar() {
 * 	Foo();
 * 	j = 1;
 * 	j = 3;
 * 	j = 2;
 * }
 * 
 * 由于Bar重载了Foo中的getValue方法，所以根据Java的多态特性
 * 可以知道， 其调用的getValue方法是被Bar重载的那个getValue
 * 方法
 * 所以new Bar()合并起来，做了如下操作
 * i = 1;
 * i = 2;
 * System.out.println(i);
 * int x = j;
 * System.out.println(x);
 * j = 1;
 * j = 3;
 * j = 2;
 * */
