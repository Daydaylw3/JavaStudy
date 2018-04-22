package reuse.exercise;
/**
 * 创建两个带有默认构造器（空参数列表）的类A和类B。从A中继承产生一个名为C
 * 的新类，并在C内创建一个B类成员。不要给C编写构造器。创建一个C类的对象并
 * 观察其结果。
 * */
class A {
	A(){
		System.out.println("A");
	}
}
class B {
	B() {
		System.out.println("B");
	}
}
class C extends A {
	B b = new B();
}
public class Exercise5 {

	public static void main(String[] args) {
		C c = new C();
	}

}
