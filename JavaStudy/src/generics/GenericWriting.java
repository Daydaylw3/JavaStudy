package generics;

import java.util.ArrayList;
import java.util.List;

//这和书上说的不太一样
public class GenericWriting {
	static <T> void writeExact(List<T> list, T item) {
		list.add(item);
	}
	static List<Apple> apples = new ArrayList<Apple>();
	static List<Fruit> fruit = new ArrayList<Fruit>();
	
	static void f1() {
		writeExact(apples, new Apple());
		writeExact(fruit, new Apple());	//现在允许这种方式
//		new ArrayList<Fruit>().add(new Apple());
	}
	static <T> void writeWithWildcard(List<? super T> list, T item) {
		list.add(item);
	}
	
	static void f2() {
		writeWithWildcard(apples, new Apple());
		writeWithWildcard(fruit, new Apple());
	}
}
