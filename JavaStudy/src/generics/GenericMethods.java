package generics;

/**
 * 泛型方法
 * 要定义泛型方法，只需要将泛型参数列表置于返回值之前
 * 1、泛型方法使得该方法能够独立与类而产生变化
 * 2、以下是一个基本的指导原则：无论何时，只要你能做到，
 * 你就应该尽量使用泛型方法。也就是说，如果使用泛型方法
 * 可以取代将整个类泛型化，那么就应该只使用泛型方法，因
 * 为它可以使事情更清楚明白。
 * 3、对于一个static的方法而言，无法访问泛型类的类型参数
 * 所以如果static方法需要使用泛型能力，就必须使其成为泛型
 * 方法
 * 4、使用泛型方法时，通常不必指明参数类型，因为编译器会
 * 为我们找出具体的类型，这被称为“类型参数推断”
 * */
public class GenericMethods {
	public <T> void f(T t) {
		System.out.println(t.getClass().getName());
	}
	
	public static void main(String[] args) {
		GenericMethods g = new GenericMethods();
		//这样看起来就好像f()方法被无限次地重载过
		g.f("");
		g.f(1);
		g.f(1.1);
		g.f(1.1f);
		g.f('c');
		g.f(g);
	}
}
