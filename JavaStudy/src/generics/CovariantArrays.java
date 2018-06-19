package generics;

import java.util.ArrayList;
import java.util.List;

class Fruit {}
class Apple extends Fruit {}
class Jonathan extends Apple {}
class Orange extends Fruit {}

//展示数组的一种特殊的行为：可以将子类类型的数组赋予父类型的数组引用
public class CovariantArrays {
	public static void main(String[] args) {
		//创建了一个Apple数组，并将其赋值给一个Fruit数组引用
		Fruit[] fruit = new Apple[10];
		fruit[0] = new Apple();
		fruit[1] = new Jonathan();
		//fruit运行时是Apple[]，而不是Fruit[]
		try {
			//编译器允许你往fruit中加入Fruit
			//因为它有一个Fruit[]引用
			fruit[0] = new Fruit();
		}catch(Exception e) {
			System.out.println(e);
		}
		try {
			fruit[1] = new Orange();
		}catch(Exception e) {
			System.out.println(e);
		}
		/*
		 * 需要注意的是：向上转型不适用于这里，我们真正做的是将一个数组赋值给另一个数组
		 * 数组的行为应该是他可以持有其他对象，这里只是因为我们能够向上转型而已
		 * */
		
		//如果将数组用泛型容器来替代
		//无法编译
//		List<Fruit> flist = new ArrayList<Apple>();
	}
}
/*
 * 1、如果 Fruit[] fruit = new Apple[10]; 这句通过了编译，那我就认为fruit是一个Fruit[]
 * 2、但是fruit[0] = new Fruit(); 运行时会报错，那么我也认为其实fruit不能存储Fruit类型的对象
 * 3、综合1、2得出结论，那么fruit是实际上是一个只能持有Apple对象及其子类的Fruit[]数组
 * 4、编译器为何会允许这样的语法产生?为何不让 Fruit[] fruit = new Apple[10]; 或者 fruit[0] = new Fruit(); 在编译期报错?
 * */

