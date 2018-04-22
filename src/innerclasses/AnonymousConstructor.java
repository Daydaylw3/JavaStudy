package innerclasses;

abstract class Base {
	public Base(int i) {
		System.out.println("Base constructor, i = " + i);
	}
	public abstract void f();
}

//在匿名类中不可能有命名构造器，但通过实例初始化，能够达到
//为匿名内部类创建一个构造器的效果
public class AnonymousConstructor {
	//在此例中，不要求变量i一定是final的。
	//因为i被传递给匿名类的基类的构造器，他并不会在匿名类内部被直接使用
	public static Base getBase(int i) {
		return new Base(i) {
			private int a = i;
			
			{ 
				System.out.println("Inside instance initializer"); 
				//Local variable i defined in an enclosing scope must be final or effectively final
//				i++;
			}
			public void f() {
				System.out.println("in anonymous f()");
			}
		};
	}
	
	public static void main(String[] args) {
		new AnonymousConstructor().getBase(10).f();
	}
}
