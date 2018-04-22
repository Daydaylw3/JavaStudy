package reuse.exercise;
/**
 * 修改练习5，使A和B以带参数的构造器取代默认的构造器。，为C写一个构造器，并在
 * 其中执行所有的初始化
 * */
class A1 {
	A1(int i){
		System.out.println("A con " + i);
	}
}
class B1 {
	B1(int i){
		System.out.println("B con " + i );
	}
}
class C1 extends A1 {
	B1 b1;
	C1(){
		super(47);
		b1 = new B1(1);
	}
}
public class Exercise7 {

	public static void main(String[] args) {
		C1 c1 = new C1();
	}

}
