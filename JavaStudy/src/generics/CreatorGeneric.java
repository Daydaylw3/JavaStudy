package generics;

/**
 * 创建类型实例
 * 
 * 另一种方式是 模板方法设计模式
 * */
abstract class GenericWithCreate<T> {
	final T element;
	GenericWithCreate() { element = create(); }
	abstract T create();
}
class X {}

//实现创建X的creator类
class Creator extends GenericWithCreate<X> {
	@Override
	X create() {
		return new X();
	}
	void f() {
		System.out.println(element.getClass().getSimpleName());
	}
}

public class CreatorGeneric {
	public static void main(String[] args) {
		Creator c = new Creator();
		c.f();
	}
}
